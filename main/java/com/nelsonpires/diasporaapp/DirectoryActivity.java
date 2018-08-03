package com.nelsonpires.diasporaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nelsonpires.diasporaapp.adapter.RecyclerViewAdapter;
import com.nelsonpires.diasporaapp.model.DictItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DirectoryActivity extends AppCompatActivity {

    //private final String JSON_URL = "";
    public static String JSON_URL = null; // URL for directory/listing of the country
    public static String country = null;
    private JsonObjectRequest request;
    private RequestQueue requestQueue;
    private List<DictItem> lstDict;
    private RecyclerView recyclerView;

    private ProgressBar progressbar = null;

    private RecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;

    List<DictItem> filteredList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        progressbar = (ProgressBar) findViewById(R.id.progressBarDir);

        final SearchView searchView;

        lstDict = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerId);
        jsonrequest();


        searchView=(SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Pesquise por nome");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                filter(query.toString());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText.toString());
                return true;
            }
        });

    }



    private void jsonrequest() {

        request = new JsonObjectRequest(Request.Method.GET,JSON_URL,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray response2 = (JSONArray) response.getJSONArray("pages") ;

                    for (int i = 0; i < response2.length(); i++){

                        DictItem dictItem = new DictItem();

                        JSONArray jsonArray = response.getJSONArray("pages");


                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        JSONObject jsonObject2 = jsonObject1.getJSONObject("custom_fields");
                        JSONArray jsonArrayAdress = jsonObject2.getJSONArray("listing_map_location_address");
                        //JSONArray jsonArrayRating = jsonObject2.getJSONArray("inventor_reviews_post_total_rating");

                        String[] arr = new String[jsonArrayAdress.length()];
                        for(int n = 0; n < jsonArrayAdress.length(); n++){
                            arr[n] = jsonArrayAdress.getString(n);
                        }

                        /*String[] arr1 = new String[jsonArrayRating.length()];
                        for(int n = 0; n < jsonArrayRating.length(); n++){
                            arr1[n] = jsonArrayRating.getString(n);
                        }*/

                        dictItem.setAdress(arr[0]);
                        //dictItem.setRating(arr1[0]);

                        dictItem.setName(jsonArray.getJSONObject(i).getString("title"));
                        dictItem.setCategory(jsonArray.getJSONObject(i).getString("type"));
                        dictItem.setDescription(jsonArray.getJSONObject(i).getString("content"));
                        dictItem.setImage(jsonArray.getJSONObject(i).getString("thumbnail"));
                        dictItem.setNb_episode(jsonArray.getJSONObject(i).getInt("id"));


                        lstDict.add(dictItem);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //setuprecyclerview(lstDict);

                //filter directory by country
                filterCountry(country);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(DirectoryActivity.this);
        requestQueue.add(request);

    }

    private void setuprecyclerview(List<DictItem> lstDict) {

        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, lstDict);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
        progressbar.setVisibility(View.GONE);

    }

    public void openCategories(View v)
    {
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
    }

    private void filterCountry(String text) {

        for (DictItem item : lstDict) {
            if (item.getAdress().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        setuprecyclerview(filteredList);
    }


    private void filter(String text) {
        List<DictItem> filteredList2 = new ArrayList<>();

        for (DictItem item : filteredList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList2.add(item);
            }
        }

        setuprecyclerview(filteredList2);
    }




}
