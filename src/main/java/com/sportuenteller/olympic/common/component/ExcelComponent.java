package com.sportuenteller.olympic.common.component;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

abstract public class ExcelComponent<T, V> {

    abstract protected T excelRead(XSSFRow row);

    abstract protected XSSFRow excelWrite(XSSFRow row, V request);

    public List<T> read(File excel) {
        List<T> excels = new ArrayList<>();

        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(excel);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);

            int startRow = 2;
            int rows = sheet.getPhysicalNumberOfRows();
            XSSFRow row = null;
            for (int r = startRow; r < rows; r++) {
                row = sheet.getRow(r);
                if (row != null) {
                    try {
                        T obj = this.excelRead(row);
                        excels.add(obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (Exception e) {
            }
        }
        return excels;
    }

    abstract protected int getStartRow();
    abstract protected String getFullPath();
    abstract protected String getSource();

    public File write(List<V> list) {
        int startRow  = getStartRow();
       // String fullPath = this.getFullPath();
        String fullPath = Paths.get("").toAbsolutePath().toString();
        String source = "/"+this.getSource();

        File file = null;
        File filePath = new File(fullPath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        InputStream in = null;
        FileOutputStream out = null;
        try {

            in = getClass().getResourceAsStream(source);
            XSSFWorkbook wb = new XSSFWorkbook(in);
            XSSFSheet sheet = wb.getSheetAt(0);

            int count = 0;
            for (V obj : list) {
                try {
                    count = createRow(obj, sheet, startRow, count);
                } catch (Exception e) {
                }
            }
            file = new File(fullPath + "\\" + this.getFullPath() + ".xlsx");
            out = new FileOutputStream(file);
            wb.write(out);
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException e) {
            }
            try {
                if (out != null) out.close();
            } catch (IOException e) {
            }
        }
        return file;
    }

    protected String toString(XSSFCell cell) {
        String value = null;
        if (cell != null) {
            switch (cell.getCellType()) {
                case XSSFCell.CELL_TYPE_FORMULA:
                    value = cell.getCellFormula();
                    break;
                case XSSFCell.CELL_TYPE_NUMERIC:
                    value = "" + cell.getNumericCellValue();
                    break;
                case XSSFCell.CELL_TYPE_STRING:
                    value = "" + cell.getStringCellValue();
                    break;
                case XSSFCell.CELL_TYPE_BLANK:
                    value = null;
                    break;
                case XSSFCell.CELL_TYPE_ERROR:
                    value = "" + cell.getErrorCellValue();
                    break;
                default:
            }
        }
        return value;
    }

    private int createRow(V obj, XSSFSheet sheet, int startRow, int i) {

        XSSFRow row = null;
        if (i == 0) {
            row = sheet.getRow(startRow);
        } else {
            row = sheet.createRow(startRow + i);
            makeCell(row, sheet.getRow((startRow + i) - 1), sheet.getRow((startRow + i) - 1).getLastCellNum());
        }

        this.excelWrite(row, obj);
        // row.getCell(0).setCellValue(date);
        //row.getCell(1).setCellValue(subject);
        //row.getCell(2).setCellValue(content);

        return i + 1;
    }

    private void makeCell(XSSFRow currentHssfrow, XSSFRow prevHssfrow, int count) {
        for (int i = 0; count > i; i++) {
            XSSFCell cell = currentHssfrow.createCell(i);
            cell.setCellStyle(prevHssfrow.getCell(i).getCellStyle());
            cell.setCellType(prevHssfrow.getCell(i).getCellType());
        }
    }
}
