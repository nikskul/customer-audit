CREATE OR REPLACE FUNCTION customer_search(p_params json)
    RETURNS customer
    LANGUAGE plpgsql
    STABLE
AS
$$
DECLARE
    stmt     text = '';
    v_filter json = JSON_EXTRACT_PATH(p_params, 'filter');
    v_id     text = JSON_EXTRACT_PATH_TEXT(v_filter, 'id');
    v_name   text = JSON_EXTRACT_PATH_TEXT(v_filter, 'name');
    v_phone  text = JSON_EXTRACT_PATH_TEXT(v_filter, 'phone');
BEGIN
    stmt := '
    SELECT customer.*
    FROM customer
    WHERE 1=1 ';

    IF v_id IS NOT NULL THEN
        stmt := stmt || ' AND customer.id = ' || QUOTE_LITERAL(v_id);
    END IF;

    IF v_name IS NOT NULL THEN
        stmt := stmt || ' AND ts_vector(customer.full_name) @@ ts_query(' ||
                QUOTE_LITERAL(v_name) || ')';
    END IF;

    IF v_id IS NOT NULL THEN
        stmt := stmt || ' AND customer.phone = ' || QUOTE_LITERAL(v_phone);
    END IF;

    RETURN (SELECT * FROM stmt)::customer;
END;
$$;
