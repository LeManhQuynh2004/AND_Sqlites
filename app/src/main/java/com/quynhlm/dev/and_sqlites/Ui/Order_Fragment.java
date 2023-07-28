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

import com.quynhlm.dev.and_sqlites.Adapter.Order_Adapter;
import com.quynhlm.dev.and_sqlites.Dao.OrderDao;
import com.quynhlm.dev.and_sqlites.Model.Orders;
import com.quynhlm.dev.and_sqlites.Model.Product;
import com.quynhlm.dev.and_sqlites.R;

import java.util.ArrayList;


public class Order_Fragment extends Fragment {

    View view;

    RecyclerView recyclerView;

    EditText edt_name, edt_price, edt_product_id, edt_quantity;

    OrderDao orderDao;

    Order_Adapter order_adapter;

    ArrayList<Orders> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_order, container, false);
        recyclerView = view.findViewById(R.id.RecyclerView_order);
        orderDao = new OrderDao(getContext());
        list = orderDao.selectAll();
        order_adapter = new Order_Adapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(order_adapter);
        view.findViewById(R.id.fab_add_order).setOnClickListener(view1 -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            View mView = LayoutInflater.from(getContext()).inflate(R.layout.item_add_order, null);
            builder.setView(mView);
            AlertDialog alertDialog = builder.create();
            edt_name = mView.findViewById(R.id.edt_order_add_name);
            edt_price = mView.findViewById(R.id.edt_order_add_price);
            edt_product_id = mView.findViewById(R.id.edt_order_add_product_id);
            edt_quantity = mView.findViewById(R.id.edt_order_add_quantity);
            mView.findViewById(R.id.btn_Add_Order).setOnClickListener(view2 -> {
                String name = edt_name.getText().toString().trim();
                double price = Double.parseDouble(edt_price.getText().toString().trim());
                int quantity = Integer.parseInt(edt_quantity.getText().toString().trim());
                int product_id = Integer.parseInt(edt_product_id.getText().toString().trim());
                double tongtien = quantity * price;
                Orders orders = new Orders();
                orders.setName(name);
                orders.setPrice(price);
                orders.setQuantity(quantity);
                orders.setMoney(tongtien);
                orders.setProduct_id(product_id);

                if (orderDao.insertData(orders)) {
                    Toast.makeText(getContext(), "them thanh cong", Toast.LENGTH_SHORT).show();
                    list.add(orders);
                    order_adapter.notifyDataSetChanged();
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