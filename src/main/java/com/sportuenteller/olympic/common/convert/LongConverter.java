package com.sportuenteller.olympic.common.convert;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LongConverter implements AttributeConverter<Long, Long> {
    @Override
    public Long convertToDatabaseColumn(Long attribute) {
        return attribute != null ? attribute : 0L;
    }

    @Override
    public Long convertToEntityAttribute(Long dbData) {
        return dbData != null ? dbData : 0L;
    }
}
