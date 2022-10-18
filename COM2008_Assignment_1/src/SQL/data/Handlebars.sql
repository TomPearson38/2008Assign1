USE buildabike;

CREATE TABLE Handlebars (
    id INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
    serial_number INTEGER NOT NULL UNIQUE,
    brand_name VARCHAR(255) NOT NULL,
    cost DOUBLE NOT NULL,
    style ENUM('straight','high','dropped') NOT NULL,
    PRIMARY KEY (id)
);
