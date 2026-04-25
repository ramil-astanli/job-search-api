CREATE INDEX IF NOT EXISTS idx_job_fts ON jobs
    USING GIN (to_tsvector('english', coalesce(title, '') || ' ' || coalesce(description, '')));