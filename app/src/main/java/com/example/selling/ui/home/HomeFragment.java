package com.example.selling.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.selling.R;
import com.example.selling.adaptations.HomeCategoryAdapter;
import com.example.selling.adaptations.ProductAdapters;
import com.example.selling.databinding.FragmentHomeBinding;
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
    ProductAdapters productAdapters;

    List<CategoriesHome> categoriesHomeList;
    HomeCategoryAdapter homeCategoryAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        db = FirebaseFirestore.getInstance();




        productRec = root.findViewById(R.id.all_products);
        catHomeRec = root.findViewById(R.id.every_category);


        // Product Items
        productRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        productModelList = new ArrayList<>();
        productAdapters = new ProductAdapters(getActivity(), productModelList);
        productRec.setAdapter(productAdapters);

        db.collection("Products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                ProductModel productModel = document.toObject(ProductModel.class);
                                productModelList.add(productModel);
                                productAdapters.notifyDataSetChanged();
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