package com.example.jorgepc.fotoceramica.adaptador;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jorgepc.fotoceramica.R;
import com.example.jorgepc.fotoceramica.bean.VideosBean;
import com.example.jorgepc.fotoceramica.youtubekey.YoutubeApiKey;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by JORGEPC on 24/03/2017.
 */

public class VideosAdaptador extends RecyclerView.Adapter<VideosAdaptador.ViewHolder>{

    private Activity activity;
    private ArrayList<VideosBean> videos;
    private Context context;

    public VideosAdaptador(Activity activity, ArrayList<VideosBean> videos){

                this.activity = activity;
                this.videos = videos;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_videos_y_testimonios, parent, false);

        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final int i = position;

        holder.play.setVisibility(View.INVISIBLE);
        holder.cargando.setVisibility(View.VISIBLE);

        Picasso.with(context).load("http://img.youtube.com/vi/"+videos.get(position).getImg()+"/mqdefault.jpg")
                .error(R.drawable.icono).into(holder.img, new Callback() {
            @Override
            public void onSuccess() {

                holder.play.setVisibility(View.VISIBLE);
                holder.cargando.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onError() {

            }
        });


        holder.descr.setText(videos.get(i).getDescripcion());

        holder.play.setClickable(true);
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if(YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(context).equals(YouTubeInitializationResult.SUCCESS)){

                    Intent intent = YouTubeStandalonePlayer.createVideoIntent(activity, YoutubeApiKey.API_KEY,videos.get(i).getImg(),0,true,true);
                    activity.startActivity(intent);

                }else{

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Testimonio de " + videos.get(i).getDescripcion())
                            .setMessage("Hemos notado que no tienes instalado la aplicación de Youtube." +
                                    "\n \n ¿Quieres reproducir el video en tu navegador?")
                            .setPositiveButton("Ver en el navegador", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+videos.get(i).getImg()));
                                    activity.startActivity(intent);

                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();


                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView descr;
        CircleImageView play;
        ProgressBar cargando;

        public ViewHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.imgVideos);
            descr = (TextView) itemView.findViewById(R.id.txtCliente);
            play = (CircleImageView) itemView.findViewById(R.id.playVideo);
            cargando = (ProgressBar) itemView.findViewById(R.id.cargandoVideos);











        }

    }
}
