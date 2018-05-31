package com.sportuenteller.olympic.common.excel.game;

import com.sportuenteller.olympic.common.component.ExcelComponent;
import com.sportuenteller.olympic.common.utils.Properties;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PoiGameExcelConvert extends ExcelComponent<GameExcelRead, WriteGameExcelRequest>{
    @Autowired
    Properties properties;

    @Override
    protected GameExcelRead excelRead(XSSFRow row) {
        return new GameExcelRead(this.toString(row.getCell(2))
                , this.toString(row.getCell(0))
                , this.toString(row.getCell(3))
                , this.toString(row.getCell(4))
                , this.toString(row.getCell(8))
                , this.toString(row.getCell(9))
                , this.toString(row.getCell(10))
                , this.toString(row.getCell(11))
                , this.toString(row.getCell(5))
                , this.toString(row.getCell(12)));
    }
    @Override
    protected XSSFRow excelWrite(XSSFRow row, WriteGameExcelRequest request) {

        row.getCell(0).setCellValue(request.getGameTypeCode());
        row.getCell(1).setCellValue(request.getGameTypeSubject());
        row.getCell(2).setCellValue(Long.toString(request.getGameId()));
        row.getCell(3).setCellValue(request.getGameNameKr());
        row.getCell(4).setCellValue(request.getGameNameEn());
        row.getCell(5).setCellValue(request.getGroup());
        row.getCell(6).setCellValue(request.getStatusTypeSubject());
        row.getCell(7).setCellValue(request.getTermination());
        row.getCell(8).setCellValue(request.getStartDate());
        row.getCell(9).setCellValue(request.getEndDate());
        row.getCell(10).setCellValue(request.getVoteStartDate());
        row.getCell(11).setCellValue(request.getVoteEndDate());
        row.getCell(12).setCellValue("F");

        return row;
    }

    @Override
    protected int getStartRow() {
        return 2;
    }

    @Override
    protected String getFullPath() {
        return "game";
    }

    @Override
    protected String getSource() {
        return properties.getGameExcelTemplate();
    }
}
