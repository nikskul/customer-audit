DO
$$
    BEGIN

        IF EXISTS(SELECT 1
                  FROM information_schema.columns
                  WHERE table_name = 'customer'
                    AND column_name = 'spent')
        THEN
            IF EXISTS(SELECT 1
                      FROM information_schema.columns
                      WHERE table_name = 'customer_spent_history'
                        AND column_name = 'amount')
            THEN
                INSERT INTO customer_spent_history(customer_id, amount, created_at)
                SELECT customer.id, spent, '12.12.2025'
                FROM customer
                WHERE customer.spent > 0;
            END IF;

            ALTER TABLE customer
                DROP COLUMN IF EXISTS spent;

        END IF;

    END;
$$