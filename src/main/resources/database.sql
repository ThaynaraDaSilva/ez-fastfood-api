CREATE SCHEMA IF NOT EXISTS EZ_FASTFOOD;


CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.CUSTOMER(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE,
    email VARCHAR(255),
    password VARCHAR (10)
);

CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.CATEGORY (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.PRODUCT (
    id BIGSERIAL PRIMARY KEY,
    category_id INT,
    name VARCHAR(255),
    description TEXT,
    price DECIMAL,
    FOREIGN KEY (category_id) REFERENCES EZ_FASTFOOD.CATEGORY(id)
);

CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.ORDER (
    id BIGSERIAL PRIMARY KEY,
    order_number VARCHAR(255),
    customer_id INT NULL,
    order_time TIMESTAMP WITH TIME ZONE,
    total_price DECIMAL(10, 2),
    order_status VARCHAR(50) NULL,
    customer_name VARCHAR(255),
    completed_time TIMESTAMP WITH TIME ZONE,
    FOREIGN KEY (customer_id) REFERENCES EZ_FASTFOOD.CUSTOMER(id)
);

CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.ORDER_ITEMS (
    id BIGSERIAL PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT,
    price DECIMAL,
    FOREIGN KEY (order_id) REFERENCES EZ_FASTFOOD.ORDER(id),
    FOREIGN KEY (product_id) REFERENCES EZ_FASTFOOD.PRODUCT(id)
);

CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.PAYMENT (
    id BIGSERIAL PRIMARY KEY,
    order_id INT,
    customer_id INT NULL,
    payment_date TIMESTAMP WITH TIME ZONE NULL,
    payment_price DECIMAL,
    payment_status VARCHAR(50),
    FOREIGN KEY (order_id) REFERENCES EZ_FASTFOOD.ORDER(id),
    FOREIGN KEY (customer_id) REFERENCES EZ_FASTFOOD.CUSTOMER(id)
);

INSERT INTO EZ_FASTFOOD.CUSTOMER (name, cpf, email,password)
VALUES 
('Thaynara da Silva', '359.380.170-17', 'thaynara@gmail.com','senha123'),
('Flavio da Silva', '530.335.610-80', 'flavio@gmail.com','senha123');

INSERT INTO EZ_FASTFOOD.CATEGORY (name)
VALUES 
('LANCHE'),
('BEBIDA'),
('ACOMPANHAMENTO'),
('SOBREMESA');


INSERT INTO EZ_FASTFOOD.PRODUCT (name,description,price, category_id)
VALUES 
('X-BURGUER', 'CARNE BOVINA, QUEIJO PRATA, ALFACE, TOMATE, CEBOLA E MOLHO DA CASA','20.50',1),
('X-FRANGO', 'FILE DE FRANGO EMPANADO, MUÇARELA, ALFACE, TOMATE,BACON,CEBOLA E MOLHO DA CASA','19.50',1),
('COCA-COLA', 'REFRIGERENTE DE 300 ML','5.50',2),
('TUTU', 'REFRIGERENTE DE GARRAFA DAS ANTIGAS','4.5',2),
('BATATA FRITA', 'FRITAS ONDULADAS','11.90',3),
('PETIT GATEAU', 'TIRA GOSTO','7.00',4);

INSERT INTO EZ_FASTFOOD.ORDER (order_number, customer_id, order_time, total_price, order_status, customer_name, completed_time) 
VALUES ('001 Thay', 1, NOW(), 20.5, 'WAITING_PAYMENT', 'Thaynara da Silva', NULL);

INSERT INTO EZ_FASTFOOD.ORDER_ITEMS (order_id, product_id, quantity, price) 
VALUES (1, 1, 1, 20.50);

INSERT INTO EZ_FASTFOOD.PAYMENT (order_id, customer_id, payment_date, payment_price, payment_status) 
VALUES (1, 1, NULL, 20.50, 'PENDING');

/*
CODIGO PARA QUANDO ENUM EH ALTERADO
ALTER TABLE ez_fastfood.order DROP CONSTRAINT order_order_status_check;
ALTER TABLE ez_fastfood.order ADD CONSTRAINT order_order_status_check 
CHECK (order_status IN ('WAITING_PAYMENT', 'CANCELLED', 'RECEIVED', 'IN_PREPARATION', 'READY', 'COMPLETED'));
*/

