CREATE TYPE unit_type AS ENUM ('volume', 'weight', 'count');
CREATE TYPE unit_system AS ENUM ('metric', 'imperial');

CREATE TABLE units (
  unit_id SERIAL PRIMARY KEY,
  unit_name TEXT NOT NULL,
  unit_abbreviation VARCHAR(10) NOT NULL,
  unit_type unit_type NOT NULL,
  unit_system unit_system NOT NULL
);

CREATE TABLE tags (
  tag_id SERIAL PRIMARY KEY,
  tag_name TEXT UNIQUE NOT NULL
);

CREATE TABLE recipes (
  recipe_id SERIAL PRIMARY KEY,
  recipe_uuid UUID UNIQUE NOT NULL DEFAULT gen_random_uuid(),
  recipe_name TEXT UNIQUE NOT NULL,
  recipe_description TEXT,
  recipe_image TEXT, -- URL or path to image
  recipe_yield INT,
  recipe_yield_unit INT REFERENCES units(unit_id)
);

CREATE TABLE ingredients (
  ingredient_id SERIAL PRIMARY KEY,
  ingredient_uuid UUID UNIQUE NOT NULL DEFAULT gen_random_uuid(),
  ingredient_name TEXT NOT NULL,
  ingredient_quantity INT NOT NULL,
  ingredient_unit INT NOT NULL REFERENCES units(unit_id),
  recipe_uuid UUID NOT NULL REFERENCES recipes(recipe_uuid)
);

CREATE TABLE recipe_ingredient_tags (
  recipe_uuid UUID REFERENCES recipes(recipe_uuid),
  ingredient_uuid UUID REFERENCES ingredients(ingredient_uuid),
  tag_id INT NOT NULL REFERENCES tags(tag_id)
);
CREATE UNIQUE INDEX recipe_ingredient_tags_idx ON recipe_ingredient_tags (recipe_uuid, ingredient_uuid, tag_id);
ALTER TABLE recipe_ingredient_tags ADD CONSTRAINT recipe_ingredient_tags_check CHECK (recipe_uuid IS NOT NULL OR ingredient_uuid IS NOT NULL);

CREATE TABLE instructions (
  instruction_id SERIAL PRIMARY KEY,
  instruction_order INT NOT NULL,
  instruction TEXT NOT NULL,
  recipe_uuid UUID REFERENCES recipes(recipe_uuid)
);

INSERT INTO units (unit_name, unit_abbreviation, unit_type, unit_system)
VALUES ('teaspoon', 'tsp', 'volume', 'imperial'),
  ('tablespoon', 'tbsp', 'volume', 'imperial'),
  ('fluid ounce', 'fl oz', 'volume', 'imperial'),
  ('cup', 'c', 'volume', 'imperial'),
  ('pint', 'pt', 'volume', 'imperial'),
  ('quart', 'qt', 'volume', 'imperial'),
  ('gallon', 'gal', 'volume', 'imperial'),
  ('milliliter', 'mL', 'volume', 'metric'),
  ('liter', 'L', 'volume', 'metric'),
  ('kiloliter', 'kL', 'volume', 'metric'),
  ('gram', 'g', 'weight', 'metric'),
  ('kilogram', 'kg', 'weight', 'metric'),
  ('ounce', 'oz', 'weight', 'imperial'),
  ('pound', 'lb', 'weight', 'imperial'),
  ('count', 'ct', 'count', 'metric'),
  ('count', 'ct', 'count', 'imperial'),
  ('each', 'ea', 'count', 'metric'),
  ('each', 'ea', 'count', 'imperial');
