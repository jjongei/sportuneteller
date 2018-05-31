package com.sportuenteller.olympic.common.code;

public enum StatusType {
    possible(1, "Possible")
    ,wait(2, "Wait")
    ,termination(3, "Terminated");

    private int value;
    private String subject;

    private StatusType(int value, String subject){
        this.value = value;
        this.subject = subject;
    }

    public int getValue() {
        return value;
    }

    public String getSubject() {
        return subject;
    }

    public static StatusType convert(int value){
        for(StatusType type : StatusType.values()){
            if(type.value == value){
                return type;
            }
        }
        return null;
    }

    public static StatusType convert(String name){
        for(StatusType type : StatusType.values()){
            if(type.name().equals(name)){
                return type;
            }
        }
        return null;
    }
}
