package com.quynhlm.dev.and_sqlites.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import com.quynhlm.dev.and_sqlites.Dao.ProductDao;
import com.quynhlm.dev.and_sqlites.MainActivity;
import com.quynhlm.dev.and_sqlites.Model.Product;
import com.quynhlm.dev.and_sqlites.R;
import com.quynhlm.dev.and_sqlites.Ui.Show_Detail_Fragment;

import java.util.ArrayList;

public class Product_Adapter extends RecyclerView.Adapter<Product_Adapter.ProductViewHolder> {
    Context context;
    ArrayList<Product> list;

    ProductDao productDao;

    EditText edt_name, edt_price, edt_quantity, edt_describe;

    public Product_Adapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
        productDao = new ProductDao(context);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.txt_id.setText(list.get(position).getProduct_id() + "");
        holder.txt_name.setText(list.get(position).getName());
        holder.txt_price.setText(list.get(position).getPrice() + "");
        holder.itemView.setOnClickListener(view -> {
            ShowPopur_menu(view, position);
        });
        holder.txt_show_detail.setOnClickListener(view -> {
            Show_Detail_Fragment show_detail_fragment = new Show_Detail_Fragment();
            Product product = list.get(position);
            Bundle bundle = new Bundle();
            bundle.putSerializable("product", product);
            show_detail_fragment.setArguments(bundle);

            ((MainActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, show_detail_fragment).commit();
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

    private void DeleteItem(int position) {
        Product product = list.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("thong bao");
        builder.setMessage("ban co chan chan muon xoa " + product.getName() + " Ko ?");
        builder.setPositiveButton("xoa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    if (productDao.deleteDate(product)) {
                        Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                        list.remove(product);
                        notifyItemRemoved(position);
                        notifyItemChanged(position);
                    } else {
                        Toast.makeText(context, "Xoa that bai", Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLiteConstraintException e) {
                    Toast.makeText(context, "Xoa that bai co lien ket", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("huy", null);
        builder.show();
    }

    private void UpdateItem(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View mView = LayoutInflater.from(context).inflate(R.layout.item_add_product, null);
        builder.setView(mView);
        AlertDialog alertDialog = builder.create();
        edt_name = mView.findViewById(R.id.edt_product_add_name);
        edt_price = mView.findViewById(R.id.edt_product_add_price);
        edt_describe = mView.findViewById(R.id.edt_product_add_describe);
        edt_quantity = mView.findViewById(R.id.edt_product_add_quantity);

        edt_name.setText(list.get(position).getName());
        edt_price.setText(list.get(position).getPrice() + "");
        edt_price.setText(list.get(position).getDescribe());
        edt_quantity.setText(list.get(position).getQuantity() + "");
        mView.findViewById(R.id.btnAdd_Product).setOnClickListener(view2 -> {
            String name = edt_name.getText().toString().trim();
            double price = Double.parseDouble(edt_price.getText().toString().trim());
            int quantity = Integer.parseInt(edt_quantity.getText().toString().trim());
            String describe = edt_describe.getText().toString().trim();
            Product product = list.get(position);
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setDescribe(describe);
            if (productDao.updateDate(product)) {
                Toast.makeText(context, "sua thanh cong", Toast.LENGTH_SHORT).show();
                list.add(product);
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

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView txt_id, txt_name, txt_price, txt_show_detail;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_id = itemView.findViewById(R.id.txt_product_id);
            txt_name = itemView.findViewById(R.id.txt_product_name);
            txt_price = itemView.findViewById(R.id.txt_product_price);
            txt_show_detail = itemView.findViewById(R.id.txt_show_detail);
        }
    }
}
