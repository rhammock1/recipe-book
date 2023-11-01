package com.rhammock.recipebook;

public class RecipeStepModel {
    private int recipeStepId;
    private String content;
    private String imageUrl;
    private int recipeId;
    private int recipeStepNumber;

    public RecipeStepModel(int recipeStepId, String content, String imageUrl, int recipeId, int recipeStepNumber) {
        this.recipeStepId = recipeStepId;
        this.content = content;
        this.imageUrl = imageUrl;
        this.recipeId = recipeId;
        this.recipeStepNumber = recipeStepNumber;
    }

    public int getRecipeStepId() {
        return recipeStepId;
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public int getRecipeStepNumber() {
        return recipeStepNumber;
    }
}
