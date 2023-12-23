package com.rhammock.recipebook;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.ViewHolder> {
    private List<IngredientModel> data;
    private Activity context;

    public IngredientListAdapter(Activity activity, List<IngredientModel> data) {
        this.data = data;
        this.context = activity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ingredientName;
        public TextView ingredientQuantity;
        public TextView ingredientUnit;

        public ViewHolder(View view) {
            super(view);
            ingredientName = view.findViewById(R.id.ili_ingredient_name);
            ingredientQuantity = view.findViewById(R.id.ili_ingredient_quantity);
            ingredientUnit = view.findViewById(R.id.ili_ingredient_unit);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        IngredientModel ingredient = data.get(position);
        holder.ingredientName.setText(ingredient.getIngredientName());
        holder.ingredientQuantity.setText(Float.toString(ingredient.getIngredientQuantity()));
        holder.ingredientUnit.setText(ingredient.getIngredientUnit());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
