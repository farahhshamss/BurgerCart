package com.prj.burgercart;

import android.content.Context;

import com.example.myapplication.R;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Cart implements Serializable {
   public HashMap<Integer, MenuItem> AllOrderItems;

    Cart() {
        AllOrderItems = new HashMap<Integer, MenuItem>();
    }

    public void AddItem(MenuItem menuItem) {
        if (AllOrderItems.containsKey(menuItem.ID)) {
            MenuItem CurrentItem = AllOrderItems.get(menuItem.ID);
            CurrentItem.Quantity++;
            AllOrderItems.put(menuItem.ID, CurrentItem);
        } else {
            AllOrderItems.put(menuItem.ID, menuItem);
        }

    }
    public void EndAddingToCart(String ordersdetails, String Username, Context context) {

        OrdersDB neworder = new OrdersDB(context);
        int UserID = neworder.getUserId(Username);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String formattedDate = df.format(c.getTime());
        int OrderID = neworder.CreateNewOrder(formattedDate, ordersdetails, UserID);
        for (MenuItem item : AllOrderItems.values()) {
            neworder.CreateOrderItems(OrderID, item.ID, item.Quantity);
        }
    }
}




