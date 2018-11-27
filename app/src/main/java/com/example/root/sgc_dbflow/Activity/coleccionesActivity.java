package com.example.root.sgc_dbflow.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.root.sgc_dbflow.Assets.Cookie;
import com.example.root.sgc_dbflow.Assets.CustomAarrayAdapter;
import com.example.root.sgc_dbflow.Assets.DataManager;
import com.example.root.sgc_dbflow.Entity.coleccion;
import com.example.root.sgc_dbflow.R;

import java.util.List;

public class coleccionesActivity extends AppCompatActivity {

    private ImageButton ibtnBuscar, ibtnAgregar;
    private ListView lvColecciones;
    private Button btnTodas, btnSalir;
    private List<coleccion> coleccionList;
    private CustomAarrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colecciones);

        coleccionList = coleccion.listaByUsuario(DataManager.ID_USUARIO);

        //referencias
        ibtnBuscar =  findViewById(R.id.ibtnBuscar);
        ibtnAgregar =  findViewById(R.id.ibtnAgregar);
        btnSalir =  findViewById(R.id.btnSalir);
        lvColecciones =  findViewById(R.id.lvColecciones);
        btnTodas = findViewById(R.id.btnTodas);

        adapter = new CustomAarrayAdapter(coleccionesActivity.this,coleccionList);


        lvColecciones.setAdapter(adapter);
        // set the custom adapter to listview

        ibtnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });

        btnTodas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                coleccionList = coleccion.listaByUsuario(DataManager.ID_USUARIO);
                adapter.clear();
                adapter = new CustomAarrayAdapter(coleccionesActivity.this,coleccionList);
                lvColecciones.setAdapter(adapter);
            }
        });

        lvColecciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DataManager.ID_COLECCION = coleccionList.get(position).getColeccionid();
                Intent intent;

                int tipoColeccion = coleccionList.get(position).getTipo();

                if (tipoColeccion == 1){
                    intent = new Intent(coleccionesActivity.this, nuevoItemAlbumActivity.class );
                }else{
                    intent = new Intent(coleccionesActivity.this, listaAutosActivity.class );
                }

                startActivity(intent);



            }
        });

        ibtnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cli="", pas="";
                int id = 0;
                int estado=2; //0 no existe, 1 clave mala, 2 ok

                //estado = BaseDatos.buscarUsuario(etUser.getText().toString(), etPass.getText().toString());
                if(estado != 0){
                    if (estado == 1) {
                        Toast.makeText(coleccionesActivity.this, "Clave incorrecta", Toast.LENGTH_SHORT).show();

                    } else {
                        //generar Cookie
                        Cookie.set(id);
                        id= 1;
                        Intent intent = new Intent( coleccionesActivity.this, coleccionNuevaActivity.class);
                        intent.putExtra("id", id);
                        startActivityForResult(intent, 0);
                    }

                } else {
                    //implementar

                }

            }
        });

        ibtnBuscar.setVisibility(View.INVISIBLE);

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }
}
