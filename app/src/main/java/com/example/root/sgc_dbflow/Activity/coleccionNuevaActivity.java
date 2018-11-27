package com.example.root.sgc_dbflow.Activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.root.sgc_dbflow.Assets.DataManager;
import com.example.root.sgc_dbflow.Assets.StringId;
import com.example.root.sgc_dbflow.Entity.album;
import com.example.root.sgc_dbflow.Entity.coleccion;
import com.example.root.sgc_dbflow.Entity.tipo;
import com.example.root.sgc_dbflow.R;

import java.util.List;

public class coleccionNuevaActivity extends AppCompatActivity {

    private TextView txt1;
    private TextView txt2;
    private TextView txt3;

    private TextInputLayout lyTxt1;
    private TextInputLayout lyTxt2;
    private TextInputLayout lyTxt3;
    private TextInputLayout lyTxt4;

    private Button btnAgregar;
    private Button btnCancelar;
    private EditText editNombre;
    private Spinner spTipo;

    private EditText editInicio;
    private EditText editFin;
    private EditText editInicioEsp;
    private EditText editFinEsp;

    private EditText editPrefijo;

    private CheckBox checkTapa;

    private String strTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coleccion_nueva);

        //Inicio Views que deben hacerse invisibles;
        txt1 = findViewById(R.id.textView5);
        txt2 = findViewById(R.id.textView6);
        txt3 = findViewById(R.id.textView7);

        lyTxt1 = findViewById(R.id.textInputLayout);
        lyTxt2 = findViewById(R.id.textInputLayout2);
        lyTxt3 = findViewById(R.id.textInputLayout3);
        lyTxt4 = findViewById(R.id.textInputLayout4);

        //Fin Views que deben hacerse invisibles;

        btnAgregar = findViewById(R.id.button2);
        btnCancelar = findViewById(R.id.button3);

        editNombre = findViewById(R.id.editNombre);
        spTipo = findViewById(R.id.spTipo);

        editInicio = findViewById(R.id.editInicio);
        editFin = findViewById(R.id.editFin);
        editInicioEsp = findViewById(R.id.editInicioEsp);
        editFinEsp = findViewById(R.id.editFinEsp);

        editPrefijo = findViewById(R.id.editText2);
        checkTapa = findViewById(R.id.checkBox);

        cargarSpinner();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarAuto();
                finish();
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        spTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                strTipo = ((StringId)spTipo.getItemAtPosition
                        (spTipo.getSelectedItemPosition())).getValue();

                if (strTipo.equals("Album")){

                    txt1.setVisibility(View.VISIBLE);
                    txt2.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    lyTxt1.setVisibility(View.VISIBLE);
                    lyTxt2.setVisibility(View.VISIBLE);
                    lyTxt3.setVisibility(View.VISIBLE);
                    lyTxt4.setVisibility(View.VISIBLE);
                    editPrefijo.setVisibility(View.VISIBLE);
                    checkTapa.setVisibility(View.VISIBLE);

                }else {

                    txt1.setVisibility(View.INVISIBLE);
                    txt2.setVisibility(View.INVISIBLE);
                    txt3.setVisibility(View.INVISIBLE);
                    lyTxt1.setVisibility(View.INVISIBLE);
                    lyTxt2.setVisibility(View.INVISIBLE);
                    lyTxt3.setVisibility(View.INVISIBLE);
                    lyTxt4.setVisibility(View.INVISIBLE);
                    editPrefijo.setVisibility(View.INVISIBLE);
                    checkTapa.setVisibility(View.INVISIBLE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //no es necesario setear este segmento
            }
        });



    }

    private void guardarAuto(){

        coleccion tmp = new coleccion();

        tmp.setNombre(editNombre.getText().toString());
        tmp.setUsuario(DataManager.ID_USUARIO);
        tmp.setTipo((((StringId)spTipo.getItemAtPosition
                (spTipo.getSelectedItemPosition())).getId()));
        tmp.save();

        if (strTipo.equals("Album")){

            guardarAlbum(tmp);
        }

    }

    private void guardarAlbum(coleccion coleccion){

        album tmp = new album();

        tmp.setColeccion(coleccion.getColeccionid());
        tmp.setNormalmin(Integer.parseInt(editInicio.getText().toString()));
        tmp.setNormalmax(Integer.parseInt(editFin.getText().toString()));
        tmp.setEspecialmin(Integer.parseInt(editInicioEsp.getText().toString()));
        tmp.setEspecialmax(Integer.parseInt(editFinEsp.getText().toString()));
        tmp.setEspecialprefijo(editPrefijo.getText().toString());
        tmp.setTapadura(checkTapa.isChecked());
        tmp.save();

    }

    private void cargarSpinner (){

        List<StringId> lst_tipo = tipo.GetAll();

        ArrayAdapter<StringId> adp_tipo = new ArrayAdapter<StringId>(this,
                android.R.layout.simple_spinner_item, lst_tipo);

        adp_tipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipo.setAdapter(adp_tipo);


    }
}
