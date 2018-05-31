package com.sportuenteller.olympic.manage.countries.application.convert;

import com.sportuenteller.olympic.manage.countries.domain.Country;

import java.io.File;
import java.util.List;

public interface WriteCountryExcel {
    File write(List<Country> countries);
}
