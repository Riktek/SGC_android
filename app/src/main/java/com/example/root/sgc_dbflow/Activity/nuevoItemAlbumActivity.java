package com.example.root.sgc_dbflow.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.root.sgc_dbflow.Assets.CustomAarrayAdapterEspecial;
import com.example.root.sgc_dbflow.Assets.CustomAarrayAdapterNormal;
import com.example.root.sgc_dbflow.Assets.DataManager;
import com.example.root.sgc_dbflow.Entity.album;
import com.example.root.sgc_dbflow.Entity.lamina;
import com.example.root.sgc_dbflow.R;

import java.util.ArrayList;
import java.util.List;

public class nuevoItemAlbumActivity extends AppCompatActivity {

    private List<String> listaNomal = new ArrayList<>();
    private List<String> listaEspecial = new ArrayList<>();
    private album album;
    private ListView lsNormales, lsEspeciales;
    private CustomAarrayAdapterNormal adapterNormal;
    private CustomAarrayAdapterEspecial adapterEspecial;
    private int id_album;
    private Button btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_item_album);

        album = album.getByColeccion(DataManager.ID_COLECCION);
        id_album = album.getAlbumid();

        List<lamina> listLamina = lamina.getListByAlbum(id_album);

        lsNormales = findViewById(R.id.lvNormales);
        lsEspeciales = findViewById(R.id.lvEspeciales);
        btnSalir =  findViewById(R.id.btnSalir);


        int maxNormal = album.getNormalmax();
        int minNormal = album.getNormalmin();
        int maxEspecial = album.getEspecialmax();
        int minEspecial = album.getEspecialmin();

        int countNormal = minNormal;
        int counEspecial = minEspecial;

        int tamListaNomal = maxNormal - minNormal;

        int tamListaEspecial = maxEspecial - minEspecial;

        String prefijo = album.getEspecialprefijo();

        if (tamListaNomal > 0){

            for (int i = 0; i <= tamListaNomal; i++){

                listaNomal.add(String.valueOf(countNormal));
                countNormal++;

            }
            adapterNormal = new CustomAarrayAdapterNormal(nuevoItemAlbumActivity.this, listaNomal, id_album );
        }




        if (tamListaEspecial > 0){
            for (int i = 0; i <= tamListaEspecial; i++){

                listaEspecial.add(String.valueOf(counEspecial));
                counEspecial++;

            }

            adapterEspecial = new CustomAarrayAdapterEspecial(nuevoItemAlbumActivity.this, listaEspecial, id_album );
        }

        if (listaNomal.size()> 0){

            lsNormales.setAdapter(adapterNormal);
        }

        lsNormales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lamina tmp = lamina.getNormalByNumero(Integer.parseInt(listaNomal.get(position)), id_album);
                if (tmp.getNumero()== 0){
                    tmp.setAlbum(id_album);
                    tmp.setEspecial(false);
                    tmp.setNumero(Integer.parseInt(listaNomal.get(position)));
                    tmp.save();
                }else {
                    tmp.delete();
                }

                /*adapterNormal.clear();
                adapterNormal = new CustomAarrayAdapterNormal(nuevoItemAlbumActivity.this, listaNomal, id_album );
                lsNormales.setAdapter(adapterNormal);*/
                adapterNormal.notifyDataSetChanged();

            }
        });

        if (listaEspecial.size()> 0){

            lsEspeciales.setAdapter(adapterEspecial);
        }

        lsEspeciales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lamina tmp = lamina.getEspecialByNumero(Integer.parseInt(listaEspecial.get(position)), id_album);
                if (tmp.getNumero()== 0){
                    tmp.setAlbum(id_album);
                    tmp.setEspecial(true);
                    tmp.setNumero(Integer.parseInt(listaEspecial.get(position)));
                    tmp.save();
                }else {
                    tmp.delete();
                }

                /*adapterNormal.clear();
                adapterNormal = new CustomAarrayAdapterNormal(nuevoItemAlbumActivity.this, listaNomal, id_album );
                lsNormales.setAdapter(adapterNormal);*/
                adapterEspecial.notifyDataSetChanged();

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
