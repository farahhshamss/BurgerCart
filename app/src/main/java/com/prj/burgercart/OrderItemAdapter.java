package com.prj.burgercart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

import java.util.ArrayList;

public class OrderItemAdapter  extends ArrayAdapter {
    ArrayList list=new ArrayList();

    public OrderItemAdapter(Context con, int resource)
    {
        super(con,resource);

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
        convertView = lf.inflate(R.layout.order_items, parent, false);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        MenuItem item = (MenuItem) this.getItem((position));
        title.setText(item.Title);
        price.setText(String.valueOf(item.Quantity+"X"+item.Price+" LE"));
        description.setText(item.Description);
        return convertView;
    }
}
