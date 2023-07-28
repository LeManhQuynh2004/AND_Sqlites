package com.quynhlm.dev.and_sqlites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.quynhlm.dev.and_sqlites.Ui.Product_Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Product_Fragment()).commit();
    }
}