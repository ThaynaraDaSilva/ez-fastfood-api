SELECT * FROM EZ_FASTFOOD.ORDER ORDER BY ID DESC;
SELECT * FROM EZ_FASTFOOD.CUSTOMER; 456.000.000-78

SELECT * FROM EZ_FASTFOOD.PAYMENT ORDER BY ID DESC;
SELECT * FROM EZ_FASTFOOD.PAYMENT WHERE ORDER_ID = '176';
ALTER TABLE EZ_FASTFOOD.ORDER ADD COLUMN order_number VARCHAR(255);

UPDATE ez_fastfood.order 
SET order_number = LPAD(CAST(id AS VARCHAR), 4, '0') || ' ' || customer_name;


WITH SequencedOrders AS (
    SELECT 
        id, 
        customer_name, 
        ROW_NUMBER() OVER (ORDER BY id) - 1 AS seq_number
    FROM ez_fastfood.order
)
UPDATE ez_fastfood.order
SET order_number = LPAD(SequencedOrders.seq_number::text, 4, '0') || ' ' || SequencedOrders.customer_name
FROM SequencedOrders
WHERE ez_fastfood.order.id = SequencedOrders.id;

SELECT *
FROM ez_fastfood.order
WHERE DATE(order_time) = CURRENT_DATE
ORDER BY order_time DESC
LIMIT 1;

DELETE FROM EZ_FASTFOOD.ORDER WHERE ID = '142';

SELECT * FROM ez_fastfood.order WHERE DATE(order_time) = CURRENT_DATE ORDER BY order_time DESC LIMIT 1


TRUNCATE TABLE EZ_FASTFOOD.ORDER_ITEMS;
TRUNCATE TABLE EZ_FASTFOOD.PAYMENT;
TRUNCATE TABLE EZ_FASTFOOD.ORDER CASCADE;