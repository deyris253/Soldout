package com.example.selling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.selling.adaptations.ProductAdapter;
import com.example.selling.models.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ViewProduct extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    List<ProductModel> productModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        db = FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("type");

        recyclerView = findViewById(R.id.view_product_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        productModelList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productModelList);
        recyclerView.setAdapter(productAdapter);

        db.collection("Products").whereEqualTo("type", type)
                .get()
                .addOnCompleteListener(task -> {

                        for (QueryDocumentSnapshot document : task.getResult()) {

                            ProductModel productModel = document.toObject(ProductModel.class);
                            productModelList.add(productModel);
                            productAdapter.notifyDataSetChanged();
                        }

                });

    }
}