USE team028;

CREATE TABLE Staff (
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(64) NOT NULL,
    salt VARCHAR(15) NOT NULL,
    PRIMARY KEY (username)
);
