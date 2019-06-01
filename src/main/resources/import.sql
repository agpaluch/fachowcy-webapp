
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

INSERT INTO fachmann.messages (message_id, message, recipient, sender, wasRead) VALUES (1, 'Siema Heniu trza mi dżewo urąbać', 1, 2, 0);
INSERT INTO fachmann.messages (message_id, message, recipient, sender, wasRead) VALUES (2, 'Siema Heniu tępa ta twoja siekiera jak nóź do masła', 1, 3, 1);
INSERT INTO fachmann.messages (message_id, message, recipient, sender, wasRead) VALUES (3, 'Pochwalony, a hydraulik u was jest he?', 2, 2, 0);
INSERT INTO fachmann.messages (message_id, message, recipient, sender, wasRead) VALUES (4, 'Jak tu się pisze halo halo', 3, 4, 0);
INSERT INTO fachmann.messages (message_id, message, recipient, sender, wasRead) VALUES (5, 'Panie źle pan pralkę złożył. Kręci się w drugą stronę i zamiast czyste to jeszcze brudniejsze sie robią rzeczy', 4, 1, 1);