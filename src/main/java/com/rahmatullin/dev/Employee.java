package com.rahmatullin.dev;

import java.util.concurrent.ConcurrentLinkedDeque;

public class Employee implements Runnable {
    public void setTaskComplete(int taskComplete) {
        this.taskComplete = taskComplete;
    }

    public int getTaskCompleteWeek() {
        return taskCompleteWeek;
    }

    public void setTaskCompleteWeek(int taskCompleteWeek) {
        this.taskCompleteWeek = taskCompleteWeek;
    }

    private String name;

    private int workMinutes;
    private int maxTime;
    private ConcurrentLinkedDeque<Tasks> tasks;
    private int taskComplete = 0;
    private int taskCompleteWeek;

    public Employee(String name, int workMinutes, ConcurrentLinkedDeque<Tasks> tasks) {
        this.name = name;
        this.workMinutes = workMinutes;
        this.tasks = tasks;
        this.maxTime = workMinutes;
    }

    @Override
    public void run() {
        taskComplete = 0;
        while (workMinutes != 0) {
            if (!tasks.isEmpty()){
                var task = tasks.pollFirst();
                if (task == null){
                    try {
                        Thread.sleep(10);
                        continue;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                int durationOfTask = task.getDurationOfTaskInMinutes();
                if (workMinutes - durationOfTask < 0) {
                    task.setDurationOfTaskInMinutes(durationOfTask - workMinutes);
                    workMinutes = 0;
                    tasks.addFirst(task);
                }
                if (workMinutes - durationOfTask >= 0) {
                    workMinutes = workMinutes - durationOfTask;
                    taskCompleteWeek++;
                    taskComplete++;

                }
            }
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", workMinutes=" + workMinutes +
                ", maxTime=" + maxTime +
                ", taskComplete=" + taskComplete +
                '}';
    }

    public String getName() {
        return name;
    }
    public int getMaxTime() {
        return maxTime;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getWorkMinutes() {
        return workMinutes;
    }

    public void setWorkMinutes(int workMinutes) {
        this.workMinutes = workMinutes;
    }

    public int getTaskComplete() {
        return taskComplete;
    }
}
