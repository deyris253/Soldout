package com.example.selling.adaptations;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.selling.R;
import com.example.selling.models.CartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;
    List<CartModel> cartModelList;
    int totalPrice = 0;
    FirebaseFirestore firestore;
    FirebaseAuth auth;



    public CartAdapter(Context context, List<CartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

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

        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                        .collection("CurrentUser")
                        .document(cartModelList.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<Void> task) {

                                if (task.isSuccessful()) {

                                    cartModelList.remove(cartModelList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Le produit a bien été retiré", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(context, "Erroooor" + task, Toast.LENGTH_LONG).show();
                                }

                            }
                        });

            }
        });
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
        ImageView deleteItem;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.text_view_1);
            productPrice = itemView.findViewById(R.id.text_view_2);
            totalPrice = itemView.findViewById(R.id.price_amount);
            deleteItem = itemView.findViewById(R.id.del_item);

        }
    }
}
