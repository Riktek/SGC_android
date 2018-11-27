package com.example.root.sgc_dbflow.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.root.sgc_dbflow.Entity.usuario;
import com.example.root.sgc_dbflow.R;

public class registroActivity extends AppCompatActivity {

    private EditText etNombre, etApellido, etUser, etPass;
    private Button btnCancelar, btnRegistrar;
    private TextView lblLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //referencias
        etNombre = findViewById(R.id.etNombres);
        etApellido = findViewById(R.id.etApellidos);
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        lblLogin = findViewById(R.id.lblLogin);

        btnCancelar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Limpiar();
                finish();
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try{
                    usuario obj = new usuario(0, etNombre.getText().toString(), etApellido.getText().toString(), etUser.getText().toString(), etPass.getText().toString());
                    obj.setActivo(true);
                    if (obj.save()) {
                        Aviso("Usuario agregado exitosamente");
                        Limpiar();
                    }
                }catch (Exception ex) {
                    Aviso(ex.getMessage());
                }

            }
        });

        etUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    if (usuario.existeUsuario(etUser.getText().toString())){
                        etUser.setError("Usuario ya existe");
                    }
                }
            }

        });

        lblLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

    }

    private void Limpiar(){
        etNombre.setText("");
        etApellido.setText("");
        etUser.setText("");
        etPass.setText("");
    }

    private void Aviso(String msg){
        AlertDialog alertDialog = new AlertDialog.Builder(registroActivity.this).create();

        alertDialog.setMessage(msg);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alertDialog.show();
    }
}
