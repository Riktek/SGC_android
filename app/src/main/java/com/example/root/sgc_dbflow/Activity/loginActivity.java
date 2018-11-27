package com.example.root.sgc_dbflow.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.root.sgc_dbflow.Assets.Cookie;
import com.example.root.sgc_dbflow.Assets.DataManager;
import com.example.root.sgc_dbflow.Entity.tipo;
import com.example.root.sgc_dbflow.Entity.usuario;
import com.example.root.sgc_dbflow.R;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.ArrayList;
import java.util.List;

public class loginActivity extends AppCompatActivity {

    private EditText etUser, etPass;
    private Button btnEntrar, btnLimpiar;
    private TextView lblRegistro;

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Se debe Iniciar el DBFlow junto con el primer activity de la aplicación
        //Se inicia con la siguiente Linea
        FlowManager.init(new FlowConfig.Builder(this).build());

        //referencias
        etUser =  findViewById(R.id.etUser);
        etPass =  findViewById(R.id.etPass);
        btnEntrar =  findViewById(R.id.btnEntrar);
        btnLimpiar  = findViewById(R.id.btnLimpiar);
        lblRegistro =  findViewById(R.id.lblRegistro);
        etUser.requestFocus();

        checkTipo();

        //Variables
        int id = 0;

        if (Cookie.isValid()){
            //iniciar activity Colecciones
            //enviar id de cliente.
            id = Cookie.getId();
            Intent intent = new Intent(loginActivity.this, coleccionesActivity.class);
            intent.putExtra("id", id);
            startActivityForResult(intent, 0);
        } else {
            //borrar cookie
            Cookie.delete();
        }

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkPermisos()){
                    usuario user = usuario.getUsuario(etUser.getText().toString());
                    if (user.getNombre() != null){

                        if (user.getUser().equals(etUser.getText().toString()) &&  user.getPass().equals(etPass.getText().toString())){
                            int id = user.getUsuarioid();
                            DataManager.ID_USUARIO = id;
                            //Cookie.set(id); Implementar escritura de Cookie
                            Intent intent = new Intent(loginActivity.this, coleccionesActivity.class);
                            intent.putExtra("id", id);
                            startActivityForResult(intent, 0);

                        }

                    } else {

                        etUser.setError("Usuario o Password no existe");

                    }
                }

            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Limpiar();
            }
        });

        lblRegistro.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(loginActivity.this, registroActivity.class);
                startActivity(intent);
            }
        });

        //Generar "cookie" para tiempo de sesion activa por share preference encriptada.

    }

    private void checkTipo(){

        List<tipo> tipoList = tipo.GetListAll();

        if (tipoList.size()== 0){

            tipo tmp1 = new tipo();
            tipo tmp2 = new tipo();

            tmp1.setNombre("Album");
            tmp1.setActivo(true);
            tmp2.setNombre("Autos a Escala");
            tmp2.setActivo(true);

            tmp1.save();
            tmp2.save();

        }



    }

    private boolean checkPermisos(){

        int almacExtPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        final List<String> listPermissionsNeeded = new ArrayList<>();

        if (almacExtPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            AlertDialog alertDialog = new AlertDialog.Builder(loginActivity.this).create();
            alertDialog.setTitle("Faltan Permisos");
            alertDialog.setMessage("Para que la aplicación funcione en su totalidad necesitamos que apruebes los permisos a continuación");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {


                            ActivityCompat.requestPermissions(loginActivity.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);

                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            return false;
        }
        return true;

    }

    @Override
    protected void onResume() {
        super.onResume();
        Limpiar();
    }

    private void Limpiar (){
        etUser.setText("");
        etPass.setText("");
        etUser.requestFocus();
    }
}
