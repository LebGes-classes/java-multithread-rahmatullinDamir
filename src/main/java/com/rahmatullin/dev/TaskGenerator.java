package com.rahmatullin.dev;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;

public class TaskGenerator implements Runnable{


    ConcurrentLinkedDeque<Tasks> tasks;
    TaskGenerator(ConcurrentLinkedDeque<Tasks> tasks){
        this.tasks = tasks;
    }
    @Override
    public void run() {
        int i = 0;
        Random rnd = new Random();
        while (true){
            Tasks task =  new Tasks(String.valueOf(i), rnd.nextInt(60, 960));
            tasks.addLast(task);
            i++;
            try {
                Thread.sleep(rnd.nextInt(0, 200));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
