USE team028;

DELETE FROM Bicycles;
DELETE FROM Frames;
DELETE FROM Wheels;
DELETE FROM Handlebars;
DELETE FROM Gearsets;

INSERT INTO Gearsets(name) VALUES ("2 Speed");
INSERT INTO Gearsets(name) VALUES ("3 Speed");
INSERT INTO Gearsets(name) VALUES ("4 Speed");
INSERT INTO Gearsets(name) VALUES ("5 Speed");
INSERT INTO Gearsets(name) VALUES ("8 Speed");
INSERT INTO Gearsets(name) VALUES ("10 Speed");

INSERT INTO Handlebars(serial_number, brand_name, cost, style) VALUES (1100001, "Dannies", 19.99, 3);
INSERT INTO Handlebars(serial_number, brand_name, cost, style) VALUES (1100002, "Dannies", 99.99, 3);
INSERT INTO Handlebars(serial_number, brand_name, cost, style) VALUES (1100003, "Forwards", 199.99, 3);
INSERT INTO Handlebars(serial_number, brand_name, cost, style) VALUES (1200001, "TallNMighty", 59.99, 3);
INSERT INTO Handlebars(serial_number, brand_name, cost, style) VALUES (1200002, "TallNMighty", 99.99, 3);
INSERT INTO Handlebars(serial_number, brand_name, cost, style) VALUES (1200003, "Giants", 119.99, 3);
INSERT INTO Handlebars(serial_number, brand_name, cost, style) VALUES (1300001, "LowRiderz", 99.99, 3);
INSERT INTO Handlebars(serial_number, brand_name, cost, style) VALUES (1300002, "Dannies", 19.99, 3);
INSERT INTO Handlebars(serial_number, brand_name, cost, style) VALUES (1300003, "LowRiderz", 119.99, 3);

INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type) VALUES (2110001, "Dannies", 59.99, 650.0, 1, 1);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type) VALUES (2110002, "Dannies", 89.99, 650.0, 1, 2);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type) VALUES (2120001, "Dannies", 99.99, 600.0, 2, 1);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type) VALUES (2120002, "Dannies", 119.99, 600.0, 2, 2);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type) VALUES (2130001, "Dannies", 159.99, 622.0, 3, 1);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type) VALUES (2130002, "Dannies", 199.99, 622.0, 3, 2);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type) VALUES (2210001, "Forwards", 29.99, 650.0, 1, 1);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type) VALUES (2210002, "Forwards", 49.99, 650.0, 1, 2);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type) VALUES (2220001, "Forwards", 79.99, 600.0, 2, 1);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type) VALUES (2220002, "Forwards", 99.99, 600.0, 2, 2);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type) VALUES (2230001, "Forwards", 119.99, 622.0, 3, 1);
INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type) VALUES (2230002, "Forwards", 159.99, 622.0, 3, 2);

INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id) VALUES (311001, "Giants", 119.99, 99.0, FALSE, (SELECT id FROM Gearsets WHERE name="2 Speed"));
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id) VALUES (311002, "Giants", 129.99, 99.0, TRUE, (SELECT id FROM Gearsets WHERE name="2 Speed"));
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id) VALUES (311003, "Giants", 139.99, 99.0, TRUE, (SELECT id FROM Gearsets WHERE name="3 Speed"));
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id) VALUES (311004, "Giants", 149.99, 99.0, FALSE, (SELECT id FROM Gearsets WHERE name="4 Speed"));
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id) VALUES (311005, "Giants", 159.99, 99.0, TRUE, (SELECT id FROM Gearsets WHERE name="5 Speed"));
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id) VALUES (311006, "Giants", 179.99, 109.0, FALSE, (SELECT id FROM Gearsets WHERE name="8 Speed"));
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id) VALUES (311007, "Giants", 199.99, 119.0, TRUE, (SELECT id FROM Gearsets WHERE name="10 Speed"));
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id) VALUES (312001, "Dannies", 119.99, 99.0, FALSE, (SELECT id FROM Gearsets WHERE name="2 Speed"));
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id) VALUES (312002, "Dannies", 139.99, 99.0, TRUE, (SELECT id FROM Gearsets WHERE name="3 Speed"));
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id) VALUES (312003, "Dannies", 129.99, 109.0, TRUE, (SELECT id FROM Gearsets WHERE name="4 Speed"));
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id) VALUES (312004, "Dannies", 149.99, 109.0, FALSE, (SELECT id FROM Gearsets WHERE name="5 Speed"));
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id) VALUES (312005, "Dannies", 199.99, 119.0, TRUE, (SELECT id FROM Gearsets WHERE name="8 Speed"));
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id) VALUES (312006, "Dannies", 229.99, 119.0, FALSE, (SELECT id FROM Gearsets WHERE name="10 Speed"));
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id) VALUES (312007, "Dannies", 249.99, 129.0, FALSE, (SELECT id FROM Gearsets WHERE name="8 Speed"));
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id) VALUES (312008, "Dannies", 299.99, 129.0, TRUE, (SELECT id FROM Gearsets WHERE name="3 Speed"));