package com.rhammock.recipebook;

public class IngredientModel {
    private int ingredientId;
    private int recipeId;

    private String ingredientName;
    private float ingredientQuantity;
    private String ingredientUnit;
    private String imageUrl;

    public IngredientModel(int ingredientId, int recipeId, String ingredientName, float ingredientQuantity, String ingredientUnit, String imageUrl) {
        this.ingredientId = ingredientId;
        this.recipeId = recipeId;
        this.ingredientName = ingredientName;
        this.ingredientQuantity = ingredientQuantity;
        this.ingredientUnit = ingredientUnit;
        this.imageUrl = imageUrl;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public float getIngredientQuantity() {
        return ingredientQuantity;
    }

    public String getIngredientUnit() { return ingredientUnit; }

    public String getImageUrl() {
        return imageUrl;
    }
}
