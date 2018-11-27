package com.example.root.sgc_dbflow.Assets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.sgc_dbflow.Entity.lamina;
import com.example.root.sgc_dbflow.R;

import java.util.List;


public class CustomAarrayAdapterEspecial extends ArrayAdapter {


    private List<String> coleccionList;
    private int id_album;
    public CustomAarrayAdapterEspecial(Context context, List<String> list, int id_album)
    {
        super(context,0,list);
        coleccionList = list;
        this.id_album = id_album;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        ImageView checkOff;
        ImageView checkOn;

        int numero = Integer.parseInt(coleccionList.get(position));

        lamina tmp = lamina.getEspecialByNumero(numero, id_album);

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_normal,parent,false);
// inflate custom layout called row
            holder = new ViewHolder();
            holder.tv =(TextView) convertView.findViewById(R.id.textView1);
            holder.checkOn = convertView.findViewById(R.id.imageView4);
            holder.checkOff = convertView.findViewById(R.id.imageView3);
// initialize textview
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tv.setText("Item Especial " + coleccionList.get(position));
        // set the name to the text;

        if (tmp.getLaminaid() == 0){

            holder.checkOff.setVisibility(View.VISIBLE);
            holder.checkOn.setVisibility(View.INVISIBLE);

        }else {
            holder.checkOff.setVisibility(View.INVISIBLE);
            holder.checkOn.setVisibility(View.VISIBLE);
        }



        return convertView;

    }

    static class ViewHolder
    {

        TextView tv;
        ImageView checkOn;
        ImageView checkOff;
    }

}
