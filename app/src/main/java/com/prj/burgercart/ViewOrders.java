package com.prj.burgercart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;

public class ViewOrders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vieworders);
        final ListView list = (ListView) findViewById(R.id.historyorders);
        ArrayAdapter<String> listadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        list.setAdapter(listadapter);
        OrdersDB h = new OrdersDB(this);
        String username = getIntent().getStringExtra("username");
        final Cursor cursor = h.ShowHistoryOrders(username);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();


            for (int i = 0; i < cursor.getCount(); i++) {
                String str = cursor.getString(1) + " Order Id: " + cursor.getString(0).toString() + " Status: " + cursor.getString(3).toString() + "\nDetails: " + cursor.getString(2).toString();
                listadapter.add(str);
                cursor.moveToNext();
            }


        } else {
            listadapter.add("NO PAST ORDERS");

        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                int position = list.getSelectedItemPosition();
                cursor.move(position);
                Intent inn = new Intent(ViewOrders.this, OrderItems.class);
                inn.putExtra("OrderID", cursor.getString(0));
                startActivity(inn);
            }
        });
}
}