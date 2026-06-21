DROP TABLE IF EXISTS champion_builds CASCADE;
DROP TABLE IF EXISTS build_items CASCADE;
DROP TABLE IF EXISTS build_augments CASCADE;
DROP TABLE IF EXISTS builds CASCADE;
DROP TABLE IF EXISTS champions CASCADE;
DROP TABLE IF EXISTS items CASCADE;
DROP TABLE IF EXISTS augments CASCADE;

CREATE TABLE champions (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(255)
);

CREATE TABLE builds (
    id BIGSERIAL PRIMARY KEY,
    description TEXT,
    effectivity VARCHAR(255)
);

CREATE TABLE items (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    image VARCHAR(255)
);

CREATE TABLE augments (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    tier VARCHAR(50),
    image VARCHAR(255)
);

CREATE TABLE champion_builds (
    champion_id VARCHAR(50) REFERENCES champions(id),
    build_id BIGINT REFERENCES builds(id) ON DELETE CASCADE,
    PRIMARY KEY (champion_id, build_id)
);

CREATE TABLE build_items (
    build_id BIGINT REFERENCES builds(id) ON DELETE CASCADE,
    item_id BIGINT REFERENCES items(id),
    PRIMARY KEY (build_id, item_id)
);

CREATE TABLE build_augments (
    build_id BIGINT REFERENCES builds(id) ON DELETE CASCADE,
    augment_id BIGINT REFERENCES augments(id),
    PRIMARY KEY (build_id, augment_id)
);