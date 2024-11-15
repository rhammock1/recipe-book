SELECT i.instruction_id,
  i.recipe_uuid,
  i.instruction_order,
  i.instruction
FROM instructions i
WHERE i.recipe_uuid = ${recipe_uuid}
ORDER BY i.instruction_order;

