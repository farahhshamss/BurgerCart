package com.prj.burgercart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.HashMap;

public class OrdersCart extends AppCompatActivity {
    Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_cart);
        GridView grid = (GridView) findViewById(R.id.GV);
        Button makeorder = (Button) findViewById(R.id.createorder);
        final TextView details = (TextView) findViewById(R.id.orderdetailstexxt);
        final String username = getIntent().getStringExtra("username");
        cart = (Cart) getIntent().getSerializableExtra("cart");
        OrderItemAdapter Item = new OrderItemAdapter(getApplicationContext(), R.layout.activity_orders_cart);
        for (MenuItem item : cart.AllOrderItems.values()) {
            Item.AddItem(item);
        }

        grid.setAdapter(Item);
        makeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart.EndAddingToCart(details.getText().toString(), username, getApplicationContext());
                finish();
            }
        });
    }
}