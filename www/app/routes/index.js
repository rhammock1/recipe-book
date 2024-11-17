import express from 'express';
import {search} from '../actions/index';
import {getRecipeByUuid} from '../actions/recipes';
import recipes from './recipes';
import indexPage from "../../pages/index.marko";

const router = express.Router();

router.get('/', async (req, res) => {
  const {query: {recipe_uuid}} = req;
  const selected_recipe = recipe_uuid ? await getRecipeByUuid(recipe_uuid) : null;

  res.marko(indexPage, {selected_recipe});
});

router.use('/recipes', recipes);

router.get('/search', async (req, res, next) => {
  const {q} = req.query;
  try {
    const results = await search(q);

    res.json(results);
  } catch(err) {
    next(err);
  }
})

router.use((err, _req, res, _next) => {
  console.error(err);
  res.status(500).send('Internal Server Error');
});


export default router;