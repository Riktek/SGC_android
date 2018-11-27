package com.example.root.sgc_dbflow.Entity;

import com.example.root.sgc_dbflow.Database.dataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;



@Table(database = dataBase.class) //Esta linea es la que permite al DBFlow generar las tablas
public class usuario extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    private int usuarioid;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String user;
    @Column
    private String pass;
    @Column
    private boolean activo;

    public usuario() {
    }

    public usuario(String nombre, String apellido, String user, String pass) throws Exception {
        if (nombre.trim().length() == 0 || apellido.trim().length() == 0 || user.trim().length() == 0 || pass.trim().length() == 0) {
            throw new Exception("Ningun campo puede estar vacio");
        }
        if (existeUsuario(user)){
            throw new Exception("El User ya existe");
        }
        this.nombre = nombre.trim();
        this.apellido = apellido.trim();
        this.user = user.trim();
        this.pass = pass.trim();
    }

    public usuario(int usuarioid, String nombre, String apellido, String user, String pass) throws Exception {
        if (nombre.trim().length() == 0 || apellido.trim().length() == 0 || user.trim().length() == 0 || pass.trim().length() == 0) {
            throw new Exception("Ningun campo puede estar vacio");
        }
        if (existeUsuario(user)){
            throw new Exception("El User ya existe");
        }
        this.usuarioid = usuarioid;
        this.nombre = nombre.trim();
        this.apellido = apellido.trim();
        this.user = user.trim();
        this.pass = pass.trim();
    }

    public int getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(int usuarioid) {
        this.usuarioid = usuarioid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString (){
        return nombre + " " + apellido;
    }

    //Ejemplo de metodo de busqueda en tabla usuario por el email
    public static usuario getUsuario(String correo){
        usuario obj;

        obj = SQLite.select()
                .from(usuario.class)
                .where(usuario_Table.user.eq(correo)) //El Usuario_Table lo auto genera el DBFlow
                .querySingle();                          //Si no se ha generado debes hacer un build

        if (obj!=null){
            return obj;
        }else {
            return new usuario();
        }
    }

    public static usuario getUsuarioById(int idparam){
        usuario obj;

        obj = SQLite.select()
                .from(usuario.class)
                .where(usuario_Table.usuarioid.eq(idparam)) //El Usuario_Table lo auto genera el DBFlow
                .querySingle();                          //Si no se ha generado debes hacer un build

        if (obj!=null){
            return obj;
        }else {
            return new usuario();
        }
    }

    public static boolean existeUsuario(String correo) {
        boolean respuesta = false;
        usuario obj;

        obj = SQLite.select()
                .from(usuario.class)
                .where(usuario_Table.user.eq(correo)) //El Usuario_Table lo auto genera el DBFlow
                .querySingle();                          //Si no se ha generado debes hacer un build

        if (obj!=null){
            respuesta = true;
        }

        return respuesta;
    }

}
