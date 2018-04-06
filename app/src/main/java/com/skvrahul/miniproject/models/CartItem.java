package com.skvrahul.miniproject.models;

import java.io.Serializable;

/**
 * Created by skvrahul on 30/3/18.
 */

//A minor addition to the Inventory model to represent data about the qty in a customers order
//Note: This model is purely for the Frontend purpose and isn't linked with the backend DB

public class CartItem implements Serializable{
    private Inventory item;
    private int qty;

    public CartItem(){}
    public CartItem(Inventory item){
        this.item = item;
        this.qty = 1;
    }
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

    public void incrementQty(){
        this.qty += 1;
    }
    public void incrementQty(int n){
        this.qty += n;

    }

}

