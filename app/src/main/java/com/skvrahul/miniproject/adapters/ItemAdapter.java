package com.skvrahul.miniproject.adapters;



import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.models.Inventory;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    public interface DeleteClickListener{
        void onClick(Inventory item);
    }
    private List<Inventory> items;
    private DeleteClickListener deleteClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price, units,itemId;



        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.cat_name);
            units = (TextView) view.findViewById(R.id.item_units);
            price = (TextView) view.findViewById(R.id.item_price);
            itemId = view.findViewById(R.id.cat_id);



        }
    }


    public ItemAdapter(List<Inventory> items) {
        this.items = items;
    }

    public ItemAdapter(List<Inventory> items, DeleteClickListener deleteClickListener) {
        this.items = items;
        this.deleteClickListener = deleteClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Inventory item = items.get(position);
        holder.name.setText(item.getItemName());
        holder.units.setText(item.getStock()+" Units");
        holder.price.setText("Rs. "+item.getPrice()+"");
        holder.itemId.setText("ID: "+item.getItem_id());
        /*
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deleteClickListener!=null){
                    deleteClickListener.onClick(item);
                }
            }
        });
        */

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}