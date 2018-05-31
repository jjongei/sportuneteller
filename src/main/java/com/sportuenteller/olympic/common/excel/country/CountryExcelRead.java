package com.sportuenteller.olympic.common.excel.country;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CountryExcelRead {
    private String countryCode;
    private String countryNameKr;
    private String countryNameEn;
    private boolean available;
    private boolean modify;

    public CountryExcelRead(String countryCode, String countryNameKr, String countryNameEn, String available, String modify) {
        this.countryCode = countryCode;
        this.countryNameKr = countryNameKr;
        this.countryNameEn = countryNameEn;
        this.available = available != null && "T".equals(available);
        this.modify = modify != null && "T".equals(modify);
    }
}
