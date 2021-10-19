insert into PROFESOR (NOMBRE, APELLIDOS,NICKNAME, PASSWORD) values ('Marcos', 'Puente','marcos.puente','$2a$10$wl85fG3SvoOqVBaUgr1b/OONuc0b0slNJ6bZCoOhS528smitjqbi.');
insert into PROFESOR (NOMBRE, APELLIDOS,NICKNAME, PASSWORD) values ('Roger', 'Pressman','pressman','$2a$10$wl85fG3SvoOqVBaUgr1b/OONuc0b0slNJ6bZCoOhS528smitjqbi.');
insert into PROFESOR (NOMBRE, APELLIDOS,NICKNAME, PASSWORD) values ('Martin', 'Fawler','fawler','$2a$10$wl85fG3SvoOqVBaUgr1b/OONuc0b0slNJ6bZCoOhS528smitjqbi.');
insert into PROFESOR (NOMBRE, APELLIDOS,NICKNAME, PASSWORD) values ('Steve', 'Wozniak','wozniak','$2a$10$wl85fG3SvoOqVBaUgr1b/OONuc0b0slNJ6bZCoOhS528smitjqbi.');
insert into PROFESOR (NOMBRE, APELLIDOS,NICKNAME, PASSWORD) values ('Steve', 'Jobs','jobs','$2a$10$wl85fG3SvoOqVBaUgr1b/OONuc0b0slNJ6bZCoOhS528smitjqbi.');
insert into PROFESOR (NOMBRE, APELLIDOS,NICKNAME, PASSWORD) values ('Bill', 'Gates','gates','$2a$10$wl85fG3SvoOqVBaUgr1b/OONuc0b0slNJ6bZCoOhS528smitjqbi.');
insert into PROFESOR (NOMBRE, APELLIDOS,NICKNAME, PASSWORD) values ('Paul', 'Allen','allen','$2a$10$wl85fG3SvoOqVBaUgr1b/OONuc0b0slNJ6bZCoOhS528smitjqbi.');
insert into PROFESOR (NOMBRE, APELLIDOS,NICKNAME, PASSWORD) values ('Steve', 'Ballmer','ballmer','$2a$10$wl85fG3SvoOqVBaUgr1b/OONuc0b0slNJ6bZCoOhS528smitjqbi.');


insert into ROL (ID_ROL, NOMBRE_ROL ) values (1, 'ROL_PROFESOR');
insert into ROL (ID_ROL, NOMBRE_ROL ) values (2,'ROL_ADMIN');

insert into PROFESOR_ROL (ID_PROFESOR, ID_ROL ) values (1,1);
insert into PROFESOR_ROL (ID_PROFESOR, ID_ROL ) values (1,2);
insert into PROFESOR_ROL (ID_PROFESOR, ID_ROL ) values (2,1);
insert into PROFESOR_ROL (ID_PROFESOR, ID_ROL ) values (3,1);
insert into PROFESOR_ROL (ID_PROFESOR, ID_ROL ) values (4,1);
insert into PROFESOR_ROL (ID_PROFESOR, ID_ROL ) values (5,1);
insert into PROFESOR_ROL (ID_PROFESOR, ID_ROL ) values (6,1);
insert into PROFESOR_ROL (ID_PROFESOR, ID_ROL ) values (7,1);
insert into PROFESOR_ROL (ID_PROFESOR, ID_ROL ) values (8,1);



insert into MODULO (NOMBRE_MODULO) values ('Entornos de desarrollo');
insert into MODULO (NOMBRE_MODULO) values ('Sistemas Informáticos');
insert into MODULO (NOMBRE_MODULO) values ('Programacion');
insert into MODULO (NOMBRE_MODULO) values ('Bases de Datos');
insert into MODULO (NOMBRE_MODULO) values ('Lenguaje de Marcas y SGI');
insert into MODULO (NOMBRE_MODULO) values ('Formación y Orientación Laboral');

insert into MODULO (NOMBRE_MODULO) values ('Desarrollo Entorno Servidor');
insert into MODULO (NOMBRE_MODULO) values ('Desarrollo Entono Cliente');
insert into MODULO (NOMBRE_MODULO) values ('Desarrollo de Interfaces Web');
insert into MODULO (NOMBRE_MODULO) values ('Despliegue de Aplicaciones Web');
insert into MODULO (NOMBRE_MODULO) values ('Inglés');
insert into MODULO (NOMBRE_MODULO) values ('Empresa e iniciativa emprendedora');


insert into EMAIL (DIRECCION_EMAIL,ID_PROFESOR) values ('marcos@des.com',1);
insert into EMAIL (DIRECCION_EMAIL,ID_PROFESOR) values ('marcosfppf@gmail.com',1);
insert into EMAIL (DIRECCION_EMAIL,ID_PROFESOR) values ('roger@des.com',2);
insert into EMAIL (DIRECCION_EMAIL,ID_PROFESOR) values ('martin@des.com',3);
insert into EMAIL (DIRECCION_EMAIL,ID_PROFESOR) values ('steve@des.com',4);
insert into EMAIL (DIRECCION_EMAIL,ID_PROFESOR) values ('wozniak@apple.com',4);
insert into EMAIL (DIRECCION_EMAIL,ID_PROFESOR) values ('jobs@apple.com',5);
insert into EMAIL (DIRECCION_EMAIL,ID_PROFESOR) values ('gates@microsoft.com',6);
insert into EMAIL (DIRECCION_EMAIL,ID_PROFESOR) values ('allen@microsoft.com',7);
insert into EMAIL (DIRECCION_EMAIL,ID_PROFESOR) values ('ballmer@microsoft.com',8);


insert into PROFESOR_MODULO(ID_PROFESOR, ID_MODULO) values (1, 1);
insert into PROFESOR_MODULO(ID_PROFESOR, ID_MODULO) values (1, 7);
insert into PROFESOR_MODULO(ID_PROFESOR, ID_MODULO) values (1, 5);
insert into PROFESOR_MODULO(ID_PROFESOR, ID_MODULO) values (1, 3);
insert into PROFESOR_MODULO(ID_PROFESOR, ID_MODULO) values (2, 2);
insert into PROFESOR_MODULO(ID_PROFESOR, ID_MODULO) values (3, 5);
insert into PROFESOR_MODULO(ID_PROFESOR, ID_MODULO) values (4, 2);
insert into PROFESOR_MODULO(ID_PROFESOR, ID_MODULO) values (4, 10);
insert into PROFESOR_MODULO(ID_PROFESOR, ID_MODULO) values (4, 3);
insert into PROFESOR_MODULO(ID_PROFESOR, ID_MODULO) values (8, 2);
insert into PROFESOR_MODULO(ID_PROFESOR, ID_MODULO) values (7, 8);
insert into PROFESOR_MODULO(ID_PROFESOR, ID_MODULO) values (5, 9);
insert into PROFESOR_MODULO(ID_PROFESOR, ID_MODULO) values (5, 6);
