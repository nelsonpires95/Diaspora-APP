package com.nelsonpires.diasporaapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nelsonpires.diasporaapp.DirectoryDetailsActivity;
import com.nelsonpires.diasporaapp.model.DictItem;
import com.nelsonpires.diasporaapp.R;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context nContext;
    private List<DictItem> nData;
    RequestOptions option;

    public RecyclerViewAdapter(Context nContext, List<DictItem> nData) {
        this.nContext = nContext;
        this.nData = nData;

        //Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(nContext);
        view = inflater.inflate(R.layout.direc_item_row_layout, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nContext, DirectoryDetailsActivity.class);
                intent.putExtra("i_item_name", nData.get(viewHolder.getAdapterPosition()).getName());
                intent.putExtra("i_description", nData.get(viewHolder.getAdapterPosition()).getDescription());
                intent.putExtra("i_adress", nData.get(viewHolder.getAdapterPosition()).getAdress());
                intent.putExtra("i_category", nData.get(viewHolder.getAdapterPosition()).getCategory());
                intent.putExtra("i_nb_episode", nData.get(viewHolder.getAdapterPosition()).getNb_episode());
                intent.putExtra("i_rating", nData.get(viewHolder.getAdapterPosition()).getNb_episode());
                intent.putExtra("i_img", nData.get(viewHolder.getAdapterPosition()).getImage());

                nContext.startActivity(intent);

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.dir_name.setText(Html.fromHtml(nData.get(position).getName()));
        //holder.dir_rating.setText(nData.get(position).getRating());
        holder.dir_adress.setText(nData.get(position).getAdress());
        holder.dir_category.setText(nData.get(position).getCategory());

        //Load image from internet to set in ImageView with Glide
        Glide.with(nContext).load(nData.get(position).getImage()).apply(option).into(holder.img_thumbnail);

    }

    @Override
    public int getItemCount() {
        return nData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView dir_name;
        TextView dir_rating;
        TextView dir_adress;
        TextView dir_category;
        ImageView img_thumbnail;
        LinearLayout view_container;


        public MyViewHolder(View itemView){
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            dir_name = itemView.findViewById(R.id.item_name);
            //dir_rating = itemView.findViewById(R.id.rating);
            dir_adress = itemView.findViewById(R.id.item_adress);
            dir_category = itemView.findViewById(R.id.category);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);

        }
    }
}
