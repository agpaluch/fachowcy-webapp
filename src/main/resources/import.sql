
ALTER DATABASE fachmann CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

INSERT INTO fachmann.userLogin (id, email, password, role, signUpDate) VALUES (1, 'client1@gmail.com', 'client1', 1, '1991-01-01');
INSERT INTO fachmann.userLogin (id, email, password, role, signUpDate) VALUES (2, 'client2@gmail.com', 'client2', 1, '1991-01-01');
INSERT INTO fachmann.userLogin (id, email, password, role, signUpDate) VALUES (3, 'prof1@gmail.com', 'prof1', 0, '1991-01-01');
INSERT INTO fachmann.userLogin (id, email, password, role, signUpDate) VALUES (4, 'prof2@gmail.com', 'prof2', 0, '1991-01-01');
INSERT INTO fachmann.userLogin (id, email, password, role, signUpDate) VALUES (5, 'admin@gmail.com', 'admin', 2, '1991-01-01');

INSERT INTO fachmann.userDetails (id, name, surname, profession, phoneNumber, city, longitude, latitude, numberLikes, comments) VALUES (1, 'aaa', 'Kowalski', null , 111111111, 'Warsaw', 23.0, 23.0, 0, 'com1');
INSERT INTO fachmann.userDetails (id, name, surname, profession, phoneNumber, city, longitude, latitude, numberLikes, comments) VALUES  (2, 'bbb', 'Manur', null,  222222222,'Warsaw', 24.0, 24.0, 0, 'com1');
INSERT INTO fachmann.userDetails (id, name, surname, profession, phoneNumber, city, longitude, latitude, numberLikes, comments) VALUES (3, 'ccc', 'Nowak', 'PLUMBER' , 333333333,'Warsaw', 25.0, 25.0, 0, 'com1');
INSERT INTO fachmann.userDetails (id, name, surname, profession, phoneNumber, city, longitude, latitude, numberLikes, comments) VALUES (4, 'ddd', 'Tomczyk', 'ELECTRICIAN', 444444444,'Warsaw', 23.0, 26.0, 0, 'com1');
INSERT INTO fachmann.userDetails (id, name, surname, profession, phoneNumber, city, longitude, latitude, numberLikes, comments) VALUES  (5, 'eee', 'Żółć', null, 555555555,'Warsaw', 23.0, 27.0, 0, 'com1');

ALTER TABLE fachmann.userDetails DROP FOREIGN KEY FKsi6y9hiyw9phgcji85fg2cgkt;
ALTER TABLE fachmann.userDetails ADD CONSTRAINT FKsi6y9hiyw9phgcji85fg2cgkt FOREIGN KEY (id) REFERENCES fachmann.userLogin (id) ON DELETE CASCADE;