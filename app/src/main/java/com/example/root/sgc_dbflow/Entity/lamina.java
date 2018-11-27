package com.example.root.sgc_dbflow.Entity;

import com.example.root.sgc_dbflow.Database.dataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;


@Table(database = dataBase.class) //Esta linea es la que permite al DBFlow generar las tablas
public class lamina extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    private int laminaid;
    @Column
    private int numero;
    @Column
    private boolean especial;
    @Column
    private int album;


    public static List<lamina> getListByAlbum(int idAlbum){

        List<lamina> tmp;

        tmp = SQLite.select()
                .from(lamina.class).where(lamina_Table.album.eq(idAlbum))
                .queryList();

        return tmp;

    }

    public static lamina getNormalByNumero(int num, int id_album){

        lamina tmp;

        tmp = SQLite.select()
                .from(lamina.class).where(lamina_Table.numero.eq(num))
                .and(lamina_Table.album.eq(id_album))
                .and(lamina_Table.especial.eq(false))
                .querySingle();

        if (tmp != null)
            return tmp;
        else
            return new lamina();

    }

    public static lamina getEspecialByNumero(int num, int id_album){

        lamina tmp;

        tmp = SQLite.select()
                .from(lamina.class).where(lamina_Table.numero.eq(num))
                .and(lamina_Table.album.eq(id_album))
                .and(lamina_Table.especial.eq(true))
                .querySingle();

        if (tmp != null)
            return tmp;
        else
            return new lamina();

    }

    public int getLaminaid() {
        return laminaid;
    }

    public void setLaminaid(int laminaid) {
        this.laminaid = laminaid;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isEspecial() {
        return especial;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    public int getAlbum() {
        return album;
    }

    public void setAlbum(int album) {
        this.album = album;
    }
}
