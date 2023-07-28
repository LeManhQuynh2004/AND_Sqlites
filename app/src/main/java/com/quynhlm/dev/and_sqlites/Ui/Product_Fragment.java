package com.quynhlm.dev.and_sqlites.Ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

    EditText edt_name, edt_price, edt_quantity, edt_describe;

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
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            View mView = LayoutInflater.from(getContext()).inflate(R.layout.item_add_product, null);
            builder.setView(mView);
            AlertDialog alertDialog = builder.create();
            edt_name = mView.findViewById(R.id.edt_product_add_name);
            edt_price = mView.findViewById(R.id.edt_product_add_price);
            edt_describe = mView.findViewById(R.id.edt_product_add_describe);
            edt_quantity = mView.findViewById(R.id.edt_product_add_quantity);
            mView.findViewById(R.id.btnAdd_Product).setOnClickListener(view2 -> {
                String name = edt_name.getText().toString().trim();
                double price = Double.parseDouble(edt_price.getText().toString().trim());
                int quantity = Integer.parseInt(edt_quantity.getText().toString().trim());
                String describe = edt_describe.getText().toString().trim();
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
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.show();

        });
        return view;
    }
}