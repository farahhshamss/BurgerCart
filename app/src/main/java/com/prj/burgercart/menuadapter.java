package com.prj.burgercart;

import android.content.Context;
import android.os.Debug;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class menuadapter extends ArrayAdapter {
    ArrayList list=new ArrayList();
    Cart Cart;
    String username;
    public menuadapter(Context con,int resource,Cart cart,String Username) {
        super(con, resource);
        this.Cart = cart;
        this.username = Username;
    }

    public void AddItem(MenuItem or) {
        list.add(or);
        super.add(or);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater lf = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = lf.inflate(R.layout.menuitem, parent, false);


        TextView title = (TextView) convertView.findViewById(R.id.txt_title);
        TextView price = (TextView) convertView.findViewById(R.id.txt_price);
        TextView description = (TextView) convertView.findViewById(R.id.txt_description);
        Button add = (Button)convertView.findViewById(R.id.btn_add);
        final MenuItem item = (MenuItem) this.getItem((position));

        title.setText(item.Title);
        price.setText(String.valueOf(item.Price));
        description.setText(item.Description);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart.AddItem(item);
            }
        });
        return convertView;
    }
}
