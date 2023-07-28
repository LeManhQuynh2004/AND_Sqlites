package com.quynhlm.dev.and_sqlites.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quynhlm.dev.and_sqlites.Model.Product;
import com.quynhlm.dev.and_sqlites.R;

import java.util.ArrayList;

public class Product_Adapter extends RecyclerView.Adapter<Product_Adapter.ProductViewHolder> {
    Context context;
    ArrayList<Product> list;

    public Product_Adapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView txt_id, txt_name, txt_price;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_id = itemView.findViewById(R.id.txt_product_id);
            txt_name = itemView.findViewById(R.id.txt_product_name);
            txt_price = itemView.findViewById(R.id.txt_product_price);
        }
    }
}
