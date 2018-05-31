package com.sportuenteller.olympic.common.excel.country;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WriteCountryExcelRequest {
    private String countryCode;
    private String countryNameKr;
    private String countryNameEn;
    private String available;

    public WriteCountryExcelRequest(String countryCode
            , String countryNameKr
            , String countryNameEn
            , Boolean available) {

        this.countryCode = countryCode;
        this.countryNameKr = countryNameKr;
        this.countryNameEn = countryNameEn;
        this.available = available != null && available ? "T" : "F";
    }

}
