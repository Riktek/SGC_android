package com.example.root.sgc_dbflow.Assets;

import android.content.ContentResolver;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.sgc_dbflow.Entity.autoescala;
import com.example.root.sgc_dbflow.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by hpalacios on 23/01/2018.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {


    private List<autoescala> fotoitemList = new ArrayList<>();
    private Context context;

    ContentResolver contentResolver;

    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;


    public ImageAdapter(Context context, List<autoescala> items,
                        RecyclerViewOnItemClickListener recyclerViewOnItemClickListener){

        this.context     = context;
        this.fotoitemList = items;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;

    }

    class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        protected ImageView img;
        protected TextView comentario;

        public ImageViewHolder(View view) {
            super(view);
            this.img = (ImageView) view.findViewById(R.id.img);
            this.comentario = view.findViewById(R.id.txComentario);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            recyclerViewOnItemClickListener.onClick(view,getAdapterPosition());

        }
    }



    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_image, viewGroup, false);
        contentResolver = view.getContext().getContentResolver();
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder viewHolder, int i) {

        if(fotoitemList.size()>0) {

            autoescala _foto_item = fotoitemList.get(i);

            //String path = _foto_item.getUriString();
            String path = _foto_item.getFoto();

            ImageView img = (ImageView) viewHolder.img;
            TextView comentario = viewHolder.comentario;

            comentario.setText(_foto_item.getModelo());

            if (path != "")
            Picasso.with(context).load(path).resize(150, 150)
                    .centerCrop().into(img);
        }
    }

    @Override
    public int getItemCount() {
        return fotoitemList.size();
    }


    public void addImagen(List<autoescala> imagenList){
        fotoitemList.clear();
        fotoitemList = imagenList;
        notifyDataSetChanged();
    }



}
