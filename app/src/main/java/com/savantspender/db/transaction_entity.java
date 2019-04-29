package com.savantspender.db;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.room.TypeConverters;

import com.savantspender.db.entity.Tag;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;



@Entity(tableName = "transactions")
public class transaction_entity {
    @PrimaryKey
    protected String idnumber;
    protected String vendor;
    protected String location;
    protected int day;
    protected String monthyear;
    protected int amount;
    protected String pendingID;
    @Relation(parentColumn = "transid",entityColumn = "transaction_id")
    protected List<Tag> tags;


    public boolean ispending() {if (pendingID == "") {return false;} else {return true;}}

    public transaction_entity(String transid,String acountid, String vendor, String location, String date,String amount,String[] tags,String pendingID)
    {
        famount = Float.parseFloat(amount) * 100;
        this.amount = (int)famount;
        String [] parseddate = date.split("/");
        this.day = Integer.parseInt(parseddate[0]);
        this.monthyear = parseddate[1] + "-" + parseddate[2];
        this.idnumber = transid + acountid;
        this.tags.addALl(tags);
        this.vendor = vendor;
        this.location = location;
        this.pendingID = pendingID
    }
    public float get_amount() {return flaot(this.amount/100);}
    public String get_vendor() {return this.vendor;}

    public String get_location() {return
            if (this.location == "")
            {return "None";}
            else
            {return this.location;}
    }
    public String get_date() {
        String [] datelist = this.monthyear.split("-");
        return Integer.toString(this.day) + "/" + datelist[0] + "/" + datelist[1];
    }
    public List<Tag> get_tags(){return this.tags;}
}




