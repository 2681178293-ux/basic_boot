package net.wanho.sse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SSE 入门：模拟外卖三步状态推送（已下单 → 配送中 → 已送达）。
 */
@RestController
@RequestMapping("/api/order")
public class OrderTrackController {

    private final ExecutorService pool = Executors.newCachedThreadPool();

    @GetMapping(path = "/{orderId}/track", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter track(@PathVariable("orderId") String orderId) {
        SseEmitter emitter = new SseEmitter(60_000L);

        emitter.onCompletion(() -> System.out.println("[SSE] completed orderId=" + orderId));
        emitter.onTimeout(() -> {
            System.out.println("[SSE] timeout orderId=" + orderId);
            emitter.complete();
        });
        emitter.onError(ex -> System.out.println("[SSE] error: " + ex.getMessage()));

        // 推送放线程池，别堵 Tomcat 工作线程
        pool.execute(() -> {
            try {
                // 第 1 条
                emitter.send(SseEmitter.event()
                        .name("status")
                        .data("{\"orderId\":\"" + orderId + "\",\"message\":\"已下单\"}"));
                Thread.sleep(1000);

                // 第 2 条
                emitter.send(SseEmitter.event()
                        .name("status")
                        .data("{\"orderId\":\"" + orderId + "\",\"message\":\"配送中\"}"));
                Thread.sleep(1000);

                // 第 3 条
                emitter.send(SseEmitter.event()
                        .name("status")
                        .data("{\"orderId\":\"" + orderId + "\",\"message\":\"已送达\"}"));

                // 告诉前端：可以关连接了
                emitter.send(SseEmitter.event()
                        .name("done")
                        .data("{\"orderId\":\"" + orderId + "\",\"message\":\"推送结束\"}"));
                emitter.complete();
            } catch (IOException | InterruptedException e) {
                emitter.completeWithError(e);
                Thread.currentThread().interrupt();
            }
        });

        return emitter;
    }
}
