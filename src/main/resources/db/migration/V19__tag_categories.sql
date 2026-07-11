-- Add category and applicable_to columns to tags table
ALTER TABLE tags
  ADD COLUMN category      VARCHAR(50) NOT NULL DEFAULT 'custom',
  ADD COLUMN applicable_to VARCHAR[]    NOT NULL DEFAULT ARRAY['ITEM','AUGMENT'];

-- Backfill existing custom mechanic tags
UPDATE tags
  SET category = 'custom',
      applicable_to = ARRAY['ITEM','AUGMENT']
WHERE slug IN ('on-hit','slow','burn','heal','shield',
               'critic','movement','dot','curse','autocast');

-- Seed item-tier tags (rareza de items de Arena)
INSERT INTO tags (name, slug, category, applicable_to) VALUES
  ('Boots','boots','tier',ARRAY['ITEM']),
  ('Anvil','anvil','tier',ARRAY['ITEM']),
  ('Quest','quest','tier',ARRAY['ITEM']),
  ('Legendary','legendary','tier',ARRAY['ITEM']),
  ('Prismatic','prismatic-tier','tier',ARRAY['ITEM'])
ON CONFLICT (slug) DO NOTHING;

-- Seed class tags (roles Riot para items orientados a rol)
INSERT INTO tags (name, slug, category, applicable_to) VALUES
  ('Fighter','fighter','class',ARRAY['ITEM']),
  ('Tank','tank','class',ARRAY['ITEM']),
  ('Marksman','marksman','class',ARRAY['ITEM']),
  ('Mage','mage','class',ARRAY['ITEM']),
  ('Assassin','assassin','class',ARRAY['ITEM']),
  ('Support','support','class',ARRAY['ITEM'])
ON CONFLICT (slug) DO NOTHING;