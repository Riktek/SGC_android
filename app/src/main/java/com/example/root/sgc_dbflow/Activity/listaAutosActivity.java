package com.example.root.sgc_dbflow.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.root.sgc_dbflow.Assets.CustomAarrayAdapterAutos;
import com.example.root.sgc_dbflow.Assets.DataManager;
import com.example.root.sgc_dbflow.Entity.autoescala;
import com.example.root.sgc_dbflow.Entity.coleccion;
import com.example.root.sgc_dbflow.R;

import java.util.List;

public class listaAutosActivity extends AppCompatActivity {

    private ImageButton ibtnGaleria, ibtnAgregar;
    private ListView lvColecciones;
    private Button btnTodas, btnSalir;
    private List<autoescala> autosList;
    private TextView lblTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_autos);

        autosList = autoescala.getListByColeccion(DataManager.ID_COLECCION);

        coleccion tmp = coleccion.getById(DataManager.ID_COLECCION);

        //referencias
        ibtnGaleria =  findViewById(R.id.ibtnGaleria);
        ibtnAgregar =  findViewById(R.id.ibtnAgregar);
        btnSalir =  findViewById(R.id.btnSalir);
        lvColecciones =  findViewById(R.id.lvColecciones);
        lblTitulo = findViewById(R.id.lblTitulo);

        lblTitulo.setText(tmp.getNombre());


        lvColecciones.setAdapter(new CustomAarrayAdapterAutos(listaAutosActivity.this,autosList));
        // set the custom adapter to listview

        lvColecciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                autoescala tmp =(autoescala) lvColecciones.getItemAtPosition(position);

                Intent intent = new Intent(listaAutosActivity.this, nuevoAutoActivity.class);
                intent.putExtra("ID", tmp.getAutoescalaid());
                startActivity(intent);
                finish();

            }
        });

        ibtnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(listaAutosActivity.this, galeriaAutosActivity.class);
                startActivity(intent);
                finish();

            }

        });

        ibtnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(listaAutosActivity.this, nuevoAutoActivity.class);
                startActivity(intent);
                finish();


            }
        });


        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }
}
