package com.example.selling.adaptations;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.selling.R;
import com.example.selling.models.CartModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;
    List<CartModel> cartModelList;
    int totalPrice = 0;

    public CartAdapter(Context context, List<CartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
    }

    @NonNull
    
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        holder.productName.setText(cartModelList.get(position).getProductName());
        holder.productPrice.setText(cartModelList.get(position).getProductPrice());
        //holder.totalPrice.setText(cartModelList.get(position).getTotalPrice());
        //holder.totalPrice.setText(String.valueOf(cartModelList.get(position).getTotalPrice()));

        /*totalPrice = totalPrice + cartModelList.get(position).getTotalPrice();
        Intent intent = new Intent("TotalAmount");
        intent.putExtra("totalAmount", totalPrice);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

         */




    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productName, productPrice, totalPrice;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.text_view_1);
            productPrice = itemView.findViewById(R.id.text_view_2);
            totalPrice = itemView.findViewById(R.id.price_amount);

        }
    }
}
