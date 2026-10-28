package net.wanho;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

//@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+ ":1");
        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+ ":2");
        }).start();

        new Scanner(System.in).next();
    }



}
