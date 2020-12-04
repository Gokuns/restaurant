INSERT INTO USERS(USERNAME, PASSWORD, ENABLED) VALUES ('user1', '{noop}pass1', true);
INSERT INTO USERS(USERNAME, PASSWORD, ENABLED) VALUES ('user2', '{noop}pass2', true);

INSERT INTO ROLES(AUTHORITY) VALUES ('ROLE_ADMIN');
INSERT INTO ROLES( AUTHORITY) VALUES ('ROLE_USER');

INSERT INTO AUTHORITIES(USERNAME, AUTHORITY) VALUES ('user1', 'ROLE_ADMIN');
INSERT INTO AUTHORITIES(USERNAME, AUTHORITY) VALUES ('user1', 'ROLE_USER');
INSERT INTO AUTHORITIES(USERNAME, AUTHORITY) VALUES ('user2', 'ROLE_USER');

INSERT INTO CATEGORIES(name) VALUES ('Pizza');
INSERT INTO CATEGORIES(name) VALUES ('Burger');
INSERT INTO CATEGORIES(name) VALUES ('Drink');
INSERT INTO CATEGORIES(name) VALUES ('Dessert');


INSERT INTO Product(name, details, category_id, price) VALUES ('Margarita Pizza', 'Pizza', 1, 22);
INSERT INTO Product(name, details, category_id, price) VALUES ('Fungi Pizza', 'Pizza', 1, 23);
INSERT INTO Product(name, details, category_id, price) VALUES ('Steak Burger', '200gr Steak', 2, 28);
INSERT INTO Product(name, details, category_id, price) VALUES ('Cheeseburger', 'Cheddar', 2,  25);
INSERT INTO Product(name, details, category_id, price) VALUES ('Steak Burger', '200gr Steak', 2,  28);
INSERT INTO Product(name, details, category_id, price) VALUES ('Cheeseburger', 'Cheddar', 2,  25);
INSERT INTO Product(name, details, category_id, price) VALUES ('Latte', 'Middle', 3, 14);
INSERT INTO Product(name, details, category_id, price) VALUES ('Mocha', 'Middle', 3, 15);
INSERT INTO Product(name, details, category_id, price) VALUES ('Flat White', 'Middle', 3, 13);


INSERT INTO TABLE_CATEGORIES(name,number) VALUES ('Inside',6);
INSERT INTO TABLE_CATEGORIES(name,number) VALUES ('Outside',4);
INSERT INTO TABLE_CATEGORIES(name,number) VALUES ('Balcony',5);
INSERT INTO TABLE_CATEGORIES(name,number) VALUES ('Window side',4);

