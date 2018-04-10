package com.skvrahul.miniproject.adapters;

import android.support.v7.widget.RecyclerView;

/**
 * Created by pavan on 10/4/18.
 */
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.models.Category;


import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{
    private List<Category> categories;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,catId;
        //public ImageView delete, productImage;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.cat_name);
            catId = (TextView) view.findViewById(R.id.cat_id);
            //delete = view.findViewById(R.id.cart_item_delete);


        }

    }
    public CategoryAdapter(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View catView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category, parent, false);

        return new CategoryAdapter.MyViewHolder(catView);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.MyViewHolder holder, int position) {
        final Category cat = categories.get(position);
        holder.name.setText(cat.getCatName());

        holder.catId.setText("ID: "+cat.getCat_id());


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }




}
