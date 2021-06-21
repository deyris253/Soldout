package com.example.selling.adaptations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.selling.R;
import com.example.selling.models.CategoriesHome;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {

    Context context;
    List<CategoriesHome> categoriesHomeList;

    public HomeCategoryAdapter(Context context, List<CategoriesHome> categoriesHomeList) {
        this.context = context;
        this.categoriesHomeList = categoriesHomeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_home,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(categoriesHomeList.get(position).getImg_url()).into(holder.catImg);
        holder.name.setText(categoriesHomeList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categoriesHomeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView catImg;
        TextView name;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            catImg = itemView.findViewById(R.id.category_img);
            name = itemView.findViewById(R.id.category_name);

        }
    }
}
