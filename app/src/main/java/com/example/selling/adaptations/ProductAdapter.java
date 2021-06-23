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
import com.example.selling.models.ProductModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private List<ProductModel> productModelList;

    public ProductAdapter(Context context, List<ProductModel> productModelList) {
        this.context = context;
        this.productModelList = productModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(productModelList.get(position).getImg_url()).into(holder.productImg);
        holder.name.setText(productModelList.get(position).getName());
        holder.description.setText(productModelList.get(position).getDescription());
        holder.price.setText(productModelList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImg;
        TextView name, description, price;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            productImg = itemView.findViewById(R.id.product_img);
            name = itemView.findViewById(R.id.product_name);
            description = itemView.findViewById(R.id.product_desc);
            price = itemView.findViewById(R.id.product_price);
        }
    }

}
