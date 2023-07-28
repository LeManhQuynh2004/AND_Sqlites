package com.quynhlm.dev.and_sqlites.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.quynhlm.dev.and_sqlites.Dao.OrderDao;
import com.quynhlm.dev.and_sqlites.Model.Orders;
import com.quynhlm.dev.and_sqlites.R;

import java.util.ArrayList;

public class Order_Adapter extends RecyclerView.Adapter<Order_Adapter.OrderViewHolder> {

    Context context;
    ArrayList<Orders> list;

    OrderDao ordersDao;

    EditText edt_name, edt_price, edt_product_id, edt_quantity;

    public Order_Adapter(Context context, ArrayList<Orders> list) {
        this.context = context;
        this.list = list;
        ordersDao = new OrderDao(context);
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.txt_id.setText(list.get(position).getProduct_id() + "");
        holder.txt_name.setText(list.get(position).getName());
        holder.txt_price.setText(list.get(position).getPrice() + "");
        holder.txt_quantity.setText(list.get(position).getQuantity() + "");
        holder.txt_money.setText(list.get(position).getMoney() + "");
        holder.itemView.setOnClickListener(view -> {
            ShowPopur_menu(view, position);
        });
    }

    public void ShowPopur_menu(View view, int position) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.popur_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int index = item.getItemId();
                if (index == R.id.menu_delete) {
                    DeleteItem(position);
                }
                if (index == R.id.menu_update) {
                    UpdateItem(position);
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void UpdateItem(int position) {
        Orders orders = list.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("thong bao");
        builder.setMessage("ban co chan chan muon xoa " + orders.getName() + " Ko ?");
        builder.setPositiveButton("xoa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (ordersDao.delete(orders.getId())) {
                    Toast.makeText(context, "xoa thanh cong", Toast.LENGTH_SHORT).show();
                    list.remove(orders);
                    notifyItemRemoved(position);
                    notifyItemChanged(position);
                } else {
                    Toast.makeText(context, "xoa that bai", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setNegativeButton("huy", null);
        builder.show();
    }

    private void DeleteItem(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View mView = LayoutInflater.from(context).inflate(R.layout.item_add_order, null);
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

            if (ordersDao.UpdateData(orders)) {
                Toast.makeText(context, "sua thanh cong", Toast.LENGTH_SHORT).show();
                list.set(position, orders);
                notifyDataSetChanged();
            } else {
                Toast.makeText(context, "sua that bai", Toast.LENGTH_SHORT).show();
            }

        });
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView txt_name, txt_price, txt_id, txt_money, txt_quantity;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_id = itemView.findViewById(R.id.txt_order_product_id);
            txt_name = itemView.findViewById(R.id.txt_order_product_name);
            txt_price = itemView.findViewById(R.id.txt_order_product_price);
            txt_quantity = itemView.findViewById(R.id.txt_order_product_quantity);
            txt_money = itemView.findViewById(R.id.txt_order_product_money);
        }
    }
}
