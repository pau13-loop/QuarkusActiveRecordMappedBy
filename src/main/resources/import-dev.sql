DROP TABLE IF EXISTS people;
DROP TABLE IF EXISTS band;
CREATE TABLE band
(
    name VARCHAR(255) NOT NULL UNIQUE,
    location VARCHAR(255),
    PRIMARY KEY (name)
) ENGINE = InnoDB;
CREATE TABLE people
(
    name VARCHAR (255) NOT NULL UNIQUE, 
    age INT, 
    band_name VARCHAR (255),
    PRIMARY KEY (name),
    CONSTRAINT `fk_people_business`
        FOREIGN KEY (band_name) REFERENCES band (name)
        ON DELETE SET NULL
        ON UPDATE SET NULL
) ENGINE = InnoDB;
INSERT INTO 
    band (name, location) 
VALUES
    ('Queen', 'England'),
    ('El Último de la Fila', 'Spain'),
    ('Nirvana', 'Washington');
INSERT INTO 
    people (name, age, band_name) 
VALUES 
    ('Freddy Mercury', 65, 'Queen'),
    ('Manolo Garcia', 70, 'El Último de la Fila'),
    ('Kurt Cobain', 55, 'Nirvana');