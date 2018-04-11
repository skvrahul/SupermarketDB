package com.skvrahul.miniproject.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;


/**
 * Created by pavan on 31/3/18.
 */
@Entity(
        primaryKeys = { "invoice_id", "item_id" },
        foreignKeys = {
                @ForeignKey(entity = Inventory.class,
                        parentColumns = "item_id",
                        childColumns = "item_id",
                        onUpdate=CASCADE,
                        onDelete = CASCADE),
                @ForeignKey(entity = Invoice.class,
                        parentColumns = "invoice_id",
                        childColumns = "invoice_id",
                        onUpdate=CASCADE,
                        onDelete = CASCADE)
        })

public class Contains {
    @NonNull
    @ColumnInfo(name="invoice_id")
    private int invoice_id;

    @NonNull
    @ColumnInfo(name="item_id")
    private String item_id;

    @ColumnInfo(name="quantity")
    private int quantity;

    public Contains() {}

    public Contains(int invoice_id,String item_id, int quantity)
    {
        this.invoice_id = invoice_id;
        this.item_id = item_id;
        this.quantity = quantity;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
