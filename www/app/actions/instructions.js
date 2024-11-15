import db from '../../db';

const getInstructionsByRecipeUuid = async (recipe_uuid) => {
  const {rows: instructions} = await db.file('db/instructions/get_by_recipe_uuid.sql', {recipe_uuid});
  return instructions;
};

export {getInstructionsByRecipeUuid};

