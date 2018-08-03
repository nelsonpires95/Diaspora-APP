package com.nelsonpires.diasporaapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;

import java.net.InetAddress;

public class HomeActivity extends AppCompatActivity {

    GridLayout mainGrid;
    FeedListActivity feedListActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(isNetworkConnected())
        {
            mainGrid = (GridLayout) findViewById(R.id.mainGrid);

            //Set Event
            setSingleEvent(mainGrid);
        }
        else
        {
            showDialog();
        }

    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //All child item is CardView cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);

            final int finalI = i;

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(finalI == 0) // "Noticias"
                    {
                        feedListActivity.url = "http://www.diasporalusa.pt/api/get_recent_posts/";
                        Intent intent = new Intent(HomeActivity.this,FeedListActivity.class);
                        startActivity(intent);
                    }

                    else if (finalI == 1) //"Spots"
                    {
                        Intent intent = new Intent(HomeActivity.this,NearbyPlacesActivity.class);
                        startActivity(intent);
                    }

                    else if(finalI == 2) // "Comunidades"
                    {
                        Intent intent = new Intent(HomeActivity.this,CommunitiesActivity.class);
                        startActivity(intent);
                    }

                    else if (finalI == 3) //"Siga-nos"
                    {
                        Intent facebookIntent = openFacebookIntent(HomeActivity.this);
                        startActivity(facebookIntent);
                    }

                }
            });

        }
    }


    public static Intent openFacebookIntent(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/709631199181778"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/diasporalusa"));
        }
    }



    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }


    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sem conexão à internet")
                .setMessage("Por favor ligue-se a uma rede")
                .setCancelable(false)
                .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(Settings.ACTION_SETTINGS));
                        finish();
                    }
                })
                .setNegativeButton("Sair", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
