package net.wanho.controller;

import lombok.RequiredArgsConstructor;
import net.wanho.service.VodCallbackServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/vod")
@RequiredArgsConstructor
public class VodCallbackController {

    private final VodCallbackServiceI vodCallbackService;

    @PostMapping("/callback")
    public ResponseEntity<String> handleCallback(@RequestBody Map<String, Object> payload) {
        vodCallbackService.handleCallback(payload);
        return ResponseEntity.ok("success");
    }


}
