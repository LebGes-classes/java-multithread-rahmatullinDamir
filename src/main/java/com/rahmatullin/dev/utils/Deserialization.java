package com.rahmatullin.dev.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Deserialization {
    public static ArrayList<String[]> reader() throws IOException {
        ArrayList<String[]> employerData = new ArrayList<>();
        try (FileInputStream file = new FileInputStream("employer.xlsx");
             Workbook workbook = new XSSFWorkbook(file)){
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                String[] employer = new String[2];
                int cellCount = 0;
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    employer[cellCount] = String.valueOf(cell);
                    cellCount++;
                }
                employerData.add(employer);
            }
        } return employerData;
    }
}
