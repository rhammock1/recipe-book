import express from 'express';
import { getRecipes, getRecipeByUuid, createRecipe, updateRecipe, deleteRecipe } from '../actions/recipes';
import recipesPage from "../../pages/recipes.marko";

const router = express.Router();

// STUBS
router.route('/')
  .get(async (req, res) => {
    const recipes = await getRecipes();
    res.marko(recipesPage, { recipes });
  })
  .post(async (req, res) => {
    const recipe = await createRecipe(req.body);
    res.json(recipe);
  })

export default router;
