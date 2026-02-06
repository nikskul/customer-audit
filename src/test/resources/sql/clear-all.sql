DELETE FROM customer_spent_history;
DELETE FROM customer;

ALTER SEQUENCE customer_id_seq RESTART;
ALTER SEQUENCE customer_spent_history_id_seq RESTART;