DELETE FROM products;
INSERT INTO products (name, description, price, stock) VALUES ('Laptop', 'High performance laptop', 1200.00, 10);
INSERT INTO products (name, description, price, stock) VALUES ('Smartphone', 'Latest model smartphone', 800.00, 25);
INSERT INTO products (name, description, price, stock) VALUES ('Headphones', 'Noise cancelling headphones', 150.00, 50);

DELETE FROM customers;
INSERT INTO customers (first_name, last_name, email) VALUES ('John', 'Doe', 'john.doe@example.com');
INSERT INTO customers (first_name, last_name, email) VALUES ('Jane', 'Smith', 'jane.smith@example.com');
