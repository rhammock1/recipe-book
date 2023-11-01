package com.rhammock.recipebook;

public class RecipeModel {
    private int recipeId;
    private String recipeName;
    private String imageUrl;
    private String recipePrepTime;
    private String recipeCookTime;

    // Constructors
    public RecipeModel(int recipeId, String recipeName, String recipePrepTime, String recipeCookTime, String imageUrl) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.imageUrl = imageUrl;
        this.recipePrepTime = recipePrepTime;
        this.recipeCookTime = recipeCookTime;
    }

    // toString is used for printing model data
    @Override
    public String toString() {
        return "RecipeModel{" +
                "recipeId=" + recipeId +
                ", recipeName='" + recipeName + '\'' +
                ", recipePrepTime='" + recipePrepTime + '\'' +
                ", recipeCookTime='" + recipeCookTime + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    // Getters
    public int getRecipeId() {
        return recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getRecipePrepTime() {
        return recipePrepTime;
    }

    public String getRecipeCookTime() {
        return recipeCookTime;
    }
}
