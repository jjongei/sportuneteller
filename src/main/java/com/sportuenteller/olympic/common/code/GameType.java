package com.sportuenteller.olympic.common.code;

import java.util.ArrayList;
import java.util.List;

public enum GameType {
    alpine("0001","Alpine Skiing")
    ,biathlon("0002","Biathlon")
    ,bobsleigh("0003","Bobsleigh")
    ,cross("0004","Cross-Country Skiing")
    ,figure("0005","Figure Skating")
    ,freestyle("0006","Freestyle Skiing")
    ,luge("0007","Luge")
    ,nordic("0008","Nordic Combined")
    ,shorttrack("0009","Short Track")
    ,skeleton("0010","Skeleton")
    ,skijumping("0011","Ski Jumping")
    ,snowboard("0012","Snowboard")
    ,speed("0013","Speed Skating")
    ,curling("0014","Curling")
    ,icehockey("0015","Ice Hockey");

    private String code;
    private String subject;

    private  GameType(String code, String subject){

        this.code = code;
        this.subject = subject;
    }

    public static GameType convert(String code){
        for(GameType type : GameType.values()){
            if(type.code.equals(code)){
                return type;
            }
        }
        return null;
    }

    public static List<Item> items(){
        List<Item> items = new ArrayList<>();

        for(GameType type : GameType.values()){
            items.add(new Item(type.code, type.subject));
        }
        return items;
    }

    public String getCode(){return this.code;}
    public String getSubject() {
        return subject;
    }

}
