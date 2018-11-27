package com.example.root.sgc_dbflow.Entity;

import com.example.root.sgc_dbflow.Database.dataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;
import java.util.List;


@Table(database = dataBase.class) //Esta linea es la que permite al DBFlow generar las tablas
public class coleccion extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    private int coleccionid;
    @Column
    private String nombre;
    @Column
    private Date creacion;
    @Column
    private boolean completado;
    @Column
    private boolean activo;
    @Column
    private int usuario;
    @Column
    private int tipo;


    public static List<coleccion> listaByUsuario(int idUsuario){

        List<coleccion> tmp;

        tmp = SQLite.select()
                .from(coleccion.class).where(coleccion_Table.usuario.eq(idUsuario))
                .queryList();

        return tmp;

    }

    public static coleccion getById(int id){

        coleccion tmp;

        tmp = SQLite.select()
                .from(coleccion.class).where(coleccion_Table.coleccionid.eq(id))
                .querySingle();

        return tmp;

    }

    public int getColeccionid() {
        return coleccionid;
    }

    public void setColeccionid(int coleccionid) {
        this.coleccionid = coleccionid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
