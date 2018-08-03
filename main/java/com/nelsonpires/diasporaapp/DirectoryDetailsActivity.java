package com.nelsonpires.diasporaapp;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nelsonpires.diasporaapp.model.DictItem;
import com.nelsonpires.diasporaapp.model.FeedItem;

public class DirectoryDetailsActivity extends AppCompatActivity {

    private DictItem dictItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory_details);

        //hide actionbar
        //getSupportActionBar().hide();

        // Receber dados
        String  name = getIntent().getExtras().getString("i_item_name");
        String  description = getIntent().getExtras().getString("i_description");
        String  adress = getIntent().getExtras().getString("i_adress");
        String  category = getIntent().getExtras().getString("i_category");
        int  nb_episode = getIntent().getExtras().getInt("i_nb_episode");
        String rating = getIntent().getExtras().getString("i_rating");
        String  img_url = getIntent().getExtras().getString("i_img");

        //ini views

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_name = findViewById(R.id.aa_item_name);
        TextView tv_studio = findViewById(R.id.aa_item_adress);
        TextView tv_category = findViewById(R.id.aa_category);
        //TextView tv_description = findViewById(R.id.aa_description);
        TextView tv_rating = findViewById(R.id.aa_rating);
        ImageView img = findViewById(R.id.aa_thumbnail);


        // setting values to each view

        tv_name.setText(name);
        tv_studio.setText(adress);
        tv_category.setText(category);
        //tv_description.setText(description);
        //tv_rating.setText(rating);

        collapsingToolbarLayout.setTitle(name);

        TextView htmlTextView = (TextView) findViewById(R.id.contentDirect);
        htmlTextView.setText(Html.fromHtml(description, null, null));

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


        //set image glide
        Glide.with(this).load(img_url).apply(requestOptions).into(img);



    }
}
