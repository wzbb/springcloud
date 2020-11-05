package com.rb.ribbonconsumer.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by admin on 2020-11-4.
 */
@Component
@EnableScheduling
@EnableAsync
public class MultithreadScheduleTask {

//    @Async
//    @Scheduled(fixedDelay = 1000)
//    public void first() throws InterruptedException{
//        System.out.println("第一个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
//        System.out.println();
//        Thread.sleep(1000 * 10);
//    }
//
//    @Async
//    @Scheduled(fixedDelay = 2000)
//    public void second() {
//        System.out.println("第二个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
//        System.out.println();
//    }
}
