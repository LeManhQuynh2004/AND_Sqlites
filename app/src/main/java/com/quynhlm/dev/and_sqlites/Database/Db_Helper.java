package com.quynhlm.dev.and_sqlites.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Db_Helper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "demo.db";
    private static final int DATABASE_VERSION = 1;

    public Db_Helper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String Create_table_products = "CREATE TABLE Products(" +
                "product_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "price DOUBLE NOT NULL," +
                "describe TEXT NOT NULL," +
                "quantity INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(Create_table_products);
        String Create_table_orders = "CREATE TABLE Orders(\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name TEXT NOT NULL,\n" +
                "    price DOUBLE NOT NULL,\n" +
                "    quantity INTEGER NOT NULL,\n" +
                "    money INTEGER NOT NULL,\n" +
                "    product_id INTEGER NOT NULL,\n" +
                "    FOREIGN KEY (product_id) REFERENCES Products(product_id)\n" +
                ")\n";
        sqLiteDatabase.execSQL(Create_table_orders);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Products");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Orders");
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}
