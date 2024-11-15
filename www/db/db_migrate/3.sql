CREATE TYPE recipe_source AS ENUM ('CUSTOM', 'HELLOFRESH', 'OTHER');
ALTER TABLE recipes ADD COLUMN headline TEXT,
  ADD COLUMN source recipe_source,
  ADD COLUMN x_recipe_id TEXT;

ALTER TABLE tags ADD COLUMN x_tag_id TEXT;

ALTER TABLE ingredients ADD COLUMN x_ingredient_id TEXT;
