USE team028;

CREATE TABLE Addresses (
    id INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
    house_num_name VARCHAR(50) NOT NULL,
    street_name VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    post_code VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
)
