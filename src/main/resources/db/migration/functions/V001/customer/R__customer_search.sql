DROP FUNCTION IF EXISTS customer_search_stmt;
CREATE FUNCTION customer_search_stmt(p_params json)
    RETURNS text
    LANGUAGE plpgsql
AS
$$
DECLARE
    stmt         text    = '';
    v_filter     json    = JSON_EXTRACT_PATH(p_params, 'filter');
    v_id         text    = JSON_EXTRACT_PATH_TEXT(v_filter, 'id');
    v_name       text    = JSON_EXTRACT_PATH_TEXT(v_filter, 'name');
    v_phone      text    = JSON_EXTRACT_PATH_TEXT(v_filter, 'phone');
    v_key        text    = JSON_EXTRACT_PATH_TEXT(p_params, 'key');
    v_limit      text    = JSON_EXTRACT_PATH_TEXT(p_params, 'limit');
    v_sorting    json    = JSON_EXTRACT_PATH(p_params, 'sorting');
    v_sort       record;
    v_sort_first boolean = TRUE;
BEGIN

    stmt := '
    SELECT customer.*
    FROM customer
    WHERE 1=1 ';

    IF v_id IS NOT NULL THEN
        stmt := stmt || ' AND customer.id = ' || v_id;
    END IF;

    IF v_name IS NOT NULL THEN
        stmt := stmt || ' AND fts(customer.full_name, ' || QUOTE_LITERAL(v_name) || ')';
    END IF;

    IF v_phone IS NOT NULL THEN
        stmt := stmt || ' AND customer.phone = ' || QUOTE_LITERAL(v_phone);
    END IF;

    IF v_key IS NOT NULL THEN
        stmt := stmt || ' AND id > ' || v_key;
    END IF;

    IF (SELECT COUNT(*) FROM JSON_EACH(v_sorting)) > 0 THEN
        stmt := stmt || ' ORDER BY ';

        FOR v_sort IN (SELECT * FROM JSON_EACH(v_sorting))
        LOOP
            IF NOT v_sort_first THEN
                stmt := stmt || ', ';
            END IF;
            stmt := stmt || v_sort.key || ' ' || replace(v_sort.value::text, '"', '');
            v_sort_first = FALSE;
        END LOOP;
    END IF;

    IF v_limit IS NOT NULL THEN
        stmt := stmt || ' LIMIT ' || v_limit;
    END IF;

    RETURN stmt;
END;
$$;

DROP FUNCTION IF EXISTS customer_search;
CREATE FUNCTION customer_search(p_params json)
    RETURNS TABLE (LIKE customer)
    LANGUAGE plpgsql
AS
$$
BEGIN
    RETURN QUERY EXECUTE customer_search_stmt(p_params);
END;
$$;
