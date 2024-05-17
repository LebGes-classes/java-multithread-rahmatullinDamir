package com.rahmatullin.dev;

import java.util.Queue;
import java.util.Random;

public class Tasks  {
    private String nameOFTask;
    private int durationOfTaskInMinutes;

    @Override
    public String toString() {
        return "Tasks{" +
                "nameOFTask='" + nameOFTask + '\'' +
                ", durationOfTaskInMinutes=" + durationOfTaskInMinutes +
                '}';
    }

    public int getDurationOfTaskInMinutes() {
        return durationOfTaskInMinutes;
    }

    public void setDurationOfTaskInMinutes(int durationOfTaskInMinutes) {
        this.durationOfTaskInMinutes = durationOfTaskInMinutes;
    }

    public Tasks(String nameOFTask, int durationOfTask) {
        this.nameOFTask = nameOFTask;
        this.durationOfTaskInMinutes = durationOfTask;
    }
}
