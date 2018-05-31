package com.sportuenteller.olympic.manage.countries.application.convert;

import com.sportuenteller.olympic.manage.countries.domain.Country;

import java.io.File;
import java.util.List;

public interface ReadCountryExcel {
    List<Country> read(File excel, List<Country> countries);
}
