package com.rb.ribbonconsumer.controller;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by admin on 2020-11-6.
 */
public class CycliExcuter implements Runnable {

    private CyclicBarrier cyclicBarrier;

    public CycliExcuter(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("子线程" + Thread.currentThread().getName() + "到了景点门口，等待其他人");
            cyclicBarrier.await();
            Thread.sleep((long) Math.random()*10000);
            System.out.println("子线程" + Thread.currentThread().getName() + "所有人到了，进入游玩");
        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (BrokenBarrierException e){
            e.printStackTrace();
        }

    }
}
