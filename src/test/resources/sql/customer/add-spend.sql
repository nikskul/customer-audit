INSERT INTO customer (id, full_name, phone, version, created_at, updated_at)
VALUES (nextval('customer_id_seq'), 'Ivanov Ivan', '+79008001020', 0, '2020-01-01', '2020-01-01')
;
INSERT INTO customer_spent_history (id, customer_id, amount, spend_timestamp, version, created_at, updated_at)
VALUES (nextval('customer_spent_history_id_seq'), 1, 15.09, '2020-01-01', 0, '2020-01-01', '2020-01-01')
;