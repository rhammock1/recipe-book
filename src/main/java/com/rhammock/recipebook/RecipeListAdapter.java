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

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {
    private List<RecipeModel> data;
    private Activity context;

    public RecipeListAdapter(Activity activity, List<RecipeModel> data) {
        this.data = data;
        this.context = activity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeName;
        public TextView recipePrepTime;
        public TextView recipeCookTime;
        public Button viewRecipeButton;

        public ViewHolder(View view) {
            super(view);
            recipeName = view.findViewById(R.id.tv_recipe_name);
            recipePrepTime = view.findViewById(R.id.tv_recipe_prep_time);
            recipeCookTime = view.findViewById(R.id.tv_recipe_cook_time);
            viewRecipeButton = view.findViewById(R.id.btn_view_recipe);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        RecipeModel recipe = data.get(position);
        holder.recipeName.setText(recipe.getRecipeName());
        holder.recipePrepTime.setText(recipe.getRecipePrepTime());
        holder.recipeCookTime.setText(recipe.getRecipeCookTime());
        holder.viewRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement onclick logic here (change Activity to display the recipe)
                Intent intent = new Intent(context, RecipeActivity.class);
                intent.putExtra("recipeId", Integer.toString(recipe.getRecipeId()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
