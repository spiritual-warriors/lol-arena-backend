-- Assign image asset keys to the remaining augments whose icon was missing
-- from CommunityDragon and was instead downloaded from the LoL wiki
-- (https://wiki.leagueoflegends.com/en-us/Arena/Augments).
--
-- These 39 augments had image = NULL because their icons do not exist in
-- CommunityDragon 16.9 (assets/ux/cherry/augments/icons). The wiki icons use
-- the naming convention <Name_with_underscores>_ar_augment.png and were
-- converted to .webp in frontend/public/images/augments.
--
-- Idempotent: each UPDATE only sets the value; safe to re-run.
-- Convention: image column holds the asset basename (no extension) per V9 strip.
--
-- Notes:
--   * id 210 "Ominous Pact" uses the icon file Wisdom_of_Ages_ar_augment —
--     the augment was renamed from "Wisdom of Ages" but the wiki icon was not.
--   * id 218 "Quest: Icathia's Fall" — the apostrophe was stripped from the
--     filename (Quest_Icathias_Fall_ar_augment) to avoid URL/SQL issues.
UPDATE augments SET image = 'Adamant_ar_augment'                              WHERE id = 161;
UPDATE augments SET image = 'Big_Dragon_Energy_ar_augment'                   WHERE id = 166;
UPDATE augments SET image = 'Bounce_of_the_Poro_King_ar_augment'             WHERE id = 168;
UPDATE augments SET image = 'Crack_Open_That_Egg_ar_augment'                 WHERE id = 176;
UPDATE augments SET image = 'Critical_Rhythm_ar_augment'                     WHERE id = 177;
UPDATE augments SET image = 'Despoil_ar_augment'                             WHERE id = 182;
UPDATE augments SET image = 'Devil_on_Your_Shoulder_ar_augment'              WHERE id = 183;
UPDATE augments SET image = 'Divine_Intervention_ar_augment'                 WHERE id = 184;
UPDATE augments SET image = 'Donation_ar_augment'                            WHERE id = 185;
UPDATE augments SET image = 'Double_Tap_ar_augment'                          WHERE id = 187;
UPDATE augments SET image = 'Dreadbringer_ar_augment'                        WHERE id = 188;
UPDATE augments SET image = 'Escape_Plan_ar_augment'                         WHERE id = 190;
UPDATE augments SET image = 'Final_Form_ar_augment'                          WHERE id = 191;
UPDATE augments SET image = 'Get_Excited_ar_augment'                         WHERE id = 193;
UPDATE augments SET image = 'Glass_Cannon_ar_augment'                        WHERE id = 194;
UPDATE augments SET image = 'Growth_Spurt_ar_augment'                        WHERE id = 196;
UPDATE augments SET image = 'Hextech_Soul_ar_augment'                        WHERE id = 198;
UPDATE augments SET image = 'Juiced_ar_augment'                              WHERE id = 202;
UPDATE augments SET image = 'Kill_Secured_ar_augment'                        WHERE id = 203;
UPDATE augments SET image = 'Null_ar_augment'                                WHERE id = 208;
UPDATE augments SET image = 'Wisdom_of_Ages_ar_augment'                      WHERE id = 210;
UPDATE augments SET image = 'Pinball_ar_augment'                             WHERE id = 215;
UPDATE augments SET image = 'Protein_Shake_ar_augment'                       WHERE id = 216;
UPDATE augments SET image = 'Purist_-_Caster_ar_augment'                     WHERE id = 217;
UPDATE augments SET image = 'Quest_Icathias_Fall_ar_augment'                 WHERE id = 218;
UPDATE augments SET image = 'Rags_to_Riches_ar_augment'                      WHERE id = 222;
UPDATE augments SET image = 'Shrink_Engine_ar_augment'                       WHERE id = 228;
UPDATE augments SET image = 'Speed_Demon_ar_augment'                         WHERE id = 231;
UPDATE augments SET image = 'Spin_Me_Right_Round_ar_augment'                 WHERE id = 233;
UPDATE augments SET image = 'Stuck_In_Here_With_Me_ar_augment'               WHERE id = 235;
UPDATE augments SET image = 'Twin_Fire_ar_augment'                           WHERE id = 244;
UPDATE augments SET image = 'Undying_Guard_ar_augment'                       WHERE id = 245;
UPDATE augments SET image = 'Upgrade_Collector_ar_augment'                   WHERE id = 248;
UPDATE augments SET image = 'Upgrade_Hubris_ar_augment'                      WHERE id = 249;
UPDATE augments SET image = 'Upgrade_Sword_of_Blossoming_Dawn_ar_augment'    WHERE id = 250;
UPDATE augments SET image = 'Veil_of_Warding_ar_augment'                     WHERE id = 251;
UPDATE augments SET image = 'Weighted_Popoffs_ar_augment'                    WHERE id = 252;
UPDATE augments SET image = 'Wind_Beneath_Blade_ar_augment'                  WHERE id = 255;
UPDATE augments SET image = 'Zealot_ar_augment'                              WHERE id = 256;
