package com.rahmatullin.dev;

import com.rahmatullin.dev.utils.Deserialization;
import com.rahmatullin.dev.utils.Serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedDeque;

import static com.rahmatullin.dev.utils.Serialization.workbook;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Employee> employees = new ArrayList<>();
        ConcurrentLinkedDeque<Tasks> stackOfTasks = new ConcurrentLinkedDeque<>();
        TaskGenerator taskGenerator = new TaskGenerator(stackOfTasks);
        Thread taskGenreatorThread = new Thread(taskGenerator);
        taskGenreatorThread.setDaemon(true);
        taskGenreatorThread.start();
        try {
            ArrayList<String[]> employerData = Deserialization.reader();
            for (int i = 0; i < employerData.size(); i++) {
                employees.add(new Employee(employerData.get(i)[0], (int) Double.parseDouble(employerData.get(i)[1]), stackOfTasks));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int i = 1;
        while (i < 8){
            for(Employee emp: employees){
                emp.setWorkMinutes(emp.getMaxTime());
                Thread employee = new Thread(emp);
                employee.setDaemon(true);
                employee.start();
            }

            Thread.sleep(1440);
            System.out.println("Прошел день:" + i);
            Serialization.serializeData(employees, String.valueOf(i));
            i++;
            if(i == 8) {
                try (FileOutputStream outputStream = new FileOutputStream("employeeTasks.xlsx")) {
                    workbook.write(outputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Файл успешно создан.");
            }
        }
    }

}