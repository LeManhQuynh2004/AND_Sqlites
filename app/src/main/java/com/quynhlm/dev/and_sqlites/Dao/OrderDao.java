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

    public boolean insertData(Orders orders) {
        SQLiteDatabase sqLiteDatabase = db_helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", orders.getName());
        contentValues.put("price", orders.getPrice());
        contentValues.put("money", orders.getMoney());
        contentValues.put("quantity", orders.getQuantity());
        contentValues.put("product_id", orders.getProduct_id());
        long check = sqLiteDatabase.insert("Orders", null, contentValues);
        orders.setId((int) check);
        return check > 0;
    }

    public boolean delete(int id) {
        SQLiteDatabase sqLiteDatabase = db_helper.getWritableDatabase();
        String dk[] = {String.valueOf(id)};
        long check = sqLiteDatabase.delete("Orders", "id=?", dk);
        return check > 0;
    }

    public boolean UpdateData(Orders orders) {
        SQLiteDatabase sqLiteDatabase = db_helper.getWritableDatabase();
        String dk[] = {String.valueOf(orders.getId())};
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", orders.getName());
        contentValues.put("price", orders.getPrice());
        contentValues.put("money", orders.getMoney());
        contentValues.put("quantity", orders.getQuantity());
        contentValues.put("product_id", orders.getName());
        long check = sqLiteDatabase.update("Orders", contentValues, "id=?", dk);
        return check > 0;
    }

    public ArrayList<Orders> selectAll() {
        ArrayList<Orders> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = db_helper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Orders", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new Orders(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getInt(3), cursor.getDouble(4), cursor.getInt(5)));
            } while (cursor.moveToNext());
        }
        return list;
    }
}
