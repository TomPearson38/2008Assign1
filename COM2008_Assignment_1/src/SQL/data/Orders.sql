USE team028;

CREATE TABLE Orders (
    order_number INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
    customer_id INTEGER NOT NULL,
    customer_given_name VARCHAR(20) NOT NULL,
    cost DOUBLE NOT NULL,
    order_status ENUM('pending','confirmed','fulfilled') NOT NULL,
    bike_id INTEGER NOT NULL,
    serial_number INTEGER,
    order_date DATE,
    PRIMARY KEY (order_number),
    FOREIGN KEY (bike_id) REFERENCES Bicycles(id),
    FOREIGN KEY (customer_id) REFERENCES Customers(id)
);