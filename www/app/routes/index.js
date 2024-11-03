import express from 'express';
import {search} from '../actions/index';
import recipes from './recipes';

const router = express.Router();

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