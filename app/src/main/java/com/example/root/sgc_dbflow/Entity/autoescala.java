package com.example.root.sgc_dbflow.Entity;

import com.example.root.sgc_dbflow.Database.dataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.ArrayList;
import java.util.List;


@Table(database = dataBase.class) //Esta linea es la que permite al DBFlow generar las tablas
public class autoescala extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    private int autoescalaid;
    @Column
    private String escala;
    @Column
    private String modelo;
    @Column
    private String fabricante;
    @Column
    private String color;
    @Column
    private int anio;
    @Column
    private String foto;
    @Column
    private boolean faltante;
    @Column
    private String empaque;
    @Column
    private int id_coleccion;


    public static List<autoescala> getListByColeccion(int id_coleccion){
        List<autoescala> fotosList;

        try{
            fotosList = SQLite.select()
                    .from(autoescala.class)
                    .where(autoescala_Table.id_coleccion.eq(id_coleccion))
                    .queryList();
        }catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        return fotosList;
    }

    public static List<autoescala> getListFotosByColeccion(int id_coleccion){
        List<autoescala> fotosList;

        try{
            fotosList = SQLite.select()
                    .from(autoescala.class)
                    .where(autoescala_Table.id_coleccion.eq(id_coleccion))
                    .and(autoescala_Table.foto.isNot(""))
                    .queryList();
        }catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        return fotosList;
    }

    public static autoescala getByID(int id){
        autoescala tmp;

        tmp = SQLite.select()
                .from(autoescala.class)
                .where(autoescala_Table.autoescalaid.eq(id))
                .querySingle();
        if (tmp != null)
            return tmp;
        else
            return new autoescala();
    }



    public int getAutoescalaid() {
        return autoescalaid;
    }

    public void setAutoescalaid(int autoescalaid) {
        this.autoescalaid = autoescalaid;
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isFaltante() {
        return faltante;
    }

    public void setFaltante(boolean faltante) {
        this.faltante = faltante;
    }

    public String getEmpaque() {
        return empaque;
    }

    public void setEmpaque(String empaque) {
        this.empaque = empaque;
    }

    public int getId_coleccion() {
        return id_coleccion;
    }

    public void setId_coleccion(int id_coleccion) {
        this.id_coleccion = id_coleccion;
    }
}
