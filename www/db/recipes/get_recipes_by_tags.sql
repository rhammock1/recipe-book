SELECT r.recipe_uuid,
  r.recipe_name,
  r.recipe_description,
  r.recipe_image,
  r.recipe_yield,
  u.unit_name AS recipe_yield_unit,
  array_agg(t.tag_name) AS recipe_tags
FROM recipes r
LEFT JOIN units u ON r.recipe_yield_unit = u.unit_id
INNER JOIN recipe_tags rt ON r.recipe_uuid = rt.recipe_uuid
INNER JOIN tags t ON rt.tag_id = t.tag_id
WHERE t.tag_name = ANY(${tags})
GROUP BY r.recipe_uuid;
