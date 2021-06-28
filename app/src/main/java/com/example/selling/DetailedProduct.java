package com.example.selling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.selling.models.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedProduct extends AppCompatActivity {

    Toolbar toolbar;

    ImageView detailedImg;
    TextView name, description, price;
    int totalPrice = 0;
    Button addToCart;

    RecyclerView recyclerView;
    FirebaseFirestore db;
    FirebaseAuth auth;
    ProductModel productModel = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_product);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object object = getIntent().getSerializableExtra("name");
        if (object instanceof ProductModel) {
            productModel = (ProductModel) object;
        }


        detailedImg = findViewById(R.id.detailed_img_product);
        name = findViewById(R.id.product_name);
        price = findViewById(R.id.product_price);
        description = findViewById(R.id.product_desc);

        if (productModel != null) {
            Glide.with(getApplicationContext()).load((productModel.getImg_url())).into(detailedImg);
            name.setText(productModel.getName());
            description.setText(productModel.getDescription());
            price.setText(productModel.getPrice());

            //totalPrice = productModel.getPrice();
        }

        addToCart = findViewById(R.id.addToCart);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedToCart();
            }

        });

    }

    private void addedToCart() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();

        cartMap.put("productName", productModel.getName());
        cartMap.put("productPrice", productModel.getPrice());
        cartMap.put("Date actuelle", saveCurrentDate);
        cartMap.put("Heure actuelle", saveCurrentTime);

        db.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentReference> task) {

                Toast.makeText(DetailedProduct.this, "Le produit a bien été ajouté au panier", Toast.LENGTH_LONG).show();
                finish();

            }
        });

    }
}