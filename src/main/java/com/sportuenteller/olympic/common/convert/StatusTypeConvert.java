package com.sportuenteller.olympic.common.convert;

import com.sportuenteller.olympic.common.code.StatusType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusTypeConvert implements AttributeConverter<StatusType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(StatusType attribute) {
        return attribute.getValue();
    }

    @Override
    public StatusType convertToEntityAttribute(Integer dbData) {
        return StatusType.convert(dbData);
    }
}

