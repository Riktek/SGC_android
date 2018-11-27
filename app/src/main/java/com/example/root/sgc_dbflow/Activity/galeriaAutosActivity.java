package com.example.root.sgc_dbflow.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.root.sgc_dbflow.Assets.DataManager;
import com.example.root.sgc_dbflow.Assets.ImageAdapter;
import com.example.root.sgc_dbflow.Assets.RecyclerViewOnItemClickListener;
import com.example.root.sgc_dbflow.Entity.autoescala;
import com.example.root.sgc_dbflow.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class galeriaAutosActivity extends AppCompatActivity {

    private int id_coleccion;
    private int tipo_imagen;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private List<autoescala> imagenList = new ArrayList<>();
    private ImageAdapter imageAdapter;
    private TextView txtTitulo;
    private ImageButton ibtnLista, ibtnAgregar;
    private Button btnTodas, btnSalir;

    @Override
    protected void onStart(){
        super.onStart();

        setContentView(R.layout.activity_galeria_autos);

        Runtime.getRuntime().gc();

        ibtnLista =  findViewById(R.id.ibtnLista);
        ibtnAgregar =  findViewById(R.id.ibtnAgregar);
        btnSalir =  findViewById(R.id.btnSalir);

        imageAdapter = new ImageAdapter(this, imagenList, recyclerViewOnItemClickListener);

        Picasso.with(this).setLoggingEnabled(true);


        ibtnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(galeriaAutosActivity.this, listaAutosActivity.class);
                startActivity(intent);
                finish();

            }

        });

        ibtnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(galeriaAutosActivity.this, nuevoAutoActivity.class);
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

        inicializarRecicler();

        CargarListaImagenes();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void inicializarRecicler() {

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.imageGallery);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new GridLayoutManager(getApplicationContext(), 2);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        recycler.setAdapter(imageAdapter);

        recycler.setItemAnimator(new DefaultItemAnimator());

    }

    public void CargarListaImagenes(){

        imagenList = autoescala.getListFotosByColeccion(DataManager.ID_COLECCION);
        imageAdapter.addImagen(imagenList);

        recycler.setAdapter(imageAdapter);
    }

    RecyclerViewOnItemClickListener recyclerViewOnItemClickListener = new RecyclerViewOnItemClickListener() {
        @Override
        public void onClick(View v, int position) {
            Log.d("test", "test");
        }
    };
}
