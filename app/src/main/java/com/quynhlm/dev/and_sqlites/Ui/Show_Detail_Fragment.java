package com.quynhlm.dev.and_sqlites.Ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.quynhlm.dev.and_sqlites.Adapter.Order_Adapter;
import com.quynhlm.dev.and_sqlites.Dao.OrderDao;
import com.quynhlm.dev.and_sqlites.Model.Orders;
import com.quynhlm.dev.and_sqlites.Model.Product;
import com.quynhlm.dev.and_sqlites.R;

import java.util.ArrayList;


public class Show_Detail_Fragment extends Fragment {


    View view;
    TextView txt_name, txt_price, txt_id, txt_quantity, txt_money, txt_describe, txt_cong, txt_tru, txt_soLuong;

    int quantityorder = 1;

    ArrayList<Orders> list = new ArrayList<>();

    OrderDao orderDao;

    Order_Adapter order_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_show_detail, container, false);
        txt_name = view.findViewById(R.id.txt_name_show_detail);
        txt_price = view.findViewById(R.id.txt_price_show_detail);
        txt_quantity = view.findViewById(R.id.txt_quantity_show_detail);
        txt_describe = view.findViewById(R.id.txt_describe_show_detail);
        txt_id = view.findViewById(R.id.txt_id_show_detail);
        txt_money = view.findViewById(R.id.txt_money_show_detail);
        txt_cong = view.findViewById(R.id.txt_cong);
        txt_soLuong = view.findViewById(R.id.txt_soluong);
        txt_tru = view.findViewById(R.id.txt_tru);
        orderDao = new OrderDao(getContext());
        list = orderDao.selectAll();
        order_adapter = new Order_Adapter(getContext(), list);

        Bundle bundle = getArguments();
        Product product = (Product) bundle.getSerializable("product");
        if (product != null) {
            txt_id.setText(product.getProduct_id() + "");
            txt_name.setText(product.getName());
            txt_price.setText(product.getPrice() + "");
            txt_quantity.setText(product.getQuantity() + "");
            txt_describe.setText(product.getDescribe());
            double money = product.getPrice() * quantityorder;
            txt_money.setText(money + "");
        }

        txt_cong.setOnClickListener(view1 -> {
            int maxQuantity = product.getQuantity();
            if (quantityorder < maxQuantity) {
                quantityorder += 1;
                txt_soLuong.setText(String.valueOf(quantityorder));
                txt_money.setText(String.valueOf(product.getPrice() * quantityorder));
            } else {
                Toast.makeText(getContext(), "Quá số lượng nhà cung cấp", Toast.LENGTH_SHORT).show();
            }
        });

        txt_tru.setOnClickListener(v -> {
            if (quantityorder > 1) {
                quantityorder -= 1;
                txt_soLuong.setText(String.valueOf(quantityorder));
                txt_money.setText(String.valueOf(product.getPrice() * quantityorder));
            }
        });
        view.findViewById(R.id.btn_Add_Show_Detail).setOnClickListener(view1 -> {
            int quantity = Integer.parseInt(txt_soLuong.getText().toString());
            double totalPrice = product.getPrice() * quantity;

            Orders orders = new Orders();
            orders.setPrice(product.getPrice());
            orders.setName(product.getName());
            orders.setQuantity(quantity);
            orders.setProduct_id(product.getProduct_id());
            orders.setMoney(totalPrice);

            if (orderDao.insertData(orders)) {
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                list.add(orders);
                order_adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}