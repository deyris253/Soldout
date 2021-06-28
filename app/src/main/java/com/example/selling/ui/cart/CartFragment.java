package com.example.selling.ui.cart;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.selling.R;
import com.example.selling.adaptations.CartAdapter;
import com.example.selling.models.CartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;

    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    List<CartModel> cartModelList;

    TextView monTotalAmount;

    public CartFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        recyclerView = root.findViewById(R.id.cart_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        monTotalAmount = root.findViewById(R.id.price_amount);

        LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(mMessageReceiver, new IntentFilter("TotalAmount"));

        cartModelList = new ArrayList<>();
        cartAdapter = new CartAdapter(getActivity(), cartModelList);

        recyclerView.setAdapter(cartAdapter);

        db.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                        String documentId = documentSnapshot.getId();

                        CartModel cartModel = documentSnapshot.toObject(CartModel.class);

                        cartModel.setDocumentId(documentId);

                        cartModelList.add(cartModel);
                        cartAdapter.notifyDataSetChanged();
                    }
                }

            }
        });


        return root;
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int totalBill = intent.getIntExtra("totalAmount", 0);
            monTotalAmount.setText("Montant Total : " +totalBill+ "€");

        }
    };


}