CREATE TABLE tags (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    slug VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE item_tags (
    item_id BIGINT REFERENCES items(id),
    tag_id BIGINT REFERENCES tags(id),
    PRIMARY KEY (item_id, tag_id)
);

CREATE TABLE augment_tags (
    augment_id BIGINT REFERENCES augments(id),
    tag_id BIGINT REFERENCES tags(id),
    PRIMARY KEY (augment_id, tag_id)
);

INSERT INTO tags (name, slug) VALUES ('On-Hit', 'on-hit');
INSERT INTO tags (name, slug) VALUES ('Slow', 'slow');
INSERT INTO tags (name, slug) VALUES ('Burn', 'burn');