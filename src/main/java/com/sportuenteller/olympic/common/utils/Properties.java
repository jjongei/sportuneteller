package com.sportuenteller.olympic.common.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Data
@Component
public class Properties {

    @Value("${edms.temp}")
    private String edmsTemp;
    @Value("${excel.country}")
    private String countryExcelTemplate;
    @Value("${excel.game}")
    private String gameExcelTemplate;
    @Value("${excel.plyaer}")
    private String playerExcelTemplate;
    @Value("${point}")
    private Long point;
}
