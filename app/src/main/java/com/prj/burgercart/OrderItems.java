package com.prj.burgercart;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.myapplication.R;

public class OrderItems extends AppCompatActivity {
    OrdersDB orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_items);
        orders = new OrdersDB(this);
        GridView grid = (GridView) findViewById(R.id.GV);
        OrderItemAdapter orderItem = new OrderItemAdapter(getApplicationContext(), R.layout.activity_order_items);
        grid.setAdapter(orderItem);
        TextView price = (TextView) findViewById(R.id.textView9);
        int OrderID = Integer.valueOf(getIntent().getStringExtra("OrderID"));
        /*  */
        Cursor cursor = orders.LoadOrderItems(OrderID);
        if (cursor.moveToFirst()) {
            do {
               int id = cursor.getInt(0);
                String name = cursor.getString(1);
               String descrp = cursor.getString(2);
               int Price = cursor.getInt(3);
               int Quantity = cursor.getInt(4);
                MenuItem orr = new MenuItem(id,name, descrp, Price,Quantity);
                orderItem.AddItem(orr);

            } while (cursor.moveToNext());
        }

        int p = 0;
        for (int i = 0; i < orderItem.getCount(); i++) {
            MenuItem m = (MenuItem) orderItem.getItem(i);
            p += m.Price;
        }
        price.setText("Total price " + "\n" + p + " LE");

    }
}