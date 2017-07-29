package com.example.jorgepc.fotoceramica.adaptador;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jorgepc.fotoceramica.R;
import com.example.jorgepc.fotoceramica.bean.ProductoBeanVendidos;

import java.util.ArrayList;

import me.grantland.widget.AutofitTextView;

/**
 * Created by JORGEPC on 22/03/2017.
 */

public class ProductosVendidosAdaptador extends RecyclerView.Adapter<ProductosVendidosAdaptador.ViewHolder>{

    private Context context;
    private ArrayList<ProductoBeanVendidos> productoBeen;

    public ProductosVendidosAdaptador(Context context, ArrayList<ProductoBeanVendidos> productoBeen){

                this.context = context;
                this.productoBeen = productoBeen;

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_productosvendido_personalizado,parent,false);

        context = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        int id;
        final int i = position;

        id = context.getResources().getIdentifier(productoBeen.get(position).getImg(),"drawable",context.getPackageName());
        holder.imgProducto.setImageResource(id);

        holder.tipo.setText(productoBeen.get(position).getTipo());
        holder.precio.setText(productoBeen.get(position).getPrecio());
        holder.descr.setText(productoBeen.get(position).getDescr());

        holder.ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.alertdialog_verimagen, null, false);

                ImageView imageView = (ImageView) view.findViewById(R.id.imgAlerta);

                int id2 = context.getResources().getIdentifier(productoBeen.get(i).getImgalert(), "drawable", context.getPackageName());
                imageView.setImageResource(id2);

                builder.setView(view);

                builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });

                AlertDialog d = builder.create();
                d.show();


            }
        });

    }

    @Override
    public int getItemCount() {
        return productoBeen.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProducto;
        AutofitTextView descr, precio, tipo;
        Button ver;

        public ViewHolder(View itemView){
            super(itemView);

            imgProducto = (ImageView) itemView.findViewById(R.id.imgProducto);
            descr = (AutofitTextView) itemView.findViewById(R.id.txtDescripcionProducto);
            precio = (AutofitTextView) itemView.findViewById(R.id.txtPrecio);
            tipo = (AutofitTextView) itemView.findViewById(R.id.txtTipo);
            ver = (Button) itemView.findViewById(R.id.btnVerImagen);


        }
    }
}
