package com.rahmatullin.dev.utils;

import com.rahmatullin.dev.Employee;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Serialization {
    public static Workbook workbook = new XSSFWorkbook();


    // Добавление листа

    public static void serializeData(ArrayList<Employee> emps, String day){
        Sheet sheet = workbook.createSheet(day);

        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Name");
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("workingTime");
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("taskDone");
        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("taskDoneWeek");

        Object[][] data = new Object[emps.size()][];
        int i = 0;
        for(Employee emp: emps){
            data[i] = new Object[]{emp.getName(), emp.getWorkMinutes() / 60, emp.getTaskComplete(), emp.getTaskCompleteWeek()};
            i++;
        }

        int rowNum = 1;
        for (Object[] row : data) {
            Row rowObj = sheet.createRow(rowNum++);
            int cellNum = 0;
            for (Object field : row) {
                Cell cell = rowObj.createCell(cellNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }
    }

    // день, сколько часов работал, сколько заданий выполнил
}
