package com.rb.ribbonprovider.controller;

import com.rb.ribbonprovider.service.ProviderService;
import com.rb.util.RabbitSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by rb on 2020/10/28.
 */
@RestController
public class ProviderController {

    @Autowired
    ProviderService providerService;
    @Autowired
    RabbitSender rabbitSender;

    @GetMapping("/index")
    public String index(){
        return providerService.getProvider();
    }


    @GetMapping("/sendRabbitMessage")
    public void sendRabbitMessage() throws Exception{
        String content = "testsendRabbitMessage";
        Map<String, Object> pro = new HashMap<>();
        pro.put("name", "ruanbin");
        rabbitSender.send(content, pro);

        pro.put("test", "queue2");
        rabbitSender.send2("dasdfasdf", pro);
    }

    @GetMapping("/testException")
    public int testException() {
        String[] arr = new String[5];
        System.out.println(arr[6]);
        return 2;
    }
    @GetMapping("/testResponseAdvice")
    public int testResponseAdvice(){
        return 1;
    }


    @GetMapping("/testHighEx")
    public void testHighEx(){
        int n = 120;

        AtomicInteger count = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(n);
        CountDownLatch order = new CountDownLatch(1);


        for(int i = 0; i < n; i++){
            Excute excute = new Excute(latch, order, count);
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("子线程" + Thread.currentThread().getName() + "开始等待赛跑");
                    count.incrementAndGet();

                    String content = count.toString();
                    Map<String, Object> pro = new HashMap<>();
                    pro.put("name", "ruanbin");
                    try {
                        order.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        rabbitSender.send(content, pro);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    latch.countDown();//当前线程调用此方法，则计数减一
                }
            });
            t.start();
        }


        while (true){
            if(count.intValue() == n) {
                System.out.println("*******************"+count);
                try {
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("主线程" + Thread.currentThread().getName() + "发令枪，比赛开始");
                    order.countDown();
                    latch.await(); //阻塞当前线程，直到计数器的值为0
                    System.out.println("主线程" + Thread.currentThread().getName() + "所有人跑完，统计成绩。。。");
                    System.out.println("========================");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }

}
