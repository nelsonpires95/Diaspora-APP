package com.nelsonpires.diasporaapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;


import com.battleent.ribbonviews.RibbonLayout;
import com.squareup.picasso.Picasso;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class SelectNewsDirectoryActivity extends AppCompatActivity {

    RibbonLayout ribbonLayout;
    GridLayout countryGrid;
    FeedListActivity feedListActivity;
    DirectoryActivity directoryActivity;
    ImageView imageView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_directory_layout);

        countryGrid = (GridLayout) findViewById(R.id.countryGrid);
        ribbonLayout = (RibbonLayout) findViewById(R.id.ribbonLayoutSelected);
        imageView = (ImageView) findViewById(R.id.imageViewSelected);



        //Set Events
        fillCard(ribbonLayout);
        setSingleEvent(countryGrid);



    }

    private void fillCard(RibbonLayout ribbonLayout){
        String bottomText = getIntent().getStringExtra("bottomText");
        String image = getIntent().getStringExtra("image");
        String headerText = getIntent().getStringExtra("headerText");

        ribbonLayout.setBottomText(bottomText);
        ribbonLayout.setHeaderText(headerText);
        Picasso.with(context).load(image)
                .into(imageView);

    }

    private void setSingleEvent(GridLayout countryGrid) {
        //Loop childs Grid
        for (int i = 0; i < countryGrid.getChildCount(); i++) {

            //All childs are cardViews
            CardView cardView = (CardView) countryGrid.getChildAt(i);

            final int finalI = i;

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(finalI == 0) // "News of the country"
                    {
                        if(getIntent().hasExtra("bottomText")){
                            String bottomText = getIntent().getStringExtra("bottomText");

                            String strNoAccent = Normalizer.normalize(bottomText, Normalizer.Form.NFD);
                            strNoAccent = strNoAccent.replaceAll("[^\\p{ASCII}]", "");
                            String slug = strNoAccent.replaceAll(" ", "-");

                            String news_url = "http://www.diasporalusa.pt/api/get_category_posts/?slug=" + slug;
                            Log.d("GET URL : ", "URL: "+ news_url);


                            feedListActivity.url = news_url;
                            Intent intent = new Intent(SelectNewsDirectoryActivity.this,FeedListActivity.class);
                            startActivity(intent);
                        }
                    }

                    else if(finalI == 1) // "Country directory"
                    {
                        if(getIntent().hasExtra("bottomText")) {
                            String country = getIntent().getStringExtra("bottomText");

                            Log.d("AQUI", "onClick: "+ country);

                            directoryActivity.country = country;
                            directoryActivity.JSON_URL="http://www.diasporalusa.pt/api/get_page_index/?post_type=restaurante/";
                            Intent intent = new Intent(SelectNewsDirectoryActivity.this, DirectoryActivity.class);
                            startActivity(intent);
                        }

                    }
                }
            });

        }



    }

}
