package com.example.root.sgc_dbflow.Entity;

import com.example.root.sgc_dbflow.Database.dataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = dataBase.class) //Esta linea es la que permite al DBFlow generar las tablas
public class album extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    private int albumid;
    @Column
    private boolean tapadura;
    @Column
    private int normalmin;
    @Column
    private int normalmax;
    @Column
    private String especialprefijo;
    @Column
    private int especialmin;
    @Column
    private int especialmax;
    @Column
    private int coleccion;



    public static album getByColeccion(int idColeccion){

        album tmp;

        tmp = SQLite.select()
                .from(album.class).where(album_Table.coleccion.eq(idColeccion))
                .querySingle();

        if (tmp != null)
            return tmp;
        else
            return new album();

    }

    public int getAlbumid() {
        return albumid;
    }

    public void setAlbumid(int albumid) {
        this.albumid = albumid;
    }

    public boolean isTapadura() {
        return tapadura;
    }

    public void setTapadura(boolean tapadura) {
        this.tapadura = tapadura;
    }

    public int getNormalmin() {
        return normalmin;
    }

    public void setNormalmin(int normalmin) {
        this.normalmin = normalmin;
    }

    public int getNormalmax() {
        return normalmax;
    }

    public void setNormalmax(int normalmax) {
        this.normalmax = normalmax;
    }

    public String getEspecialprefijo() {
        return especialprefijo;
    }

    public void setEspecialprefijo(String especialprefijo) {
        this.especialprefijo = especialprefijo;
    }

    public int getEspecialmin() {
        return especialmin;
    }

    public void setEspecialmin(int especialmin) {
        this.especialmin = especialmin;
    }

    public int getEspecialmax() {
        return especialmax;
    }

    public void setEspecialmax(int especialmax) {
        this.especialmax = especialmax;
    }

    public int getColeccion() {
        return coleccion;
    }

    public void setColeccion(int coleccion) {
        this.coleccion = coleccion;
    }
}
