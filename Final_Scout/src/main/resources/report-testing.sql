DROP TABLE IF EXISTS `scoutinfo`;
CREATE TABLE scoutinfo(

id INT AUTO_INCREMENT NOT NULL,
player VARCHAR(30) NOT NULL,
age INT NOT NULL,
region VARCHAR(45) NOT NULL,
position VARCHAR(15) NOT NULL,
playstyle VARCHAR(45) NOT NULL,
price DOUBLE NOT NULL,
injured BOOLEAN NOT NULL,
details VARCHAR(250) NOT NULL,
PRIMARY KEY (id)

);


INSERT INTO `scoutinfo` (`player`, `age`, `region`, `position`,`playstyle`,`price`,`injured`,`details`) VALUES ('Pedri', 19, 'Barcelona Spain','CM', 'Tiki Taka',90000000, false, 'Club is not selling, must activate release clause');
INSERT INTO `scoutinfo` (`player`, `age`, `region`, `position`,`playstyle`,`price`,`injured`,`details`) VALUES ('Tiago', 19, 'London England', 'CDM', 'Box to Box',5000000, false, 'Not in a club but agent is demanding a signing fee of 15%');