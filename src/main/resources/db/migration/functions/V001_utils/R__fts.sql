DROP FUNCTION IF EXISTS fts;
CREATE FUNCTION fts(t1 text, t2 text)
    RETURNS boolean
    LANGUAGE plpgsql
    STABLE
AS
$$
BEGIN
    RETURN t1 @@ t2;
END;
$$;