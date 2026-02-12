-- Add 'enabled' column with default value false to avoid null constraint violation
ALTER TABLE users ADD COLUMN IF NOT EXISTS enabled BOOLEAN DEFAULT FALSE;

-- Add 'password' column with a temporary default to avoid null constraint violation
ALTER TABLE users ADD COLUMN IF NOT EXISTS password VARCHAR(255) DEFAULT 'changeme123';

-- Optionally, if you want to enforce NOT NULL afterwards (once all values are valid), run this in a later version:
-- ALTER TABLE users ALTER COLUMN enabled SET NOT NULL;
-- ALTER TABLE users ALTER COLUMN password SET NOT NULL;
