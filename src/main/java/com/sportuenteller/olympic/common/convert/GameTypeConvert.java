package com.sportuenteller.olympic.common.convert;

import com.sportuenteller.olympic.common.code.GameType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GameTypeConvert implements AttributeConverter<GameType, String> {
    @Override
    public String convertToDatabaseColumn(GameType attribute) {
        return attribute.getCode();
    }

    @Override
    public GameType convertToEntityAttribute(String dbData) {
        return GameType.convert(dbData);
    }
}
