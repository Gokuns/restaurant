INSERT INTO USERS(USERNAME, PASSWORD, ENABLED) VALUES ('user1', '{noop}pass1', true);
INSERT INTO USERS(USERNAME, PASSWORD, ENABLED) VALUES ('user2', '{noop}pass2', true);

INSERT INTO AUTHORITIES(USERNAME, AUTHORITY) VALUES ('user1', 'ROLE_ADMIN');
INSERT INTO AUTHORITIES(USERNAME, AUTHORITY) VALUES ( 'user2', 'ROLE_USER');

INSERT INTO Product(name, details, category, price) VALUES ('Margarita Pizza', 'Pizza', 'Pizza', 22);
INSERT INTO Product(name, details, category, price) VALUES ('Fungi Pizza', 'Pizza', 'Pizza', 23);
INSERT INTO Product(name, details, category, price) VALUES ('Steak Burger', '200gr Steak', 'Burger', 28);
INSERT INTO Product(name, details, category, price) VALUES ('Cheeseburger', 'Cheddar', 'Burger', 25);
INSERT INTO Product(name, details, category, price) VALUES ('Steak Burger', '200gr Steak', 'Burger', 28);
INSERT INTO Product(name, details, category, price) VALUES ('Cheeseburger', 'Cheddar', 'Burger', 25);
INSERT INTO Product(name, details, category, price) VALUES ('Latte', 'Middle', 'Coffee', 14);
INSERT INTO Product(name, details, category, price) VALUES ('Mocha', 'Middle', 'Coffee', 15);
INSERT INTO Product(name, details, category, price) VALUES ('Flat White', 'Middle', 'Coffee', 13);