UPDATE recipes
SET recipe_name = COALESCE(${recipe_name}, recipe_name),
  recipe_description = COALESCE(${recipe_description}, recipe_description),
  recipe_image = COALESCE(${recipe_image}, recipe_image),
  recipe_yield = COALESCE(${recipe_yield}, recipe_yield),
  recipe_yield_unit = COALESCE(${recipe_yield_unit}, recipe_yield_unit)
WHERE recipe_uuid = ${recipe_uuid}
RETURNING *;
