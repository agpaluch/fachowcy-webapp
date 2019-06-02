CREATE SCHEMA IF NOT EXISTS fachmann;

ALTER DATABASE fachmann CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE fachmann;

--+ Create table for user data (login and details)
CREATE TABLE IF NOT EXISTS userData
(
    id          INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    email       VARCHAR(100) UNIQUE NOT NULL,
    password    VARCHAR(100)        NOT NULL,
    signup_date DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    name          VARCHAR(100)      NOT NULL,
    surname       VARCHAR(100)      NOT NULL,
    profession    VARCHAR(32)              ,
    phoneNumber   BIGINT(11)        NOT NULL,
    city          ENUM ('Warsaw')   NOT NULL,
    longitude     DECIMAL(10, 7)    NOT NULL,
    latitude      DECIMAL(10, 7)    NOT NULL,
    numberOfLikes SMALLINT UNSIGNED NOT NULL
);


INSERT INTO fachmann.userData (email, password)
VALUES ('client1@gmail.com', 'client1'),
       ('client2@gmail.com', 'client2'),
       ('prof1@gmail.com', 'prof1'),
       ('prof2@gmail.com', 'prof2'),
       ('admin@gmail.com', 'admin');


--+
create table for user details
CREATE TABLE IF NOT EXISTS fachmann.ud
(
    login_id      INT UNSIGNED,
    name          VARCHAR(100)      NOT NULL,
    surname       VARCHAR(100)      NOT NULL,
    phoneNumber   BIGINT(11)        NOT NULL,
    city          ENUM ('Warsaw')   NOT NULL,
    longitude     DECIMAL(10, 7)    NOT NULL,
    latitude      DECIMAL(10, 7)    NOT NULL,
    numberOfLikes SMALLINT UNSIGNED NOT NULL
);


ALTER TABLE fachmann.ud
    ADD FOREIGN KEY (login_id) REFERENCES fachmann.ul (id);

INSERT INTO ud (login_id, name, surname, phoneNumber,
                city, longitude, latitude, numberOfLikes)
VALUES (1, 'aaa', 'Kowalski', 111111111, 'Warsaw', 23.0, 23.0, 0),
       (2, 'bbb', 'Manur', 222222222, 'Warsaw', 24.0, 24.0, 0),
       (3, 'ccc', 'Nowak', 333333333, 'Warsaw', 25.0, 25.0, 0),
       (4, 'ddd', 'Tomczyk', 444444444, 'Warsaw', 23.0, 26.0, 0),
       (5, 'eee', 'Żółć', 555555555, 'Warsaw', 23.0, 27.0, 0);



--+
create table for professions and connect it with userDetails


                                                --+
create table for storing comments


    --+
create table for storing messages

CREATE TABLE IF NOT EXISTS fachmann.messages
(
    message_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    message    VARCHAR(400) NOT NULL,
    wasRead    BOOLEAN DEFAULT '0',
    sender     BIGINT       NOT NULL,
    recipient  BIGINT       NOT NULL
);

ALTER TABLE fachmann.messages
    ADD FOREIGN KEY (sender) REFERENCES fachmann.userLogin (id);
ALTER TABLE fachmann.messages
    ADD FOREIGN KEY (recipient) REFERENCES fachmann.userLogin (id);

--+
create table for storing info about transactions
(
    who fixed sth for whom
)







