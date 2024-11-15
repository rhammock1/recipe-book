ALTER TABLE recipes DROP CONSTRAINT recipes_recipe_name_key;
ALTER TABLE recipes ADD CONSTRAINT recipes_x_recipe_id_key UNIQUE (x_recipe_id);