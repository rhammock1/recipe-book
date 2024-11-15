SELECT i.ingredient_name,
  i.ingredient_uuid,
  ri.ingredient_quantity,
  u.unit_name,
  u.unit_abbreviation
FROM recipe_ingredients ri
INNER JOIN ingredients i ON ri.ingredient_uuid = i.ingredient_uuid
LEFT JOIN units u ON ri.ingredient_unit = u.unit_id
WHERE ri.recipe_uuid = ${recipe_uuid};

