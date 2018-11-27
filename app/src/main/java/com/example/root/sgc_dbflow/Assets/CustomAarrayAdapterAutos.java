package com.example.root.sgc_dbflow.Assets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.root.sgc_dbflow.Entity.autoescala;
import com.example.root.sgc_dbflow.R;

import java.util.List;

public class CustomAarrayAdapterAutos extends ArrayAdapter {


    List<autoescala> coleccionList;
    public CustomAarrayAdapterAutos(Context context, List<autoescala> list)
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
        autoescala in = (autoescala) coleccionList.get(position);
        holder.tv.setText(in.getModelo());
        // set the name to the text;

        return convertView;

    }

    static class ViewHolder
    {

        TextView tv;
    }

}
