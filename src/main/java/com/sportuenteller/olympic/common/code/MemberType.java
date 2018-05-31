package com.sportuenteller.olympic.common.code;

public enum MemberType {
    general, facebook, google, twitter;

    public static MemberType convert(String name){
        for(MemberType type : MemberType.values()){
            if(type.name().equals(name)){
                return type;
            }
        }
        return null;
    }
}
