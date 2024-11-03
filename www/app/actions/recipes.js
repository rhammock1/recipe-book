import db from '../../db';

const getRecipes = async () => {
  const {rows: recipes} = await db.file('db/recipes/get_recipes.sql');
  return recipes;
};

const getRecipeByUuid = async (uuid) => {
  const {rows: [recipe]} = await db.file('db/recipes/get_recipe_by_uuid.sql', {recipe_uuid: uuid});

  return recipe;
};

const createRecipe = async (recipe) => {
  const {rows: [newRecipe]} = await db.file('db/recipes/create_recipe.sql', recipe);
  return newRecipe;
};

const updateRecipe = async (uuid, recipe) => {
  const {rows: [updatedRecipe]} = await db.file('db/recipes/update_recipe.sql', { uuid, ...recipe });
  return updatedRecipe;
};

const deleteRecipe = async (uuid) => {
  await db.file('db/recipes/delete_recipe.sql', { uuid });
};

export {
  getRecipes,
  getRecipeByUuid,
  createRecipe,
  updateRecipe,
  deleteRecipe,
};