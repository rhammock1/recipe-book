ALTER TABLE ingredients ALTER COLUMN ingredient_quantity DROP NOT NULL;
ALTER TABLE ingredients ALTER COLUMN ingredient_unit DROP NOT NULL;

-- make ingredients static and create a recipe_ingredients join table with teh details on units and quantities