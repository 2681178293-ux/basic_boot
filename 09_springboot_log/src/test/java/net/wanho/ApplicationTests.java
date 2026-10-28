package net.wanho;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
class ApplicationTests {

    // Logger logger = LoggerFactory.getLogger(ApplicationTests.class);
    @Test
    void contextLoads() {
        log.error("error");
        log.warn("warn");
        log.info("info");
        log.debug("debug");
    }

}
