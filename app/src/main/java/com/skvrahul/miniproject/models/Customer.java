package com.skvrahul.miniproject.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by pavan on 30/3/18.
 */
@Entity
public class Customer {
    @PrimaryKey
    private int c_id;


    @ColumnInfo(name = "phone_number")
    private String phoneNum;
    @ColumnInfo(name = "c_name")
    private String custName;


    public Customer(){

    }
    public Customer(String name){
        this.custName = name;
    }
    public Customer(String name, int c_id){
        this.custName = name;
        this.c_id  = c_id;

    }
    public Customer(String name, int c_id, String phoneNum)
    {
        this.c_id=c_id;
        this.custName=name;
        this.phoneNum = phoneNum;
    }


    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }


}
