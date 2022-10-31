USE team028;

CREATE TABLE Wheels (
    id INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
    serial_number INTEGER NOT NULL UNIQUE,
    brand_name VARCHAR(255) NOT NULL,
    cost DOUBLE NOT NULL,
    diameter DOUBLE NOT NULL,
    tyre_type ENUM('road','mountain','hybrid') NOT NULL,
    brake_type ENUM('rim','disc') NOT NULL,
    PRIMARY KEY (id)
);
