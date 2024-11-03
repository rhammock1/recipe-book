import express from 'express';
import { getRecipes, getRecipeByUuid, createRecipe, updateRecipe, deleteRecipe } from '../actions/recipes';

const router = express.Router();

// STUBS
router.route('/')
  .get(async (req, res) => {
    const recipes = await getRecipes();
    res.json({ recipes });
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

export default router;