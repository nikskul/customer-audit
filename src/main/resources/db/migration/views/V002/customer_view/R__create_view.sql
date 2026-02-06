DROP VIEW IF EXISTS customer_view RESTRICT;
CREATE VIEW customer_view AS
SELECT customer.id AS id,
       customer.full_name as full_name,
       customer.phone as phone,
       COALESCE(SUM(customer_spent_history.amount), 0)  as spent
FROM customer
         LEFT JOIN customer_spent_history ON customer.id = customer_spent_history.customer_id
GROUP BY customer.id, customer.full_name, customer.phone
;

