package com.skvrahul.miniproject.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;
/**
 * Created by pavan on 31/3/18.
 */
@Entity
public class Invoice {
    @PrimaryKey
    private int invoice_id;

    @ForeignKey(entity = Customer.class, parentColumns = "c_id",childColumns = "c_id",onUpdate = CASCADE,onDelete = CASCADE)
    private int c_id;

    @ForeignKey(entity = Employee.class, parentColumns = "e_id",childColumns = "e_id",onUpdate = CASCADE,onDelete = CASCADE)
    private int e_id;

    @ColumnInfo(name = "purchase_date")
    private String purchaseDate;

    @ColumnInfo(name = "tot_cost")
    private double totCost;


    public Invoice() {}

    public Invoice(int invoice_id, int e_id,int c_id,String purchaseDate,double totCost) {
        this.invoice_id=invoice_id;
        this.e_id = e_id;
        this.c_id = c_id;
        this.purchaseDate = purchaseDate;
        this.totCost = totCost;
    }

    public double getTotCost() {
        return totCost;
    }

    public int getC_id() {
        return c_id;
    }

    public int getE_id() {
        return e_id;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setTotCost(double totCost) {
        this.totCost = totCost;
    }

}
