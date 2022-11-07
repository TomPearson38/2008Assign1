USE team028;

CREATE TABLE Addresses (
    id INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
    house_num_name VARCHAR(20) NOT NULL,
    street_name VARCHAR(20) NOT NULL,
    post_code VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
)
