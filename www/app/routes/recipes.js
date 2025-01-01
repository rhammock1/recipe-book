import express from 'express';
import { getRecipes, getRecipeByUuid, createRecipe, updateRecipe, deleteRecipe } from '../actions/recipes';
import { getIngredientsByRecipeUuid } from '../actions/ingredients';
import { getInstructionsByRecipeUuid } from '../actions/instructions';
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

router.route('/:uuid')
  .get(async (req, res) => {
    const recipe = await getRecipeByUuid(req.params.uuid);
    res.json({ recipe });
  })
  .put(async (req, res) => {
    const recipe = await updateRecipe(req.params.uuid, req.body);
    res.json(recipe);
  })
  .delete(async (req, res) => {
    await deleteRecipe(req.params.uuid);
    res.status(204).send();
  });

router.get('/:recipe_uuid/ingredients', async (req, res) => {
  const {params: {recipe_uuid}} = req;
  const ingredients = await getIngredientsByRecipeUuid(recipe_uuid);
  res.json(ingredients);
});

router.get('/:recipe_uuid/instructions', async (req, res) => {
  const {params: {recipe_uuid}} = req;
  const instructions = await getInstructionsByRecipeUuid(recipe_uuid);
  res.json(instructions);
});

export default router;
