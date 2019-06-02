
INSERT INTO Address (city, street, apartment) VALUES ('wadowice', 'Kremowkowa', 2137);
INSERT INTO Address (city, street, apartment) VALUES ('Katowice', 'Mila', 420);
INSERT INTO Address (city, street, apartment) VALUES ('Gdansk', 'Grunwaldzka', 997);


INSERT INTO User (login, password, name, surname, ID_Address, userType, civilianID, evidenceNr) VALUES ('admin', 'admin', 'Tomek', 'Legowski', 3, 'ADMIN', '9712331441', 'ABC1412');
INSERT INTO User (login, password, name, surname, ID_Address, userType, civilianID, evidenceNr) VALUES ('adas', 'asd', 'Adam', 'Kopytko', 1, 'CUSTOMER', '851129772', 'PKK81273');
INSERT INTO User (login, password, name, surname, ID_Address, userType, civilianID, evidenceNr) VALUES ('kryscio', 'asd', 'Krystian', 'Lukasiak', 2, 'EMPLOYEE', '91244122', 'UXX92144');

INSERT INTO Room(describe, number) VALUES ('kolorowy pokoj dla dwoch osob', 1);
INSERT INTO Room(describe, number) VALUES ('przyjazny dla kotow i psow', 2);
INSERT INTO Room(describe, number) VALUES ('na specjalne potrzeby', 3);

INSERT INTO Opinion(opinion) VALUES ('przyjazny pobyt, polecam');
INSERT INTO Opinion(opinion) VALUES ('slabe warunki, nie polecam');
INSERT INTO Opinion(opinion) VALUES ('wszystko ok');

INSERT INTO Report(report, idroom) VALUES ('zepsuta toaleta', 1);
INSERT INTO Report(report, idroom) VALUES ('nie ma wody', 2);
INSERT INTO Report(report, idroom) VALUES ('zarowka nie swieci', 2);

INSERT INTO Reservation(roomid, date) VALUES (1, '2019-05-05');
INSERT INTO Reservation(roomid) VALUES (2);
INSERT INTO Reservation(roomid) VALUES (3);

INSERT INTO Reservation_has_room (fk_reservation, fk_room) VALUES (1,1);
INSERT INTO Reservation_has_room (fk_reservation, fk_room) VALUES (2,2);
INSERT INTO Reservation_has_room (fk_reservation, fk_room) VALUES (3,3);

INSERT INTO RESERVATION_HAS_USERS  (fk_reservation, fk_user) VALUES (1, 1);
INSERT INTO RESERVATION_HAS_USERS  (fk_reservation, fk_user) VALUES (2, 2);
INSERT INTO RESERVATION_HAS_USERS  (fk_reservation, fk_user) VALUES (3, 3);

INSERT INTO Room_type (floor, view) VALUES (2, 'widok na wschod');
INSERT INTO Room_type (floor, view) VALUES (3, 'widok na zachod');
INSERT INTO Room_type (floor, view) VALUES (1, 'widok na poludnie');

INSERT INTO Room_type_has_room (fk_room_type, fk_room) VALUES (1,1);
INSERT INTO Room_type_has_room (fk_room_type, fk_room) VALUES (2,1);
INSERT INTO Room_type_has_room (fk_room_type, fk_room) VALUES (3,2);