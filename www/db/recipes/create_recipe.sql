INSERT INTO recipes (
  recipe_name,
  recipe_description,
  recipe_image,
  recipe_yield,
  recipe_yield_unit,
  source
) VALUES (
  ${recipe_name},
  ${recipe_description},
  ${recipe_image},
  ${recipe_yield},
  ${recipe_yield_unit},
  'CUSTOM'
) ON CONFLICT (recipe_name) DO UPDATE
SET recipe_description = COALESCE(${recipe_description}, recipe_description),
  recipe_image = COALESCE(${recipe_image}, recipe_image),
  recipe_yield = COALESCE(${recipe_yield}, recipe_yield),
  recipe_yield_unit = COALESCE(${recipe_yield_unit}, recipe_yield_unit)
RETURNING recipe_id;
