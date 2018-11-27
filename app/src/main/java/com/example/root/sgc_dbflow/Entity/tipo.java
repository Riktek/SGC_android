package com.example.root.sgc_dbflow.Entity;

import com.example.root.sgc_dbflow.Assets.StringId;
import com.example.root.sgc_dbflow.Database.dataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.ArrayList;
import java.util.List;


@Table(database = dataBase.class) //Esta linea es la que permite al DBFlow generar las tablas
public class tipo extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    private int tipoid;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private boolean activo;


    public static List<StringId> GetAll(){

        List<StringId> lst = new ArrayList<StringId>();
        List<tipo> tipo;

        tipo = SQLite.select()
                .from(com.example.root.sgc_dbflow.Entity.tipo.class)
                .queryList();

        for(com.example.root.sgc_dbflow.Entity.tipo r : tipo){
            lst.add(new StringId(r.getNombre(), r.getTipoid()));
        }

        return lst;
    }

    public static List<tipo> GetListAll(){

        List<tipo> tipo;

        tipo = SQLite.select()
                .from(com.example.root.sgc_dbflow.Entity.tipo.class)
                .queryList();


        return tipo;
    }

    public static tipo tipoById(int tipoid){

        tipo tmp;

        tmp = SQLite.select()
                .from(tipo.class).where(tipo_Table.tipoid.eq(tipoid))
                .querySingle();

        if (tmp != null){
            return tmp;
        }else {
            return new tipo();
        }

    }

    public int getTipoid() {
        return tipoid;
    }

    public void setTipoid(int tipoid) {
        this.tipoid = tipoid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString(){
        return nombre;
    }


}
