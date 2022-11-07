USE team028;

CREATE TABLE Staff (
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (address_id)
)
