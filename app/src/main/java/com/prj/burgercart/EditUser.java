package com.prj.burgercart;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class EditUser extends AppCompatActivity {
   ArrayAdapter<String> listadapter;
    OrdersDB or;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user)  ;
        list = (ListView) findViewById(R.id.listt);
      listadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        list.setAdapter(listadapter);
        or = new OrdersDB(this);
        Cursor cursor =or.ShowAllUsers();
        while(!cursor.isAfterLast()) {
            listadapter.add(cursor.getString(0));
            cursor.moveToNext();
        }
        registerForContextMenu(list);

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater IN=getMenuInflater();
        IN.inflate(R.menu.edit_user_menu,menu);
        menu.setHeaderTitle("Actions");

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo inffo=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int position=inffo.position;
        String UserName  =listadapter.getItem(position);

        if(item.getItemId()==R.id.Delete)
        {
            or.DeleteUser(UserName);
            listadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
            list.setAdapter(listadapter);
            Cursor cursor =or.ShowAllUsers();
            while(!cursor.isAfterLast()) {
                listadapter.add(cursor.getString(0));
                cursor.moveToNext();
            }
            Toast.makeText(getApplicationContext(),UserName+" has been deleted" ,Toast.LENGTH_LONG).show();
        }
        if(item.getItemId()==R.id.EditUserMenu)
        {
            Intent inn = new Intent(EditUser.this, EditUserProfile.class);
            inn.putExtra("username",UserName);
            String UserLoggedIn=getIntent().getStringExtra("UserLoggedIn");
            inn.putExtra("UserLoggedIn",UserLoggedIn);
            startActivity(inn);
            return true;

        }
        if(item.getItemId()==R.id.Show)
        {
            Intent inn = new Intent(EditUser.this, ViewOrders.class);
            inn.putExtra("username",UserName);
            startActivity(inn);
            return true;
        }
        return false;
    }
}
