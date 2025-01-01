import express from 'express';
import {search} from '../actions/index';
import {getRecipeByUuid} from '../actions/recipes';
import recipeRouter from './recipe';
import recipesRouter from './recipes';
import homePage from "../../pages/home.marko";
import recipesPage from "../../pages/recipes.marko";

const router = express.Router();

router.get('/', async (req, res) => {
  const {query: {recipe_uuid}} = req;
  const selected_recipe = recipe_uuid ? await getRecipeByUuid(recipe_uuid) : null;

  res.marko(homePage, {selected_recipe});
});

router.use('/recipe', recipeRouter);
router.use('/recipes', recipesRouter);

router.get('/search', async (req, res, next) => {
  const {q} = req.query;
  try {
    const results = await search(q);

    res.marko(recipesPage, {recipes: results});
  } catch(err) {
    next(err);
  }
})

router.use((err, _req, res, _next) => {
  console.error(err);
  res.status(500).send('Internal Server Error');
});


export default router;