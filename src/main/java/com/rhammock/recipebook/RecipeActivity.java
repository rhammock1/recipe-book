package com.rhammock.recipebook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rhammock.recipebook.adapters.IngredientListAdapter;

import java.util.List;

public class RecipeActivity extends AppCompatActivity {
    private RecipeModel recipe;
    private List<IngredientModel> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_activity);

        Button backButton = findViewById(R.id.BackButton);
        RecyclerView ingredientList = findViewById(R.id.IngredientList);
        ingredientList.setLayoutManager(new LinearLayoutManager(RecipeActivity.this));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBackButton();
            }
        });

        // Retrieve the data from the intent
        Intent intent = getIntent();
        if (intent != null) {
            String recipeId = intent.getStringExtra("recipeId") == null ? "1" : intent.getStringExtra("recipeId");

            DatabaseHelper helper = new DatabaseHelper(this);
            if(recipeId != null) {
                int recipe_id = Integer.parseInt(recipeId);
                this.recipe = helper.getRecipe(recipe_id);
                this.ingredients = helper.getIngredientsByRecipeId(recipe_id);
            }

            Log.d("RECIPE", "RECIPE ID: " + recipeId);

            if(this.recipe != null) {
                TextView recipeNameView = findViewById(R.id.DynRecipeName);
                recipeNameView.setText(this.recipe.getRecipeName());

                TextView recipePrepTimeView = findViewById(R.id.DynPrepTime);
                recipePrepTimeView.setText(this.recipe.getRecipePrepTime());

                TextView recipeCookTimeView = findViewById(R.id.DynCookTime);
                recipeCookTimeView.setText(this.recipe.getRecipeCookTime());
            }

            IngredientListAdapter ingredientListAdapter = new IngredientListAdapter(RecipeActivity.this, this.ingredients);
            ingredientList.setAdapter(ingredientListAdapter);
        }
    }

    private void handleBackButton() {
        // Go Back to the main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Close the current activity
    }
}