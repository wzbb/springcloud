package com.rb.ribbonprovider.controller;

import com.rb.util.RabbitSender;
import org.checkerframework.checker.units.qual.A;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by admin on 2020-11-6.
 */
@ComponentScan
public class Excute implements Runnable {

    private CountDownLatch latch;

    private CountDownLatch order;

    private AtomicInteger count;

//    @Autowired
//    RabbitSender rabbitSender;


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

            String content = "testsendRabbitMessage";
            Map<String, Object> pro = new HashMap<>();
            pro.put("name", "ruanbin");
            RabbitSender rabbitSender = new RabbitSender(new RabbitTemplate());
            order.await();
            rabbitSender.send(content, pro);
            latch.countDown();//当前线程调用此方法，则计数减一
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
