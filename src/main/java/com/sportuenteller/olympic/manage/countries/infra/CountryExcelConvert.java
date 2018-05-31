package com.sportuenteller.olympic.manage.countries.infra;

import com.sportuenteller.olympic.common.convert.ExcelConvert;
import com.sportuenteller.olympic.common.excel.country.CountryExcelRead;
import com.sportuenteller.olympic.common.excel.country.PoiCountryExcelConvert;
import com.sportuenteller.olympic.common.excel.country.WriteCountryExcelRequest;
import com.sportuenteller.olympic.common.model.Name;
import com.sportuenteller.olympic.manage.countries.application.convert.ReadCountryExcel;
import com.sportuenteller.olympic.manage.countries.application.convert.WriteCountryExcel;
import com.sportuenteller.olympic.manage.countries.domain.Country;
import com.sportuenteller.olympic.manage.countries.domain.CountryId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class CountryExcelConvert extends ExcelConvert<Country, CountryExcelRead, WriteCountryExcelRequest> implements ReadCountryExcel, WriteCountryExcel{

    @Autowired
    PoiCountryExcelConvert convert;

    @Override
    protected List<CountryExcelRead> convert(List<CountryExcelRead> excelDataList) {
        return excelDataList;
    }

    @Override
    protected List<CountryExcelRead> readExcel(File excel) {
        return convert.read(excel);
    }
    @Override
    protected File writeExcel(List<WriteCountryExcelRequest> requests) {
        return convert.write(requests);
    }
    @Override
    protected boolean checkModify(CountryExcelRead excelData) {
        return  excelData.isModify();
    }

    @Override
    protected Country findDomain(List<Country> countries, CountryExcelRead excelData) {
        return countries.stream().filter(f -> f.getId().equals(new CountryId(excelData.getCountryCode()))).findFirst().orElse(null);
    }

    @Override
    protected void modify(Country originalCountry, CountryExcelRead excelData) {
        originalCountry.modifyCountry(new Name(excelData.getCountryNameKr(), excelData.getCountryNameEn())
                , excelData.isAvailable());
    }

    @Override
    protected Country createDomain(CountryExcelRead excelData) {
        return new Country(new CountryId(excelData.getCountryCode())
                , new Name(excelData.getCountryNameKr(), excelData.getCountryNameEn())
                , excelData.isAvailable());
    }
    @Override
    protected List<WriteCountryExcelRequest> convert(Country country) {
        List<WriteCountryExcelRequest> list = new ArrayList<>();
        list.add(new WriteCountryExcelRequest(country.getId().getValue()
                , country.getName().getNameKr()
                , country.getName().getNameEn()
                , country.getAvailable()));
        return list;
    }

}
