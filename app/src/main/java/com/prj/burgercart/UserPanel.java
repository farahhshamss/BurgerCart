package com.prj.burgercart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class UserPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpanel);
        Button showorders=(Button)findViewById(R.id.userordersbutton);
        Button add=(Button)findViewById(R.id.addneworderbutton);
        Button edit=(Button)findViewById(R.id.editprofilebutton);
        showorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent(UserPanel.this, ViewOrders.class);
                String username=getIntent().getStringExtra("username");
                inn.putExtra("username",username);
                startActivity(inn);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent(UserPanel.this, Menu.class);
                String username=getIntent().getStringExtra("username");
                inn.putExtra("username",username);
                startActivity(inn);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent(UserPanel.this, EditUserProfile.class);
                String username=getIntent().getStringExtra("username");
                inn.putExtra("UserLoggedIn",username);
                inn.putExtra("username",username);
                startActivity(inn);
            }
        });
    }
}