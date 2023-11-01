package com.rhammock.recipebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "recipe-book.db", null, 1);
    }

    // This is called the first time a database is accessed. Should include code to create a new db
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createRecipesTable = "CREATE TABLE recipes (" +
                "recipe_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name TEXT NOT NULL," +
                " prep_time TEXT NOT NULL," +
                " cook_time TEXT NOT NULL," +
                " image_url TEXT" +
                ")";
        String createIngredientsTable = "CREATE TABLE ingredients (" +
                "ingredient_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " recipe_id INTEGER NOT NULL," +
                " name TEXT NOT NULL," +
                " quantity REAL NOT NULL," +
                " unit TEXT NOT NULL," +
                " image_url TEXT," +
                " FOREIGN KEY(recipe_id) REFERENCES recipes(recipe_id)" +
                ")";
        String createRecipeStepsTable = "CREATE TABLE recipe_steps(" +
                "recipe_step_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " recipe_id INTEGER NOT NULL," +
                " content TEXT NOT NULL," +
                " step_number INTEGER NOT NULL," +
                " image_url TEXT," +
                " FOREIGN KEY(recipe_id) REFERENCES recipes(recipe_id)" +
                ")";

        db.execSQL(createRecipesTable);
        db.execSQL(createIngredientsTable);
        db.execSQL(createRecipeStepsTable);
    }

    // This is called when the database version changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void seedDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Seed database tables [TEMPORARY]
        String[] recipe_names = {"Flapjacks", "Corn Dogs", "Mac N Cheese", "PBJ", "Oatmeal"};
        for (int i = 0; i < 5; i++) {
            ContentValues cv = new ContentValues();
            cv.put("name", recipe_names[i]);
            cv.put("prep_time", "5");
            cv.put("cook_time", "10");

            long recipe_seed_result = db.insert("recipes", null, cv);
            if(recipe_seed_result == -1) {
                Log.d("[DB]", "Failed to seed recipes table");
            }
        }
        String[] ingredient_names = {"Salmon", "Mayo", "Ketchup", "Mustard", "Relish"};
        for (int i = 0; i < 5; i++) {
            ContentValues cv = new ContentValues();
            cv.put("recipe_id", i+1);
            cv.put("name", ingredient_names[i]);
            cv.put("quantity", "1");
            cv.put("unit", "ea");

            long ingredient_seed_result = db.insert("ingredients", null, cv);
            if(ingredient_seed_result == -1) {
                Log.d("[DB]", "Failed to seed ingredients table");
            }
        }
        for (int i = 0; i < 5; i++) {
            ContentValues cv = new ContentValues();
            cv.put("recipe_id", i+1);
            cv.put("content", "Mix for 5 minutes");
            cv.put("step_number", i+1);

            long recipe_steps_seed_result = db.insert("recipe_steps", null, cv);
            if(recipe_steps_seed_result == -1) {
                Log.d("[DB]", "Failed to seed recipe_steps table");
            }
        }
        Log.d("[DB]", "Successfully setup database");
    }

    public List<RecipeModel> getAllRecipes() {
        Log.d("[DB]", "Getting all recipes");

        List<RecipeModel> recipes = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT recipe_id, name, prep_time, cook_time, image_url FROM recipes;";

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            try {
                do {
                    int recipe_id = cursor.getInt(cursor.getColumnIndexOrThrow("recipe_id"));
                    String recipe_name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String recipe_prep_time = cursor.getString(cursor.getColumnIndexOrThrow("prep_time"));
                    String recipe_cook_time = cursor.getString(cursor.getColumnIndexOrThrow("cook_time"));
                    String image_url = cursor.getString(cursor.getColumnIndexOrThrow("image_url"));

                    RecipeModel recipe = new RecipeModel(recipe_id, recipe_name, recipe_prep_time, recipe_cook_time, image_url);
                    recipes.add(recipe);
                } while(cursor.moveToNext());
            } catch(Exception e) {
                // Note to self: all exceptions are descended from Exception
                Log.e("[DB]", "Failed to get recipes" + e);
            }
        }
        cursor.close();
        db.close();
        return recipes;
    }
}
