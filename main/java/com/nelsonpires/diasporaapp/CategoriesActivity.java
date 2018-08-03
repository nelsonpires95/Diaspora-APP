package com.nelsonpires.diasporaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CategoriesActivity extends AppCompatActivity {

    DirectoryActivity directoryActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_layout);
    }


    public void showAll(View v)
    {
        //directoryActivity.JSON_URL = "http://www.diasporalusa.pt/api/get_page_index/?post_type=all";
        //Intent intent = new Intent(this, DirectoryActivity.class);
        //startActivity(intent);
    }

    public void showAlojamento(View v)
    {
        directoryActivity.JSON_URL = "http://www.diasporalusa.pt/api/get_page_index/?post_type=alojamento";
        Intent intent = new Intent(this, DirectoryActivity.class);
        startActivity(intent);
    }

    public void showRestaurantes(View v)
    {
        directoryActivity.JSON_URL = "http://www.diasporalusa.pt/api/get_page_index/?post_type=restaurante";
        Intent intent = new Intent(this, DirectoryActivity.class);
        startActivity(intent);
    }

    public void showEmbaixadas(View v)
    {
        directoryActivity.JSON_URL = "http://www.diasporalusa.pt/api/get_page_index/?post_type=embaixada";
        Intent intent = new Intent(this, DirectoryActivity.class);
        startActivity(intent);
    }

    public void showEnsino(View v)
    {
        //directoryActivity.JSON_URL = "http://www.diasporalusa.pt/api/get_page_index/?post_type=ensino";
        directoryActivity.JSON_URL = "http://www.diasporalusa.pt/api/get_page_index/?post_type=education";
        Intent intent = new Intent(this, DirectoryActivity.class);
        startActivity(intent);
    }

    public void showAssociacoes(View v)
    {
        //Intent intent = new Intent(CategoriesActivity.this, WebViewActivity.class);
        //intent.putExtra("url", "http://www.diasporalusa.pt/directorio/?listing_categories=associacoes");
        //startActivity(intent);

        //directoryActivity.JSON_URL = "http://www.diasporalusa.pt/api/get_page_index/?post_type=associacao";
        //Intent intent = new Intent(this, DirectoryActivity.class);
        //startActivity(intent);
    }

}
