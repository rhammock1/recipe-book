import express from 'express';
import {getRecipeByUuid} from '../actions/recipes';
import { getIngredientsByRecipeUuid } from '../actions/ingredients';
import { getInstructionsByRecipeUuid } from '../actions/instructions';
import recipePage from "../../pages/recipe.marko";

const router = express.Router();

// STUBS
router.route('/:recipe_uuid')
  .get(async (req, res) => {
    const {recipe_uuid} = req.params;
    const recipe = await getRecipeByUuid(recipe_uuid);
    res.marko(recipePage, { recipe });
  })
  .put(async (req, res) => {
    const recipe = await updateRecipe(req.params.recipe_uuid, req.body);
    res.json(recipe);
  })
  .delete(async (req, res) => {
    await deleteRecipe(req.params.recipe_uuid);
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