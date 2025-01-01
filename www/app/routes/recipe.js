import express from 'express';
import {getRecipeByUuid} from '../actions/recipes';
import recipePage from "../../pages/recipe.marko";

const router = express.Router();

// STUBS
router.route('/:recipe_uuid')
  .get(async (req, res) => {
    const {recipe_uuid} = req.params;
    const recipe = await getRecipeByUuid(recipe_uuid);
    res.marko(recipePage, { recipe });
  });

export default router;