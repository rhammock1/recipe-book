import db from '../../db';

const getIngredientsByRecipeUuid = async (recipe_uuid) => {
  const {rows: ingredients} = await db.file('db/ingredients/get_by_recipe_uuid.sql', {recipe_uuid});
  return ingredients;
};

export {getIngredientsByRecipeUuid};

