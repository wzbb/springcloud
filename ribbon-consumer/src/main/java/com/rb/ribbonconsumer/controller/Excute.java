package com.rb.ribbonconsumer.controller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by admin on 2020-11-6.
 */
public class Excute implements Runnable {

    private CountDownLatch latch;

    private CountDownLatch order;

    private AtomicInteger count;

    public Excute(CountDownLatch latch, CountDownLatch order, AtomicInteger count){
        this.latch = latch;
        this.order = order;
        this.count = count;
    }

    @Override
    public void run() {
        try{
            System.out.println("子线程" + Thread.currentThread().getName() + "开始等待赛跑");
            count.incrementAndGet();
            order.await();
            System.out.println("子线程" + Thread.currentThread().getName() + "起跑！！");
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("子线程"+Thread.currentThread().getName()+"选手跑完");
            latch.countDown();//当前线程调用此方法，则计数减一
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
