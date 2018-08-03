package com.nelsonpires.diasporaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.nelsonpires.diasporaapp.adapter.CustomAdapter;
import com.nelsonpires.diasporaapp.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CommunitiesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    List<Item> items;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communities);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        initItem();

    }

    private void initItem(){
        // 0 = new type, 1 = hot type, 2 = other type
        items = new ArrayList<>();

        items.add(new Item(0, "New", "Canadá", "http://www.affinityvr.com/wp-content/uploads/2018/03/canadian-flag.jpg"));
        items.add(new Item(0, "New", "Estados Unidos da América", "https://www.visanews.in/wp-content/uploads/2017/10/Statue_Liberty_US.jpg"));
        items.add(new Item(0, "New", "França", "https://cdn.jornaldenegocios.pt/images/2015-11/img_817x460$2015_11_23_09_04_02_268955.jpg"));
        items.add(new Item(0, "New", "Reino Unido", "http://www.dnoticias.pt/binrepository/768x512/0c40/768d432/none/11506/JLWS/image_content_1280825_20180126214624.jpg"));
        // ... 18 comunidades Portuguesas


        adapter = new CustomAdapter(this, items);
        recyclerView.setAdapter(adapter);
    }

}
