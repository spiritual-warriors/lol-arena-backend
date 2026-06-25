-- Strip file extension from image columns.
-- The image column now holds only the asset key (e.g. 'acceleratingsorcery_large');
-- the delivery format is resolved centrally in the frontend via IMG_EXT.
-- Uses regexp_replace to drop only the trailing extension, so keys containing
-- dots elsewhere are preserved.
UPDATE augments  SET image = regexp_replace(image, '\.[^./]+$', '');
UPDATE items     SET image = regexp_replace(image, '\.[^./]+$', '');
UPDATE champions SET image = regexp_replace(image, '\.[^./]+$', '');
