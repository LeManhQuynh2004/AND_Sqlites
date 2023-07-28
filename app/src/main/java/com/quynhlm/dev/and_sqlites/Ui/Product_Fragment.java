package com.quynhlm.dev.and_sqlites.Ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.quynhlm.dev.and_sqlites.Adapter.Product_Adapter;
import com.quynhlm.dev.and_sqlites.Dao.ProductDao;
import com.quynhlm.dev.and_sqlites.Model.Product;
import com.quynhlm.dev.and_sqlites.R;

import java.util.ArrayList;
import java.util.Random;


public class Product_Fragment extends Fragment {
    View view;

    RecyclerView recyclerView;

    ProductDao productDao;

    Product_Adapter product_adapter;

    ArrayList<Product> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_product, container, false);
        recyclerView = view.findViewById(R.id.RecyclerView_Product);
        productDao = new ProductDao(getContext());
        list = productDao.selectAll();
        product_adapter = new Product_Adapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(product_adapter);
        view.findViewById(R.id.fab_add_product).setOnClickListener(view1 -> {
            String name = "San Pham" + new Random().nextInt(5) + 1;
            double price = 1.23323 + new Random().nextInt();
            int quantity = 1 + new Random().nextInt(5) + 1;
            String describe = "ngon bo re" + new Random().nextInt();
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setDescribe(describe);
            if (productDao.insertDate(product)) {
                Toast.makeText(getContext(), "them thanh cong", Toast.LENGTH_SHORT).show();
                list.add(product);
                product_adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "them that bai", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}