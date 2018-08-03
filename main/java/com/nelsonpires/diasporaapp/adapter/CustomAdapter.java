package com.nelsonpires.diasporaapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.battleent.ribbonviews.RibbonLayout;
import com.nelsonpires.diasporaapp.CommunitiesActivity;
import com.nelsonpires.diasporaapp.FeedListActivity;
import com.nelsonpires.diasporaapp.R;
import com.nelsonpires.diasporaapp.SelectNewsDirectoryActivity;
import com.nelsonpires.diasporaapp.model.Item;
import com.squareup.picasso.Picasso;

import java.text.Normalizer;
import java.util.List;


class CustomViewHolder extends RecyclerView.ViewHolder{

    RibbonLayout ribbonLayout;
    ImageView imageView;


    private AdapterView.OnItemClickListener itemClickListener;

    public CustomViewHolder(View itemView){
        super(itemView);

        ribbonLayout = (RibbonLayout)itemView.findViewById(R.id.ribbonLayout);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);

    }
}

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    Context context;
    List<Item> itemList;
    FeedListActivity feedListActivity;

    public CustomAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position){
        final Item item = itemList.get(position);

        if(item.type == 0)//New
        {
            holder.ribbonLayout.setShowBottom(true);
            holder.ribbonLayout.setShowBottom(true);

            holder.ribbonLayout.setHeaderRibbonColor(Color.parseColor("#2B323A"));
            holder.ribbonLayout.setHeaderTextColor(Color.parseColor("#FFFFFF"));

            holder.ribbonLayout.setHeaderText(item.headerText);
            holder.ribbonLayout.setBottomText(item.bottomText);
            Picasso.with(context).load(item.imageURL)
                    .into(holder.imageView);
        }
        else if(item.type == 1)//Hot
        {
            holder.ribbonLayout.setShowBottom(true);
            holder.ribbonLayout.setShowBottom(true);

            holder.ribbonLayout.setHeaderRibbonColor(Color.parseColor("#B94948"));
            holder.ribbonLayout.setHeaderTextColor(Color.parseColor("#FFFFFF"));

            holder.ribbonLayout.setHeaderText(item.headerText);
            holder.ribbonLayout.setBottomText(item.bottomText);
            Picasso.with(context).load(item.imageURL)
                    .into(holder.imageView);
        }
        else
        {
            holder.ribbonLayout.setShowBottom(false);
            holder.ribbonLayout.setShowBottom(false);

            Picasso.with(context).load(item.imageURL)
                    .into(holder.imageView);
        }

        holder.ribbonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SelectNewsDirectoryActivity.class);
                Log.d("PAIS", item.bottomText);
                intent.putExtra("bottomText",item.getBottomText());
                intent.putExtra("image", item.getImageURL());
                intent.putExtra("headerText", item.getHeaderText());
                context.startActivity(intent);
            }
        });

        /*holder.ribbonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CountriesPageActivity.class);
                Log.d("PAIS", item.bottomText);
                intent.putExtra("bottomText",item.getBottomText());
                context.startActivity(intent);
            }
        });*/

        /*holder.ribbonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FeedListActivity.class);
                Log.d("PAÍS", item.bottomText);
                String bottomText = item.getBottomText();

                String strNoAccent = Normalizer.normalize(bottomText, Normalizer.Form.NFD);
                strNoAccent = strNoAccent.replaceAll("[^\\p{ASCII}]", "");
                String slug = strNoAccent.replaceAll(" ", "-");

                String news_url = "http://www.diasporalusa.pt/api/get_category_posts/?slug=" + slug;
                Log.d("GET URL : ", "URL: "+ news_url);

                String directory_url = null;

                feedListActivity.url = news_url;

                context.startActivity(intent);

            }
        });*/
    }

    @Override
    public int getItemCount(){
        return itemList.size();
    }
}
