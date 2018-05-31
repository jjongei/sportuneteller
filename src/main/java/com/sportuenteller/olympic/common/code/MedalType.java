package com.sportuenteller.olympic.common.code;

public enum MedalType {
    gold, silver, bronze, none;

    public static MedalType convert(String name){
        for(MedalType type : MedalType.values()){
            if(type.name().equals(name)){
                return type;
            }
        }
        return null;
    }
}
