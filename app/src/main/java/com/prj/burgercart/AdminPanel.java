package com.prj.burgercart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class AdminPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpanel);
        ListView mylist = (ListView)findViewById(R.id.listo);
        String[] myarr = getResources().getStringArray(R.array.list);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myarr);
         mylist.setAdapter(adapter);
       mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
               TextView v=(TextView) view;
               if(((TextView) view).getText().toString().equals("Order")) {
                   Intent inn = new Intent(AdminPanel.this, Menu.class);
                   String username=getIntent().getStringExtra("username");
                   inn.putExtra("username",username);
                   startActivity(inn);
               }
               if(((TextView) view).getText().toString().equals("Waiting")) {
                   Intent inn = new Intent(AdminPanel.this, PendingOrders.class);
                   startActivity(inn);
               }
               if(((TextView) view).getText().toString().equals("Users")) {
                   Intent inn = new Intent(AdminPanel.this, EditUser.class);
                   String UserLoggedIn=getIntent().getStringExtra("username");
                   inn.putExtra("UserLoggedIn",UserLoggedIn);
                   startActivity(inn);
               }
               if(((TextView) view).getText().toString().equals("History")) {
                   Intent inn = new Intent(AdminPanel.this,ViewOrders.class);
                   String username=getIntent().getStringExtra("username");
                   inn.putExtra("username",username);
                   startActivity(inn);
               }
           }
       }
       );
    }
}