package com.sportuenteller.olympic.common.code;

public class Item {
    private String key;
    private long id;
    private String code;
    private String value;
    private String desc;
    private String subject;
    private boolean flag;


    public Item(long id, String value){
        this.id = id;
        this.value = value;
        this.key = Long.toString(id);
    }
    public Item(long id, String value, String subject, boolean flag){
        this.id = id;
        this.value = value;
        this.subject = subject;
        this.flag = flag;
        this.key = Long.toString(id);
    }
    public Item(long id, String value, String subject, String desc, boolean flag){
        this.id = id;
        this.value = value;
        this.subject = subject;
        this.desc = desc;
        this.flag = flag;
        this.key = Long.toString(id);
    }
    public Item(String code, String value){
        this.code = code;
        this.value = value;
        this.key = code;
    }
    public Item(String code, String value, String subject){
        this.code = code;
        this.value = value;
        this.subject = subject;
        this.key = code;
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getValue(){
        return this.value;
    }

    public String getKey(){
        return this.key;
    }

    public String getSubject(){return this.subject;}

    public boolean isFlag(){
        return this.flag;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return key != null ? key.equals(item.key) : item.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }
}
