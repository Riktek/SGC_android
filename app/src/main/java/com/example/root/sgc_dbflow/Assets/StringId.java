package com.example.root.sgc_dbflow.Assets;

public class StringId {

    private String value;
    private int id;

    public StringId(String value, int id){

        this.value = value;
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return value;
    }



}
