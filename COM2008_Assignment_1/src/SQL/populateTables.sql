USE team028;

DELETE FROM Orders;
DELETE FROM Bicycles;
DELETE FROM Frames;
DELETE FROM Wheels;
DELETE FROM Handlebars;
DELETE FROM Gearsets;
DELETE FROM Customers;
DELETE FROM Addresses;
DELETE FROM Gearsets;
DELETE FROM Staff;

ALTER TABLE Bicycles AUTO_INCREMENT = 1;
ALTER TABLE Frames AUTO_INCREMENT = 1;
ALTER TABLE Wheels AUTO_INCREMENT = 1;
ALTER TABLE Handlebars AUTO_INCREMENT = 1;
ALTER TABLE Gearsets AUTO_INCREMENT = 1;
ALTER TABLE Customers AUTO_INCREMENT = 1;
ALTER TABLE Addresses AUTO_INCREMENT = 1;
ALTER TABLE Gearsets AUTO_INCREMENT = 1;
ALTER TABLE Staff AUTO_INCREMENT = 1;
ALTER TABLE Orders AUTO_INCREMENT = 1;

INSERT INTO Gearsets(name) VALUES ("2 Speed");
INSERT INTO Gearsets(name) VALUES ("3 Speed");
INSERT INTO Gearsets(name) VALUES ("4 Speed");
INSERT INTO Gearsets(name) VALUES ("5 Speed");
INSERT INTO Gearsets(name) VALUES ("8 Speed");
INSERT INTO Gearsets(name) VALUES ("10 Speed");

INSERT INTO Handlebars(serial_number, brand_name, cost, style, stock_num) VALUES (1100001, "Dannies", 19.99, 3, 3);
INSERT INTO Handlebars(serial_number, brand_name, cost, style, stock_num) VALUES (1100002, "Dannies", 99.99, 3, 4);
INSERT INTO Handlebars(serial_number, brand_name, cost, style, stock_num) VALUES (1100003, "Forwards", 199.99, 2, 2);
INSERT INTO Handlebars(serial_number, brand_name, cost, style, stock_num) VALUES (1200001, "TallNMighty", 59.99, 1, 2);
INSERT INTO Handlebars(serial_number, brand_name, cost, style, stock_num) VALUES (1200002, "TallNMighty", 99.99, 1, 2);
INSERT INTO Handlebars(serial_number, brand_name, cost, style, stock_num) VALUES (1200003, "Giants", 119.99, 1, 5);
INSERT INTO Handlebars(serial_number, brand_name, cost, style, stock_num) VALUES (1300001, "LowRiderz", 99.99, 2, 2);
INSERT INTO Handlebars(serial_number, brand_name, cost, style, stock_num) VALUES (1300002, "Dannies", 19.99, 1, 2);
INSERT INTO Handlebars(serial_number, brand_name, cost, style, stock_num) VALUES (1300003, "LowRiderz", 119.99, 3, 2);

INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type, stock_num) VALUES (2110001, "Dannies", 59.99, 650.0, 1, 1, 4);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type, stock_num) VALUES (2110002, "Dannies", 89.99, 650.0, 1, 2, 4);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type, stock_num) VALUES (2120001, "Dannies", 99.99, 600.0, 2, 1, 4);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type, stock_num) VALUES (2120002, "Dannies", 119.99, 600.0, 2, 2, 4);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type, stock_num) VALUES (2130001, "Dannies", 159.99, 622.0, 3, 1, 6);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type, stock_num) VALUES (2130002, "Dannies", 199.99, 622.0, 3, 2, 8);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type, stock_num) VALUES (2210001, "Forwards", 29.99, 650.0, 1, 1, 2);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type, stock_num) VALUES (2210002, "Forwards", 49.99, 650.0, 1, 2, 6);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type, stock_num) VALUES (2220001, "Forwards", 79.99, 600.0, 2, 1, 8);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type, stock_num) VALUES (2220002, "Forwards", 99.99, 600.0, 2, 2, 10);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type, stock_num) VALUES (2230001, "Forwards", 119.99, 622.0, 3, 1, 12);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type, stock_num) VALUES (2230002, "Forwards", 159.99, 622.0, 3, 2, 2);

INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id, stock_num) VALUES (311001, "Giants", 119.99, 99.0, FALSE, (SELECT id FROM Gearsets WHERE name="2 Speed"), 2);
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id, stock_num) VALUES (311002, "Giants", 129.99, 99.0, TRUE, (SELECT id FROM Gearsets WHERE name="2 Speed"), 2);
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id, stock_num) VALUES (311003, "Giants", 139.99, 99.0, TRUE, (SELECT id FROM Gearsets WHERE name="3 Speed"), 6);
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id, stock_num) VALUES (311004, "Giants", 149.99, 99.0, FALSE, (SELECT id FROM Gearsets WHERE name="4 Speed"), 2);
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id, stock_num) VALUES (311005, "Giants", 159.99, 99.0, TRUE, (SELECT id FROM Gearsets WHERE name="5 Speed"), 2);
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id, stock_num) VALUES (311006, "Giants", 179.99, 109.0, FALSE, (SELECT id FROM Gearsets WHERE name="8 Speed"), 8);
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id, stock_num) VALUES (311007, "Giants", 199.99, 119.0, TRUE, (SELECT id FROM Gearsets WHERE name="10 Speed"), 2);
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id, stock_num) VALUES (312001, "Dannies", 119.99, 99.0, FALSE, (SELECT id FROM Gearsets WHERE name="2 Speed"), 2);
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id, stock_num) VALUES (312002, "Dannies", 139.99, 99.0, TRUE, (SELECT id FROM Gearsets WHERE name="3 Speed"), 3);
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id, stock_num) VALUES (312003, "Dannies", 129.99, 109.0, TRUE, (SELECT id FROM Gearsets WHERE name="4 Speed"), 1);
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id, stock_num) VALUES (312004, "Dannies", 149.99, 109.0, FALSE, (SELECT id FROM Gearsets WHERE name="5 Speed"), 2);
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id, stock_num) VALUES (312005, "Dannies", 199.99, 119.0, TRUE, (SELECT id FROM Gearsets WHERE name="8 Speed"), 3);
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id, stock_num) VALUES (312006, "Dannies", 229.99, 119.0, FALSE, (SELECT id FROM Gearsets WHERE name="10 Speed"), 2);
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id, stock_num) VALUES (312007, "Dannies", 249.99, 129.0, FALSE, (SELECT id FROM Gearsets WHERE name="8 Speed"), 5);
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id, stock_num) VALUES (312008, "Dannies", 299.99, 129.0, TRUE, (SELECT id FROM Gearsets WHERE name="3 Speed"), 2);

INSERT INTO Addresses(house_num_name, street_name, city, post_code) VALUES ('6', 'Moss Fold', 'Manchester','M29 7FP');
INSERT INTO Addresses(house_num_name, street_name, city, post_code) VALUES ('12', 'Street Lane', 'Sheffield','S10 1HS');
INSERT INTO Addresses(house_num_name, street_name, city, post_code) VALUES ('76', 'West View', 'Sheffield','S1 9LS');
INSERT INTO Addresses(house_num_name, street_name, city, post_code) VALUES ('Small Grotto', 'Country Lane', 'Middleton','L21 1AP');
INSERT INTO Addresses(house_num_name, street_name, city, post_code) VALUES ('Babble', 'Space Station', 'Space','SP1 1AA');

INSERT INTO Staff(username, password, salt) VALUES ('User1', 'cab44c9186239c3a14fced41fff06ddb4589a3f12a6caf855e1b28c959acfbd1', 'REIBTLGEKRGDYIF'); /*Password123*/
INSERT INTO Staff(username, password, salt) VALUES ('LogicInReason', '4be5990d206c57ab6ebb411b70344c790baf68c3ad2fa6f909a46fee52ce6eab', 'EXAHSMROGVULHSB'); /*Under_Pr3ssur3*/
INSERT INTO Staff(username, password, salt) VALUES ('AdamFirstName', 'f96e92e2477e784c4005d9de227e534fb7e83a85bcc842f4ff575bbf62325ec5', 'YHFZYFUZQZIOZGZ'); /*MiddleName!!1*/
INSERT INTO Staff(username, password, salt) VALUES ('AlexAdmin', '2021b81e2ff14dce586d5c606945a444579698d8ad22b5c8d6504dd175eee0b4', 'GVCGDAWOHBIVASG'); /*JavaGod1*/
INSERT INTO Staff(username, password, salt) VALUES ('OwenMakingAStart', 'f213250573677558082ca3a3cb0969155bd16ad4ebc186f95b8779cd85bdf3ba', 'LRMHBKCUYZIZKRV'); /*Slug&Lettuce4Life*/

INSERT INTO Customers(forename, surname, address_id) VALUES ('Tom', 'Pearson', (SELECT id FROM Addresses WHERE house_num_name='6'));
INSERT INTO Customers(forename, surname, address_id) VALUES ('Owen', 'Davies', (SELECT id FROM Addresses WHERE house_num_name='12'));
INSERT INTO Customers(forename, surname, address_id) VALUES ('Dianne', 'Foster', (SELECT id FROM Addresses WHERE house_num_name='76'));
INSERT INTO Customers(forename, surname, address_id) VALUES ('Frederick', 'Coles', (SELECT id FROM Addresses WHERE house_num_name='Small Grotto'));
INSERT INTO Customers(forename, surname, address_id) VALUES ('Derick', 'Thompson', (SELECT id FROM Addresses WHERE house_num_name='Babble'));
INSERT INTO Customers(forename, surname, address_id) VALUES ('Jenny', 'Wilkinson', (SELECT id FROM Addresses WHERE house_num_name='6'));
INSERT INTO Customers(forename, surname, address_id) VALUES ('Geoff', 'Pearson', (SELECT id FROM Addresses WHERE house_num_name='12'));

INSERT INTO Bicycles(frameset_id, handlebar_id, wheels_id, given_name) VALUES(1, 1, 1, 'Giants Road');
INSERT INTO Bicycles(frameset_id, handlebar_id, wheels_id, given_name) VALUES(2, 2, 3, 'Giants Mountain');
INSERT INTO Bicycles(frameset_id, handlebar_id, wheels_id, given_name) VALUES(7, 3, 5, 'Giants Hybrid');
INSERT INTO Bicycles(frameset_id, handlebar_id, wheels_id, given_name) VALUES(9, 4, 5, 'Dannies Hybrid');
INSERT INTO Bicycles(frameset_id, handlebar_id, wheels_id, given_name) VALUES(10, 5, 9, 'Dannies Mountain');

INSERT INTO Orders(customer_id, cost, order_status, bike_id, serial_number, order_date) VALUES (1, 209.97, 1, 1, 202211231, '2022-11-23');
INSERT INTO Orders(customer_id, cost, order_status, bike_id, serial_number, order_date) VALUES (2, 339.97, 2, 2, 202205102, '2022-05-10');
INSERT INTO Orders(customer_id, cost, order_status, bike_id, serial_number, order_date) VALUES (3, 569.97, 3, 3, 202209093, '2022-09-09');
INSERT INTO Orders(customer_id, cost, order_status, bike_id, serial_number, order_date) VALUES (4, 369.97, 3, 4, 202101014, '2021-01-01');
INSERT INTO Orders(customer_id, cost, order_status, bike_id, serial_number, order_date) VALUES (5, 319.97, 3, 5, 200912255, '2009-12-25');
INSERT INTO Orders(customer_id, cost, order_status, bike_id, serial_number, order_date) VALUES (5, 369.97, 2, 4, 202211296, '2022-11-29');