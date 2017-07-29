package com.example.jorgepc.fotoceramica;


import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class ActivityInicio extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView nav;
    BottomNavigationView bottomNav;
    private FragmentTransaction transaction;
    private Fragment fragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_inicio);

        nav = (NavigationView) findViewById(R.id.NavigationView);
        drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        toolbar = (Toolbar) findViewById(R.id.Toolbar);
        toolbar.setTitle("Fotocerámica");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.blanco));
        bottomNav = (BottomNavigationView) findViewById(R.id.BottomNav);


        for (int i = 0; i < nav.getChildCount(); i++) {
            nav.getChildAt(i).setOverScrollMode(View.OVER_SCROLL_NEVER);
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.abrir,R.string.cerrar);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        nav.setItemIconTintList(null);

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame, new FragmentInicio()).commit();


        nav.setNavigationItemSelectedListener(this);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {



                switch (item.getItemId()){

                    case R.id.menuInicio:

                        toolbar.setTitle("Fotocerámica");
                        fragment = new FragmentInicio();

                        break;

                    case R.id.menuVideosProd:

                        toolbar.setTitle("Videos");
                        fragment = new FragmentVideosProductos();

                        break;

                    case R.id.menuVideosTestiomonios:

                        toolbar.setTitle("Testimonios");
                        fragment = new FragmentTestimonios();

                        break;

                    case R.id.menuInfo:

                        toolbar.setTitle("Información");
                        fragment = new FragmentInfo();

                        break;

                }

                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, fragment).commit();


                return true;
            }
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        toolbar.setTitle(item.getTitle());
        CerrarDrawer();



        switch (item.getItemId()){


            case R.id.producto20x30:

                fragment = new FragmentFotosClasicas();

                break;

            case R.id.productoLoza:

                fragment = new FragmentLozas();

                break;

            case R.id.productoLapida:

                fragment = new FragmentLapidas();

                break;

            case R.id.productoPromociones:

                fragment = new FragmentPromociones();


                break;

            case R.id.productoTazas:

                fragment = new FragmentTazas();

                break;

        }

        if(fragment != null){
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame, fragment).commit();
        }

        return true;
    }

    private void CerrarDrawer(){

        drawerLayout.closeDrawer(GravityCompat.START);

    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            CerrarDrawer();
        }else{
            super.onBackPressed();
        }



    }
}
