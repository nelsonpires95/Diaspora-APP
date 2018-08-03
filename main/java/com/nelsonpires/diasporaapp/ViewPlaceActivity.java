package com.nelsonpires.diasporaapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nelsonpires.diasporaapp.Remote.IGoogleAPIService;
import com.nelsonpires.diasporaapp.model.Photos;
import com.nelsonpires.diasporaapp.model.PlaceDetail;
import com.squareup.picasso.Picasso;
import com.nelsonpires.diasporaapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPlaceActivity extends AppCompatActivity {

    ImageView photo;
    RatingBar ratingBar;
    TextView opening_hours, place_adress, place_name;
    Button btnViewOnMap;

    IGoogleAPIService mService;

    PlaceDetail mPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_place);

        mService = Common.getGoogleAPIService();

        photo = (ImageView) findViewById(R.id.photo);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        place_adress = (TextView) findViewById(R.id.place_address);
        place_name = (TextView) findViewById(R.id.place_name);
        opening_hours = (TextView) findViewById((R.id.place_open_hour));
        btnViewOnMap = (Button) findViewById((R.id.btn_show_map));

        //Empty all view
        place_name.setText("");
        place_adress.setText("");
        opening_hours.setText("");


        btnViewOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mPlace.getResult().getUrl()));
                startActivity(mapIntent);
            }
        });


        //Photo
        if(Common.currentResult.getPhotos() != null && Common.currentResult.getPhotos().length > 0)
        {
            Picasso.with(this)
                    .load(getPhotoOfPlace(Common.currentResult.getPhotos()[0].getPhoto_reference(),1000))
                    .placeholder(R.drawable.ic_image_black_24dp)
                    .error(R.drawable.ic_error_black_24dp)
                    .into(photo);

        }

        //Rating
        if (Common.currentResult.getRating() != null && !TextUtils.isEmpty(Common.currentResult.getRating()))
        {
            ratingBar.setRating(Float.parseFloat(Common.currentResult.getRating()));
        }
        else
        {
            ratingBar.setVisibility(View.GONE);
        }

        //Opening Hours
        if (Common.currentResult.getOpening_hours() != null)
        {
            if(Common.currentResult.getOpening_hours().getOpen_now() == "true"){
                String first = "Estado neste momento : ";
                String next = "<font color='green'>" + Common.currentResult.getOpening_hours().getOpen_now().replaceAll("true", "Aberto")+"</font>";
                opening_hours.setText(Html.fromHtml(first + next));
            }
            else {
                String first = "Estado neste momento : ";
                String next = "<font color='red'>" + Common.currentResult.getOpening_hours().getOpen_now().replaceAll("false", "Fechado")+"</font>";
                opening_hours.setText(Html.fromHtml(first + next));
            }
        }
        else
        {
            opening_hours.setVisibility(View.GONE);
        }

        //User Service to fetch Adress and Name
        mService.getDetailPage(getPlaceDetailUrl(Common.currentResult.getPlace_id()))
                .enqueue(new Callback<PlaceDetail>() {
                    @Override
                    public void onResponse(Call<PlaceDetail> call, Response<PlaceDetail> response) {

                        mPlace = response.body();

                        place_adress.setText(mPlace.getResult().getFormatted_address());
                        place_name.setText(mPlace.getResult().getName());

                    }

                    @Override
                    public void onFailure(Call<PlaceDetail> call, Throwable t) {

                    }
                });

    }

    private String getPlaceDetailUrl(String place_id) {

        StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/place/details/json");
        url.append("?placeid="+place_id);
        url.append("&key="+getResources().getString(R.string.browser_key));
        return url.toString();

    }

    private String getPhotoOfPlace(String photo_reference, int maxWidth) {
        StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo");
        url.append("?maxwidth="+maxWidth);
        url.append("&photoreference="+photo_reference);
        url.append("&key="+getResources().getString(R.string.browser_key));
        return url.toString();
    }
}
