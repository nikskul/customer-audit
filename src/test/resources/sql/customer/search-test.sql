INSERT INTO customer (id, full_name, phone, version, created_at, updated_at)
VALUES (NEXTVAL('customer_id_seq'), 'Ivanov Ivan Yovanovitch', '+79008001020', 0, NOW(), NOW())
     , (NEXTVAL('customer_id_seq'), 'Dyatlov Dmitriy Sergeevich', '+79018001020', 0, NOW(), NOW())
     , (NEXTVAL('customer_id_seq'), 'Petrov Petr Petrovich', '+79028001020', 0, NOW(), NOW())
     , (NEXTVAL('customer_id_seq'), 'Rybin Maksim Maksimovich', '+79038001020', 0, NOW(), NOW())
     , (NEXTVAL('customer_id_seq'), 'Test', '+79048001020', 0, NOW(), NOW())
;

INSERT INTO customer_spent_history (id, customer_id, amount, spend_timestamp, version, created_at, updated_at)
VALUES (NEXTVAL('customer_spent_history_id_seq'), 2, 1.00, NOW(), 0, NOW(), NOW())
     , (NEXTVAL('customer_spent_history_id_seq'), 3, 15, NOW(), 0, NOW(), NOW())
     , (NEXTVAL('customer_spent_history_id_seq'), 3, 0.25, NOW(), 0, NOW(), NOW())
     , (NEXTVAL('customer_spent_history_id_seq'), 4, 9999999999999999.99, NOW(), 0, NOW(), NOW())
     , (NEXTVAL('customer_spent_history_id_seq'), 5, 3.99, NOW(), 0, NOW(), NOW())
;