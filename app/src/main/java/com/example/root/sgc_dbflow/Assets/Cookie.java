package com.example.root.sgc_dbflow.Assets;

public final class Cookie {
    private static int id;

    public Cookie() {
    }

    public Cookie(int id) {
        this.id = id;
    }

    public static int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void set(int idp){

    }

    public static void delete(){

    }

    public static boolean isValid(){
        return false;
    }
}
