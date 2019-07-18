CREATE SCHEMA IF NOT EXISTS fachmann;


/*ALTER DATABASE fachmann CHARACTER SET utf8 COLLATE utf8_unicode_ci;*/


USE fachmann;

--+ Create table for professions
CREATE TABLE IF NOT EXISTS professions
(
  id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  profession VARCHAR(100)
);


INSERT INTO fachmann.professions (profession) VALUES
('PLUMBER'),
('ELECTRICIAN');


--+ Create table for user data (login and details) and connect it with professions
CREATE TABLE IF NOT EXISTS userData
(
    id          BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    email       VARCHAR(100) UNIQUE NOT NULL,
    password    VARCHAR(100)        NOT NULL,
    role        VARCHAR(32) NOT NULL,
    signUpDate DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    name          VARCHAR(100)      NOT NULL,
    surname       VARCHAR(100)      NOT NULL,
    profession_id  BIGINT UNSIGNED,
    phoneNumber   BIGINT(14)        NOT NULL,
    city          VARCHAR(100)   NOT NULL,
    longitude     DOUBLE   NOT NULL,
    latitude      DOUBLE    NOT NULL,
    numberOfLikes INT UNSIGNED NOT NULL
);

ALTER TABLE userData ADD FOREIGN KEY (profession_id) REFERENCES professions (id);


ALTER TABLE userData CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


INSERT INTO fachmann.userData (email, password, role, name, surname, profession_id,
phoneNumber, city, longitude, latitude, numberOfLikes) VALUES
    ('client1@gmail.com', 'client1', 'CLIENT', 'Jan', 'Kowalski',
    null, 507654321, 'WARSZAWA', 51, 21.7, 2),
    ('client2@gmail.com', 'client2', 'CLIENT', 'Piotr', 'Mazur',
    null, 222222222, 'WARSZAWA', 51, 21.7, 2),
    ('professional1@gmail.com', 'professional1', 'PROFESSIONAL', 'Adaś', 'Nowak',
    1, 0048765432123, 'WARSZAWA', 51, 21.7, 3),
    ('professional2@gmail.com', 'professional2', 'PROFESSIONAL', 'Sylwester', 'Dębski',
    2, 444444444, 'WARSZAWA', 51, 21.7, 12),
    ('admin1@gmail.com', 'admin1', 'ADMIN', 'Józef', 'Nijaki',
    null, 555555555, 'WARSZAWA', 51, 21.7, 0);





--+create table for storing messages

CREATE TABLE IF NOT EXISTS fachmann.messages
(
    message_id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    message    VARCHAR(400) NOT NULL,
    wasRead    BOOLEAN DEFAULT '0',
    sender_id     BIGINT UNSIGNED,
    recipient_id  BIGINT UNSIGNED
);

ALTER TABLE messages
    ADD FOREIGN KEY (sender_id) REFERENCES userData (id);
ALTER TABLE messages
    ADD FOREIGN KEY (recipient_id) REFERENCES userData (id);



INSERT INTO messages (message, sender_id, recipient_id) VALUES
("Ok.", 1, 2),
("Bede o 16:00.", 1, 2);

                                                --+
--+ create table for storing comments

CREATE TABLE IF NOT EXISTS comments
(
 id BIGINT UNSIGNED UNIQUE PRIMARY KEY AUTO_INCREMENT,
 opinion_maker_id BIGINT UNSIGNED,
 recipient_id BIGINT UNSIGNED,
 opinion VARCHAR (400) NOT NULL
);

ALTER TABLE comments
    ADD FOREIGN KEY (opinion_maker_id) REFERENCES userData (id);
ALTER TABLE comments
    ADD FOREIGN KEY (recipient_id) REFERENCES userData (id);

INSERT INTO comments (opinion_maker_id, recipient_id, opinion) VALUES
(1, 2, "Dobra robota."),
(1, 1, "Dobra robota.");


CREATE TABLE IF NOT EXISTS contactStats
(
 id BIGINT UNSIGNED UNIQUE PRIMARY KEY AUTO_INCREMENT,
 contactDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
 reason VARCHAR (100),
 contactNight INT,
 contactMorning INT,
 contactAfternoon INT
);

INSERT INTO contactStats (reason, contactNight, contactMorning, contactAfternoon) VALUES
("Stupid reason", 0, 0, 0);

--+ALTER DATABASE fachmann CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;