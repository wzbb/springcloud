package com.rb.ribbonprovider.controller;

import com.rb.util.RabbitSender;
import lombok.Data;
import org.checkerframework.checker.units.qual.A;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by admin on 2020-11-6.
 */
@Component
@Data
public class Excute implements Runnable {

    private CountDownLatch latch;

    private CountDownLatch order;

    private AtomicInteger count;

    @Autowired
    RabbitSender rabbitSender;
    @Autowired
    RedissonClient redissonClient;

    public Excute(){
        super();
    }

    public Excute(CountDownLatch latch, CountDownLatch order, AtomicInteger count){
        this.latch = latch;
        this.order = order;
        this.count = count;
    }
    public Excute(CountDownLatch latch, CountDownLatch order){
        this.latch = latch;
        this.order = order;
    }

    @Override
    public void run() {
        try{
            System.out.println("子线程" + Thread.currentThread().getName() + "开始等待赛跑");

            count.incrementAndGet();

            String content = count.toString();
            Map<String, Object> pro = new HashMap<>();
            pro.put("name", "ruanbin");
            //RabbitSender rabbitSender = new RabbitSender(new RabbitTemplate());
            order.await();

            RLock myLock = redissonClient.getLock("sendmessage");
            boolean hasLock = myLock.tryLock(5, 2, TimeUnit.SECONDS);
            if(hasLock){
                System.out.println("子线程" + Thread.currentThread().getName() + "*********抢到锁");
                //Thread.sleep(5100);
                rabbitSender.send(content, pro);
                myLock.unlock();
            }else{
                System.err.println("子线程" + Thread.currentThread().getName() + "====没有抢到锁");
            }
            latch.countDown();//当前线程调用此方法，则计数减一
        }catch (Exception e){
            latch.countDown();
            e.printStackTrace();
        }
    }


//    @Override
//    public void run() {
//        try{
//            System.out.println("子线程" + Thread.currentThread().getName() + "开始等待赛跑");
//            count.incrementAndGet();
//
//            String content = count.toString();
//            Map<String, Object> pro = new HashMap<>();
//            pro.put("name", "ruanbin");
//            //RabbitSender rabbitSender = new RabbitSender(new RabbitTemplate());
//            order.await();
//            rabbitSender.send(content, pro);
//            latch.countDown();//当前线程调用此方法，则计数减一
//        }catch (Exception e){
//            latch.countDown();
//            e.printStackTrace();
//        }
//    }
}
