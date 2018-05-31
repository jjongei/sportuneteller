package com.sportuenteller.olympic.common.excel.player;

import com.sportuenteller.olympic.common.component.ExcelComponent;
import com.sportuenteller.olympic.common.utils.Properties;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PoiPlayerExcelConvert extends ExcelComponent<PlayerExcelRead, WritePlayerExcelRequest> {
    @Autowired
    Properties properties;

    @Override
    protected PlayerExcelRead excelRead(XSSFRow row) {
        return new PlayerExcelRead(toString(row.getCell(0))
                ,toString(row.getCell(1))
                , toString(row.getCell(2))
                , toString(row.getCell(3))
                , toString(row.getCell(4))
                , toString(row.getCell(5))
                , toString(row.getCell(6))
                , toString(row.getCell(7))
                , toString(row.getCell(8))
                , toString(row.getCell(9))
        );
    }

    @Override
    protected XSSFRow excelWrite(XSSFRow row, WritePlayerExcelRequest request) {
        row.getCell(0).setCellValue(request.getPlayerId());
        row.getCell(1).setCellValue(request.getName());
        row.getCell(2).setCellValue(request.getSexuality());
        row.getCell(3).setCellValue(request.getCountryCode());
        row.getCell(4).setCellValue(request.getCountryReference());
        row.getCell(5).setCellValue(request.getCode());
        row.getCell(6).setCellValue(request.getGameId());
        row.getCell(7).setCellValue(request.getGameNameKr());
        row.getCell(8).setCellValue(request.getGameNameEn());
        row.getCell(9).setCellValue("F");
        return row;
    }

    @Override
    protected int getStartRow() {
        return 2;
    }

    @Override
    protected String getFullPath() {
        return "player";
    }

    @Override
    protected String getSource() {
        return properties.getPlayerExcelTemplate();
    }
}
