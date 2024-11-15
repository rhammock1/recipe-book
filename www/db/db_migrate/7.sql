CREATE TABLE recipe_ingredients (
  recipe_uuid UUID NOT NULL REFERENCES recipes(recipe_uuid),
  ingredient_uuid UUID NOT NULL REFERENCES ingredients(ingredient_uuid),
  ingredient_quantity INT,
  ingredient_unit INT REFERENCES units(unit_id)
);
CREATE UNIQUE INDEX recipe_ingredients_idx ON recipe_ingredients (recipe_uuid, ingredient_uuid);

ALTER TABLE ingredients DROP COLUMN recipe_uuid;
ALTER TABLE ingredients DROP COLUMN ingredient_quantity;
ALTER TABLE ingredients DROP COLUMN ingredient_unit;
CREATE UNIQUE INDEX ingredient_x_ingredient_id_idx ON ingredients (x_ingredient_id);

DROP TABLE recipe_ingredient_tags;
CREATE TABLE recipe_tags (
  recipe_uuid UUID NOT NULL REFERENCES recipes(recipe_uuid),
  tag_id INT NOT NULL REFERENCES tags(tag_id)
);
CREATE UNIQUE INDEX recipe_tags_idx ON recipe_tags (recipe_uuid, tag_id);

