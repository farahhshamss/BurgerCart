package com.prj.burgercart;

public class order {
    private String id,info,desc,date;
    public order(String id,String info,String date)
    {
        this.date=date;
        this.id=id;

        this.info=info;

    }

    public String getDate() {

        return date;
    }


    public String getId() {
        return id;
    }

    public String getInfo() {
        return info;
    }
}
