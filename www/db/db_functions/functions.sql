-- This is where the database functions are defined
CREATE OR REPLACE FUNCTION go_get_recipe_tags(_recipe_uuid UUID) RETURNS TEXT[] AS $$
DECLARE
  _recipe_tags TEXT[];
BEGIN
  SELECT array_agg(t.tag_name)
  FROM tags t
  INNER JOIN recipe_tags rt ON t.tag_id = rt.tag_id
  WHERE rt.recipe_uuid = _recipe_uuid
  INTO _recipe_tags;

  RETURN _recipe_tags;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION go_get_recipe_ingredients(_recipe_uuid UUID) RETURNS JSONB AS $$
DECLARE
  _recipe_ingredients JSONB;
BEGIN
  SELECT jsonb_object_agg(
    i.ingredient_uuid::TEXT, jsonb_build_object(
      'ingredient_name', i.ingredient_name,
      'ingredient_quantity', i.ingredient_quantity,
      'unit_name', u.unit_name
    )
  ) AS ingredients
  FROM recipe_ingredients ri
  INNER JOIN ingredients i ON i.ingredient_uuid = ri.ingredient_uuid
  INNER JOIN units u ON i.ingredient_unit = u.unit_id
  WHERE ri.recipe_uuid = _recipe_uuid
  INTO _recipe_ingredients;

  RETURN _recipe_ingredients;
END;
$$ LANGUAGE plpgsql;

CREATE EXTENSION IF NOT EXISTS pg_trgm;
CREATE OR REPLACE FUNCTION go_search(_query TEXT) RETURNS JSONB AS $$
DECLARE
  _search_results JSONB;
BEGIN
  -- Search for recipes
  SELECT jsonb_agg(
    jsonb_build_object(
      'recipe_uuid', r.recipe_uuid,
      'recipe_name', r.recipe_name,
      'recipe_description', r.recipe_description,
      'recipe_tags', go_get_recipe_tags(r.recipe_uuid),
      'recipe_image', r.recipe_image,
      'headline', r.headline,
      'resource_type', 'RECIPE'
    )
  ) INTO _search_results
  FROM recipes r
  WHERE r.recipe_name %> _query 
    OR r.recipe_description %> _query;

  RETURN _search_results;
  -- Search for tags
  -- Search for ingredients
END;
$$ LANGUAGE plpgsql;