package com.sportuenteller.olympic.manage.countries.application.convert;

import com.sportuenteller.olympic.manage.countries.application.helper.FindCountryHelper;
import com.sportuenteller.olympic.manage.countries.domain.Country;
import com.sportuenteller.olympic.manage.countries.domain.CountryRepository;
import com.sportuenteller.olympic.manage.countries.domain.CountrySearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;

@Service
public class ConvertCountryExcelService {
    @Autowired
    CountryRepository repository;
    @Autowired
    FindCountryHelper helper;
    @Autowired
    WriteCountryExcel writeCountryExcel;
    @Autowired
    ReadCountryExcel readCountryExcel;

    @Transactional
    public void upload(File file){
        List<Country> countries = readCountryExcel.read(file, helper.findList(repository));

        for(Country country : countries){
            this.create(country);
        }

    }
    public File download(CountrySearchRequest request){
        return writeCountryExcel.write(helper.findList(request, repository));
    }

    private void create(Country country) {
        repository.save(country);
    }
}
