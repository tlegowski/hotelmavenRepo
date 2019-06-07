
INSERT INTO Address (city, street, apartment) VALUES ('wadowice', 'Kremowkowa', 2137);
INSERT INTO Address (city, street, apartment) VALUES ('Katowice', 'Mila', 420);
INSERT INTO Address (city, street, apartment) VALUES ('Gdansk', 'Grunwaldzka', 997);


INSERT INTO User (login, password, name, surname, ID_Address, userType, civilianID, evidenceNr) VALUES ('a', 'a', 'Tomek', 'Legowski', 3, 'ADMIN', '9712331441', 'ABC1412');

INSERT INTO User (login, password, name, surname, ID_Address, userType, civilianID, evidenceNr) VALUES ('kryscio', 'asd', 'Krystian', 'Lukasiak', 2, 'EMPLOYEE', '91244122', 'UXX92144');
INSERT INTO User (login, password, name, surname, ID_Address, userType, civilianID, evidenceNr) VALUES ('tomcio', 'asd', 'Patryk', 'Sawczuk', 2, 'EMPLOYEE', '755545462', 'URV92144');
INSERT INTO User (login, password, name, surname, ID_Address, userType, civilianID, evidenceNr) VALUES ('c', 'c', 'Krzysztof', 'Ibisz', 2, 'EMPLOYEE', '91244122', 'UXX92144');

INSERT INTO User (login, password, name, surname, ID_Address, userType, civilianID, evidenceNr) VALUES ('kazio', 'asd', 'Kazimierz', 'Nowak', 3, 'CUSTOMER', '682248457', 'HWP81273');
INSERT INTO User (login, password, name, surname, ID_Address, userType, civilianID, evidenceNr) VALUES ('b', 'b', 'Karol', 'Kowalski', 2, 'CUSTOMER', '46778484', 'OXK78528');
INSERT INTO User (login, password, name, surname, ID_Address, userType, civilianID, evidenceNr) VALUES ('asd', 'asd', 'Pawel', 'Lubelski', 3, 'CUSTOMER', '8577485', 'PNY572885');



INSERT INTO Room(describe, number) VALUES ('kolorowy pokoj dla dwoch osob', 51);
INSERT INTO Room(describe, number) VALUES ('przyjazny dla kotow i psow', 53);
INSERT INTO Room(describe, number) VALUES ('na specjalne potrzeby', 12);
INSERT INTO Room(describe, number) VALUES ('dobrze nasloneczniony', 14);
INSERT INTO Room(describe, number) VALUES ('maly pokoj', 55);

INSERT INTO Opinion(opinion) VALUES ('1');
INSERT INTO Opinion(opinion) VALUES ('2.5');
INSERT INTO Opinion(opinion) VALUES ('3.5');
INSERT INTO Opinion(opinion) VALUES ('4');
INSERT INTO Opinion(opinion) VALUES ('5');


INSERT INTO Reservation(ROOMID_ROOM_ID  , date, customerid, employeeid) VALUES (1, '2019-06-10',5,3);
INSERT INTO Reservation(ROOMID_ROOM_ID  , date, customerid, employeeid) VALUES (2, '2019-06-11',6,4);
INSERT INTO Reservation(ROOMID_ROOM_ID  , date, customerid, employeeid) VALUES (3, '2019-06-12',7,3);


INSERT INTO Report(report, RESERVATION_ID) VALUES ('zepsuta toaleta', 1);
INSERT INTO Report(report, RESERVATION_ID) VALUES ('nie ma wody', 2);
INSERT INTO Report(report, RESERVATION_ID) VALUES ('zarowka nie swieci', 2);

INSERT INTO Room_type ( describe) VALUES ( 'aneks kuchenny');
INSERT INTO Room_type (describe) VALUES ('deszczownica');
INSERT INTO Room_type (describe) VALUES ('podwojna toaleta');

INSERT INTO Room_type_has_room (fk_room_type, fk_room) VALUES (1,1);
INSERT INTO Room_type_has_room (fk_room_type, fk_room) VALUES (1,2);
INSERT INTO Room_type_has_room (fk_room_type, fk_room) VALUES (2,1);
INSERT INTO Room_type_has_room (fk_room_type, fk_room) VALUES (3,2);

INSERT INTO OPINION_HAS_USER (fk_opinion, fk_user) VALUES (3,5);
INSERT INTO OPINION_HAS_USER (fk_opinion, fk_user) VALUES (4,6);
INSERT INTO OPINION_HAS_USER (fk_opinion, fk_user) VALUES (3,7);

INSERT INTO REPORT_HAS_USER (FK_REPORT,FK_USER ) VALUES(1, 5);
INSERT INTO REPORT_HAS_USER (FK_REPORT,FK_USER ) VALUES(2, 6);
INSERT INTO REPORT_HAS_USER (FK_REPORT,FK_USER ) VALUES(3, 7);