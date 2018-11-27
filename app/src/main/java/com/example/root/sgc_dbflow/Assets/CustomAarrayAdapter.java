package com.example.root.sgc_dbflow.Assets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.root.sgc_dbflow.Entity.coleccion;
import com.example.root.sgc_dbflow.R;

import java.util.List;

public class CustomAarrayAdapter extends ArrayAdapter {


    List<coleccion> coleccionList;
    public CustomAarrayAdapter(Context context, List<coleccion> list)
    {
        super(context,0,list);
        coleccionList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
// inflate custom layout called row
            holder = new ViewHolder();
            holder.tv =(TextView) convertView.findViewById(R.id.textView1);
// initialize textview
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        coleccion in = (coleccion) coleccionList.get(position);
        holder.tv.setText(in.getNombre());
        // set the name to the text;

        return convertView;

    }

    static class ViewHolder
    {

        TextView tv;
    }

}
