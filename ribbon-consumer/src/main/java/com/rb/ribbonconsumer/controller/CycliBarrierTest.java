package com.rb.ribbonconsumer.controller;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by admin on 2020-11-6.
 */
public class CycliBarrierTest {

    public static void  main(String[] args){

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++){
            CycliExcuter excute = new CycliExcuter(cyclicBarrier);
            Thread thread = new Thread(excute);
            service.execute(excute);
            try {
                Thread.sleep((long) Math.random()*10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
