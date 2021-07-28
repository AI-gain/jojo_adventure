package com.jojo.util.bean.thread;

import com.jojo.util.guid.IdWorker;

public class MyThread implements Runnable {

    private int key;

    public MyThread(int key) {
        this.key = key;
    }

    @Override
    public void run() {
        IdWorker idWorker = new IdWorker(0, 0);
        for (int i = 0; i < 1000; i++) {
            long id = idWorker.nextId();
            // System.out.println(Long.toBinaryString(id));
            // System.out.println(id);
        }
    }

    public static void main(String[] args) {
        MyThread myThread1 = new MyThread(0);
        MyThread myThread2 = new MyThread(1);
        Thread mt1 = new Thread(myThread1);
        Thread mt2 = new Thread(myThread2);

        mt1.run();
        mt2.run();
    }
}
