package com.example.jorgepc.fotoceramica;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jorgepc.fotoceramica.youtubekey.YoutubeApiKey;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeIntents;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

import java.util.List;

import me.grantland.widget.AutofitTextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInfo extends Fragment implements View.OnClickListener {

    AutofitTextView linkfb, linkyoutube;



    public FragmentInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_info, container, false);

        linkfb = (AutofitTextView) view.findViewById(R.id.txtFB);
        linkyoutube = (AutofitTextView) view.findViewById(R.id.txtYoutube);

        linkfb.setClickable(true);
        linkyoutube.setClickable(true);

        linkfb.setOnClickListener(this);
        linkyoutube.setOnClickListener(this);



        return view;
    }



    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.txtFB) {

            openFacebookApp();



        } else {

               Intent intentNav = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCC4LZhpHWqF7IwJZ2T3x35A"));
               getActivity().startActivity(intentNav);

        }
    }

    private void openFacebookApp() {
        String facebookUrl = "https://www.facebook.com/fotoceramicperu";
        String facebookID = "182748148436534";

        try {
            int versionCode = getActivity().getApplicationContext().getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;

            if(!facebookID.isEmpty()) {
                // open the Facebook app using facebookID (fb://profile/facebookID or fb://page/facebookID)
                Uri uri = Uri.parse("fb://profile/" + facebookID);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            } else if (versionCode >= 3002850 && !facebookUrl.isEmpty()) {
                // open Facebook app using facebook url
                Uri uri = Uri.parse("fb://facewebmodal/f?href=" + facebookUrl);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            } else {
                // Facebook is not installed. Open the browser
                Uri uri = Uri.parse(facebookUrl);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        } catch (PackageManager.NameNotFoundException e) {
            // Facebook is not installed. Open the browser
            final Uri uri = Uri.parse(facebookUrl);

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setTitle("Página en Facebook de Fotocerámica")
                    .setMessage("Hemos notado que no tienes instalado la aplicación de Facebook." +
                            "\n \n ¿Deseas entrar a la página de Fotocerámica por medio del navegador?")
                    .setPositiveButton("Ver en el navegador", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            startActivity(new Intent(Intent.ACTION_VIEW, uri));

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






}
