SELECT r.recipe_uuid,
  r.recipe_name,
  r.recipe_description,
  r.recipe_image,
  r.recipe_yield,
  r.headline,
  u.unit_name AS recipe_yield_unit,
  go_get_recipe_tags(r.recipe_uuid) AS recipe_tags
FROM recipes r
LEFT JOIN units u ON r.recipe_yield_unit = u.unit_id
WHERE r.recipe_uuid = ${recipe_uuid}
GROUP BY r.recipe_uuid, r.recipe_name,
  r.recipe_description,
  r.recipe_image,
  r.recipe_yield,
  r.headline,
  u.unit_name;
