package com.quynhlm.dev.and_sqlites.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.quynhlm.dev.and_sqlites.Database.Db_Helper;
import com.quynhlm.dev.and_sqlites.Model.Orders;

import java.util.ArrayList;

public class OrderDao {
    Db_Helper db_helper;
    Context context;

    public OrderDao(Context context) {
        db_helper = new Db_Helper(context);
    }

    public void insertData(Orders orders) {
        SQLiteDatabase sqLiteDatabase = db_helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", orders.getName());
        contentValues.put("price", orders.getPrice());
        contentValues.put("money", orders.getMoney());
        contentValues.put("quantity", orders.getQuantity());
        contentValues.put("product_id", orders.getName());
        long check = sqLiteDatabase.insert("Orders", null, contentValues);
        if (check > 0) {
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete(int id) {
        SQLiteDatabase sqLiteDatabase = db_helper.getWritableDatabase();
        String dk[] = {String.valueOf(id)};
        long check = sqLiteDatabase.delete("Orders", "id=?", dk);
        if (check > 0) {
            Toast.makeText(context, "Xoa thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Xoa thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    public void UpdateData(Orders orders) {
        SQLiteDatabase sqLiteDatabase = db_helper.getWritableDatabase();
        String dk[] = {String.valueOf(orders.getId())};
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", orders.getName());
        contentValues.put("price", orders.getPrice());
        contentValues.put("money", orders.getMoney());
        contentValues.put("quantity", orders.getQuantity());
        contentValues.put("product_id", orders.getName());
        long check = sqLiteDatabase.update("Orders", contentValues, "id=?", dk);
        if (check > 0) {
            Toast.makeText(context, "Sua thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Sua thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<Orders> selectAll() {
        ArrayList<Orders> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = db_helper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Orders",null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            
        }
    }
}
