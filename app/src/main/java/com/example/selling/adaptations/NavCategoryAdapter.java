package com.example.selling.adaptations;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.selling.R;
import com.example.selling.ViewProduct;
import com.example.selling.models.NavCategoryModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NavCategoryAdapter extends RecyclerView.Adapter<NavCategoryAdapter.ViewHolder> {

    Context context;
    List<NavCategoryModel> navCategoryModelList;

    public NavCategoryAdapter(Context context, List<NavCategoryModel> navCategoryModelList) {
        this.context = context;
        this.navCategoryModelList = navCategoryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_cat_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NavCategoryAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(navCategoryModelList.get(position).getImg_url()).into(holder.imageView);
        holder.categoryName.setText(navCategoryModelList.get(position).getCategory_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewProduct.class);
                intent.putExtra("type", navCategoryModelList.get(position).getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return navCategoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView categoryName;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cat_nav_img);
            categoryName = itemView.findViewById(R.id.cat_nav_name);
        }
    }
}
