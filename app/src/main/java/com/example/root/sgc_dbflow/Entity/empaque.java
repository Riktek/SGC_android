package com.example.root.sgc_dbflow.Entity;

import com.example.root.sgc_dbflow.Database.dataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


@Table(database = dataBase.class) //Esta linea es la que permite al DBFlow generar las tablas
public class empaque extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    private int empaqueid;
    @Column
    private String nombre;
    @Column
    private boolean activo;

    public int getEmpaqueid() {
        return empaqueid;
    }

    public void setEmpaqueid(int empaqueid) {
        this.empaqueid = empaqueid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
