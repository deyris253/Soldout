package com.example.sellout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sellout.navigationBar.FragmentAccount;
import com.example.sellout.navigationBar.FragmentCart;
import com.example.sellout.navigationBar.FragmentHome;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.mainLayout, FragmentHome.class, null)
                    .commit();
        }

        BottomNavigationView bottomNavig = findViewById(R.id.bottomNav);

        bottomNavig.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment frag = null;

                switch (item.getItemId()) {

                    case R.id.home:
                        frag = new FragmentHome();
                        break;

                    case R.id.cart:
                        frag = new FragmentCart();
                        break;

                    case R.id.account:
                        frag = new FragmentAccount();
                        break;

                }

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainLayout, frag)
                        .commit();
                return true;
            }
        });

    }
}