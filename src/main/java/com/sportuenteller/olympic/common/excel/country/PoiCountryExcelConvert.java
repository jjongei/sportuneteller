package com.sportuenteller.olympic.common.excel.country;

import com.sportuenteller.olympic.common.component.ExcelComponent;
import com.sportuenteller.olympic.common.utils.Properties;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PoiCountryExcelConvert extends ExcelComponent<CountryExcelRead, WriteCountryExcelRequest> {

    @Autowired
    Properties properties;

    @Override
    protected CountryExcelRead excelRead(XSSFRow row) {
        return new CountryExcelRead(this.toString(row.getCell(0))
                , this.toString(row.getCell(1))
                , this.toString(row.getCell(2))
                , this.toString(row.getCell(3))
                , this.toString(row.getCell(4)));
    }

    @Override
    protected XSSFRow excelWrite(XSSFRow row, WriteCountryExcelRequest request) {
        row.getCell(0).setCellValue(request.getCountryCode());
        row.getCell(1).setCellValue(request.getCountryNameKr());
        row.getCell(2).setCellValue(request.getCountryNameEn());
        row.getCell(3).setCellValue(request.getAvailable());
        row.getCell(4).setCellValue("F");
        return row;
    }

    @Override
    protected int getStartRow() {
        return 2;
    }

    @Override
    protected String getFullPath() {
        return "country";
    }

    @Override
    protected String getSource() {
        return properties.getCountryExcelTemplate();
    }
}
