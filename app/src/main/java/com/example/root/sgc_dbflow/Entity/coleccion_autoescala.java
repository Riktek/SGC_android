package com.example.root.sgc_dbflow.Entity;

import com.example.root.sgc_dbflow.Database.dataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


@Table(database = dataBase.class) //Esta linea es la que permite al DBFlow generar las tablas
public class coleccion_autoescala extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    private int id;

    @Column
    private int id_auto;

    @Column
    private int id_coleccion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_auto() {
        return id_auto;
    }

    public void setId_auto(int id_auto) {
        this.id_auto = id_auto;
    }

    public int getId_coleccion() {
        return id_coleccion;
    }

    public void setId_coleccion(int id_coleccion) {
        this.id_coleccion = id_coleccion;
    }
}
