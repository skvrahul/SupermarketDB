package com.skvrahul.miniproject.models;

/**
 * Created by skvrahul on 30/3/18.
 */

//A minor addition to the Inventory model to represent data about the qty in a customers order
//Note: This model is purely for the Frontend purpose and isn't linked with the backend DB

public class CartItem {
    private Inventory item;
    private int qty;

    public Inventory getItem() {
        return item;
    }

    public void setItem(Inventory item) {
        this.item = item;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}

