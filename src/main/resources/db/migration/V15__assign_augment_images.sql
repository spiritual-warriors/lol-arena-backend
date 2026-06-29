-- Assign image asset keys to augments whose corresponding *_large.webp file
-- exists in frontend/public/images/augments.
--
-- Idempotent: each UPDATE only sets the value; safe to re-run.
-- Convention: image column holds the asset basename (no extension) per V9 strip.
-- Source of truth: backend/augments-updated.txt + frontend asset inventory.
UPDATE augments SET image = 'aim_for_the_head_large'              WHERE id = 162;
UPDATE augments SET image = 'augmented_power_large'               WHERE id = 165;
UPDATE augments SET image = 'calculated_risk_large'               WHERE id = 171;
UPDATE augments SET image = 'firfox_large'                        WHERE id = 192;
UPDATE augments SET image = 'hive_mind_large'                     WHERE id = 199;
UPDATE augments SET image = 'pandoras_box_large'                  WHERE id = 212;
UPDATE augments SET image = 'panic_room_large'                    WHERE id = 213;
UPDATE augments SET image = 'mad_hatter_large'                    WHERE id = 219;
UPDATE augments SET image = 'quest_rite_of_the_forge_god_large'   WHERE id = 220;
UPDATE augments SET image = 'threesacredtreasures_large'         WHERE id = 221;
UPDATE augments SET image = 'righteous_fury_large'                WHERE id = 226;
UPDATE augments SET image = 'spirit_infusion_large'               WHERE id = 234;
UPDATE augments SET image = 'tank_engine_large'                   WHERE id = 237;