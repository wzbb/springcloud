package com.rb.ribbonconsumer.controller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by admin on 2020-11-6.
 */
public class CountDownLatchTest1 {

    public static void main(String[] args){
        CountDownLatch latch = new CountDownLatch(3);
        CountDownLatch order = new CountDownLatch(1);

        ExecutorService service = Executors.newFixedThreadPool(3);

        AtomicInteger count = new AtomicInteger(0);

        for (int i = 0; i < 3; i++){
            Excute excute = new Excute(latch, order, count);
            Thread thread = new Thread(excute);
            service.execute(excute);
        }

        while (true){
            System.out.println("*******************"+count);
            if(count.intValue() == 3) {
                System.out.println("*******************"+count);
                try {
                    //Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("主线程" + Thread.currentThread().getName() + "发令枪，比赛开始");
                    order.countDown();
                    latch.await(); //阻塞当前线程，直到计数器的值为0
                    System.out.println("主线程" + Thread.currentThread().getName() + "所有人跑完，统计成绩。。。");
                    System.out.println("========================");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                service.shutdown();
                break;
            }
        }

    }
}
