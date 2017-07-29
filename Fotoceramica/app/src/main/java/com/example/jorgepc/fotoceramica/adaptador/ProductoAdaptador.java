package com.example.jorgepc.fotoceramica.adaptador;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jorgepc.fotoceramica.R;
import com.example.jorgepc.fotoceramica.bean.ProductosBean;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import me.grantland.widget.AutofitTextView;

/**
 * Created by JORGEPC on 23/03/2017.
 */

public class ProductoAdaptador extends RecyclerView.Adapter<ProductoAdaptador.ViewHolder>{

    private Context context;
    private ArrayList<ProductosBean> productos;

    public ProductoAdaptador(Context context, ArrayList<ProductosBean> productos){

                this.context = context;
                this.productos = productos;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_productos_personalizado,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        final int i = position;

        holder.progressBar.setVisibility(View.VISIBLE);
        Picasso.with(context).load(productos.get(position).getImgnormal()).
                error(R.drawable.icono).into(holder.imgProducto, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });

        holder.txtDescr.setText(productos.get(position).getDescripcion());
        holder.precio.setText(productos.get(position).getPrecio());

        holder.verImagen.setClickable(true);
        holder.verImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                View view = LayoutInflater.from(context).inflate(R.layout.alertdialog_verimagen,null,false);

                ImageView img = (ImageView) view.findViewById(R.id.imgAlerta);


                //img.setBackgroundResource(R.drawable.taza3);

                Picasso.with(context).load(productos.get(i).getImgalerta()).error(R.drawable.icono).into(img);

                builder.setView(view);

                builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }

    @Override
    public int getItemCount(){
        return productos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProducto;
        AutofitTextView txtDescr, precio;
        Button verImagen;
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);

            imgProducto = (ImageView) itemView.findViewById(R.id.imgProducto);
            txtDescr = (AutofitTextView) itemView.findViewById(R.id.txtDescripcionProducto);
            verImagen = (Button) itemView.findViewById(R.id.btnVerImagen);
            precio = (AutofitTextView) itemView.findViewById(R.id.txtPrecio);
            progressBar = (ProgressBar) itemView.findViewById(R.id.cargandoProducto);

        }
    }


}
