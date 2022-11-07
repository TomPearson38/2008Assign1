USE team028;

CREATE TABLE Customers (
    id INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
    forename VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    address_id INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (address_id) REFERENCES addresses(id)
)
