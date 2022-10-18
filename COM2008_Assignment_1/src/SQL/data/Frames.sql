USE buildabike;

CREATE TABLE Frames (
    id INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
    serial_number INTEGER NOT NULL UNIQUE,
    brand_name VARCHAR(255) NOT NULL,
    cost DOUBLE NOT NULL,
    size DOUBLE NOT NULL,
    shocks BOOLEAN NOT NULL,
    gears_id INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (gears_id) REFERENCES Gearsets(id)
);
