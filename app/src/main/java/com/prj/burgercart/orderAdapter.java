package com.prj.burgercart;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class orderAdapter extends ArrayAdapter {
  ArrayList list=new ArrayList();
  Context con;
    public orderAdapter(Context con,int resource)
    {
        super(con,resource);
        this.con = con;
    }


    public void add(order or) {
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
        View row = convertView;
            LayoutInflater lf = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = lf.inflate(R.layout.row_layout, parent, false);
            TextView  id = (TextView) row.findViewById(R.id.orderId);
            TextView  date = (TextView) row.findViewById(R.id.orderdate);
            TextView   info = (TextView) row.findViewById(R.id.orderinfo);

        final order ORDER = (order) this.getItem((position));
       id.setText(ORDER.getId().toString());
        date.setText(ORDER.getDate().toString());
       info.setText(ORDER.getInfo().toString());
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent(con, OrderItems.class);
                inn.putExtra("OrderID",ORDER.getId());
                con.startActivity(inn);
            }
        });
        return row;
    }
}
