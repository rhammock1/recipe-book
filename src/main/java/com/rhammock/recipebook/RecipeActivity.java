package com.rhammock.recipebook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeActivity extends AppCompatActivity {
    private RecipeModel recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_activity);

        Button backButton = findViewById(R.id.BackButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBackButton();
            }
        });

        // Retrieve the data from the intent
        Intent intent = getIntent();
        if (intent != null) {
            String recipeId = intent.getStringExtra("recipeId");

            DatabaseHelper helper = new DatabaseHelper(this);
            if(recipeId != null) {
                this.recipe = helper.getRecipe(Integer.parseInt(recipeId));
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
        }
    }

    private void handleBackButton() {
        // Go Back to the main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Close the current activity
    }
}