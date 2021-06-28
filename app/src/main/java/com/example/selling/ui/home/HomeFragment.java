package com.example.selling.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.selling.R;
import com.example.selling.adaptations.HomeCategoryAdapter;
import com.example.selling.adaptations.ProductAdapter;
import com.example.selling.models.CategoriesHome;
import com.example.selling.models.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView productRec, catHomeRec;
    FirebaseFirestore db;

    List<ProductModel> productModelList;
    ProductAdapter productAdapter;

    List<CategoriesHome> categoriesHomeList;
    HomeCategoryAdapter homeCategoryAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        db = FirebaseFirestore.getInstance();


        productRec = root.findViewById(R.id.all_products_recycler);
        catHomeRec = root.findViewById(R.id.every_category_recycler);


        // Product Items
        productRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        productModelList = new ArrayList<>();
        productAdapter = new ProductAdapter(getActivity(), productModelList);
        productRec.setAdapter(productAdapter);

        db.collection("Products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                ProductModel productModel = document.toObject(ProductModel.class);
                                productModelList.add(productModel);
                                productAdapter.notifyDataSetChanged();
                            }
                        } else {

                            Toast.makeText(getActivity(), "Errooooooooor"+task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });


        // Category Items
        catHomeRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        categoriesHomeList = new ArrayList<>();
        homeCategoryAdapter = new HomeCategoryAdapter(getActivity(), categoriesHomeList);
        catHomeRec.setAdapter(homeCategoryAdapter);

        db.collection("CategoriesInHome")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                CategoriesHome categoriesHome = document.toObject(CategoriesHome.class);
                                categoriesHomeList.add(categoriesHome);
                                homeCategoryAdapter.notifyDataSetChanged();
                            }
                        } else {

                            Toast.makeText(getActivity(), "Errooooooooor"+task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });


        return root;
    }
}