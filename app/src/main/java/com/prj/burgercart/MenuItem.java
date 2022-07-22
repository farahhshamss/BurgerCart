package com.prj.burgercart;

import java.io.Serializable;

public class MenuItem implements Serializable {
    public int ID,Price,Quantity;
    public String Title,Description;

    public MenuItem(int ID,String title, String description, int price) {
        this.ID = ID;
        Price = price;
        Title = title;
        Description = description;
        Quantity=1;
    }
    public MenuItem(int ID,String title, String description, int price,int quantity) {
        this.ID = ID;
        Price = price;
        Title = title;
        Description = description;
        Quantity=quantity;
    }
}
