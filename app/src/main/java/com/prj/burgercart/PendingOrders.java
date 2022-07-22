package com.prj.burgercart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;

public class PendingOrders extends AppCompatActivity {
    ListView ordersList;
    orderAdapter or;
    OrdersDB orders;
String pos;
    String name,id,info,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendingorders);
        ordersList = (ListView)findViewById(R.id.listview11);
        orders = new OrdersDB(getApplicationContext());
        or = new orderAdapter(this,R.layout.row_layout);
        ordersList.setAdapter(or);
        Cursor cursor = orders.fetchAllOrders();

        if(cursor.moveToFirst()){
            do {
                id = cursor.getString(0);
                info = cursor.getString(2);
                date = cursor.getString(1);
                order orr = new order(id, info, date);
                or.add(orr);

            }while(cursor.moveToNext());
        }

        registerForContextMenu(ordersList);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater IN=getMenuInflater();
        IN.inflate(R.menu.contextmenu,menu);
        menu.setHeaderTitle("Change Order status");

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo inffo=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int position=inffo.position;

        order c= (order) or.getItem(position);
        pos=   c.getId();

        if(item.getItemId()==R.id.farah)
        {
            Toast.makeText(this,"Status has been changed",Toast.LENGTH_LONG).show();
            orders.Searching(pos);
            or = new orderAdapter(this,R.layout.row_layout);
            ordersList.setAdapter(or);
            Cursor cursor = orders.fetchAllOrders();

            if(cursor.moveToFirst()){
                do {
                    id = cursor.getString(0);
                    info = cursor.getString(2);
                    date = cursor.getString(1);
                    order orr = new order(id, info, date);

                    or.add(orr);

                }while(cursor.moveToNext());
            }
            return true;
        }

        return false;
    }
}