ALTER TABLE builds ADD COLUMN created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW();

CREATE INDEX idx_builds_created_at ON builds (created_at DESC);
