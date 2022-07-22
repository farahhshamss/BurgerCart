package com.prj.burgercart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.myapplication.R;

import java.io.Serializable;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        GridView grid_menu = (GridView)findViewById(R.id.grd_menu);
        Button btn_checkout = (Button) findViewById(R.id.btn_checkout);

        OrdersDB db = new OrdersDB(this);
        final Cart cart = new Cart();
        final String username=getIntent().getStringExtra("username");
        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent(Menu.this,OrdersCart.class);
                inn.putExtra("username",username);
                inn.putExtra("cart",cart);
                startActivity(inn);
                finish();
            }
        });
        menuadapter menuAdapter = new menuadapter(getApplicationContext(),R.layout.menuitem,cart,username);
        Cursor cursor =db.getMenu();
        do {
            menuAdapter.AddItem(new MenuItem(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));

        }while(cursor.moveToNext());


        grid_menu.setAdapter(menuAdapter);

        registerForContextMenu(grid_menu);
    }
}