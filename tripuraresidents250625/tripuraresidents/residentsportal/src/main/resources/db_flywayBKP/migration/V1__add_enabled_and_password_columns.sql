-- Add 'enabled' column allowing NULLs initially
ALTER TABLE users ADD COLUMN IF NOT EXISTS enabled BOOLEAN;

-- Set default value for existing rows
UPDATE users SET enabled = true WHERE enabled IS NULL;

-- Enforce NOT NULL constraint
ALTER TABLE users ALTER COLUMN enabled SET NOT NULL;

-- Add 'password' column allowing NULLs initially
ALTER TABLE users ADD COLUMN IF NOT EXISTS password VARCHAR(255);

-- Set temporary password value for existing rows
UPDATE users SET password = 'changeme123' WHERE password IS NULL;

-- Enforce NOT NULL constraint
ALTER TABLE users ALTER COLUMN password SET NOT NULL;

