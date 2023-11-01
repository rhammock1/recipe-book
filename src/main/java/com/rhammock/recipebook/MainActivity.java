package com.rhammock.recipebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.rhammock.recipebook.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // References to UI controls
    // Eventually add in list filter controls
    RecyclerView recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipeList = findViewById(R.id.recipeList);
        recipeList.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        DatabaseHelper helper = new DatabaseHelper(MainActivity.this);

        List<RecipeModel> recipes = helper.getAllRecipes();

        RecipeListAdapter recipeListAdapter = new RecipeListAdapter(MainActivity.this, recipes);
        recipeList.setAdapter(recipeListAdapter);

//         helper.seedDatabase();
    }
}