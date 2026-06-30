-- Assign image asset keys to augments whose corresponding .webp file
-- was downloaded from CommunityDragon 16.9 (assets/ux/cherry/augments/icons)
-- and converted to webp in frontend/public/images/augments.
--
-- These icons were missing from the original V13 seed / V15 assignment because
-- the prior download script only fetched *_large.png files; these 16 augments
-- use icon filenames without the _large suffix.
--
-- Idempotent: each UPDATE only sets the value; safe to re-run.
-- Convention: image column holds the asset basename (no extension) per V9 strip.
-- Source of truth: AUGMENT_IMAGES_AUDIT.md + frontend asset inventory.
-- Note: two filenames preserve CommunityDragon's original typos
--   (bread_sandwhich -> "sandwich", grevious_venmon -> "venom").
UPDATE augments SET image = 'bread_sandwhich'           WHERE id = 170;
UPDATE augments SET image = 'chroma_flux'               WHERE id = 173;
UPDATE augments SET image = 'combination_fried_rice'    WHERE id = 175;
UPDATE augments SET image = 'demonic_clasp'             WHERE id = 180;
UPDATE augments SET image = 'grevious_venmon'           WHERE id = 195;
UPDATE augments SET image = 'magic_girl'                WHERE id = 205;
UPDATE augments SET image = 'mercy'                     WHERE id = 206;
UPDATE augments SET image = 'rice_and_chicken'          WHERE id = 223;
UPDATE augments SET image = 'rice_and_fish'             WHERE id = 224;
UPDATE augments SET image = 'rice_and_pork'             WHERE id = 225;
UPDATE augments SET image = 'scavenger'                 WHERE id = 227;
UPDATE augments SET image = 'silver_spoon'              WHERE id = 229;
UPDATE augments SET image = 'spell_craft'               WHERE id = 232;
UPDATE augments SET image = 'transmute_silver'          WHERE id = 241;
UPDATE augments SET image = 'trash_treasure'            WHERE id = 242;
UPDATE augments SET image = 'wild_fire'                 WHERE id = 254;
