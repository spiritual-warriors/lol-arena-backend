-- Add enabled column to champions, items, and augments.
-- Existing rows default to enabled = true.
ALTER TABLE items ADD COLUMN enabled BOOLEAN NOT NULL DEFAULT TRUE;
ALTER TABLE augments ADD COLUMN enabled BOOLEAN NOT NULL DEFAULT TRUE;
ALTER TABLE champions ADD COLUMN enabled BOOLEAN NOT NULL DEFAULT TRUE;
