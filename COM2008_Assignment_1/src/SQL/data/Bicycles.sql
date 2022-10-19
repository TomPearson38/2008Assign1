USE buildabike;

CREATE TABLE Bicycles (
    id INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
    frameset_id INTEGER,
    handlebar_id INTEGER,
    wheels_id INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (frameset_id) REFERENCES Frames(id),
    FOREIGN KEY (handlebar_id) REFERENCES Handlebars(id),
    FOREIGN KEY (wheels_id) REFERENCES Wheels(id)
);
 
