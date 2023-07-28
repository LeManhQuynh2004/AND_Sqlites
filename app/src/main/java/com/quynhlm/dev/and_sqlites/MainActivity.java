package com.quynhlm.dev.and_sqlites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.quynhlm.dev.and_sqlites.Ui.Order_Fragment;
import com.quynhlm.dev.and_sqlites.Ui.Product_Fragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bottomNavigationView = findViewById(R.id.BottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Product_Fragment()).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int index = item.getItemId();
                if (index == R.id.menu_product) {
                    getSupportActionBar().setTitle("ProductManager");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Product_Fragment()).commit();
                } else {
                    getSupportActionBar().setTitle("OrderManager");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Order_Fragment()).commit();
                }
                return true;
            }
        });
    }
}