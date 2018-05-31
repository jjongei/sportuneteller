package com.sportuenteller.olympic.common.convert;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BooleanConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
       return attribute != null && attribute ? "T" : "F";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return dbData != null && "T".equals(dbData) ? true : false;
    }
}
