USE jruteros;

INSERT INTO USERS( usuario, dni, apellido, nombre, domicilio, sexo, email, contrasenia, fechaNacimiento, habilitado) 
VALUES('admin', 23333333, 'Mike', 'Rourke', '2 y 60', 'm', 'sarasa@gmail.com.ar', 'admin', '1991-09-03 00:00:00', TRUE);

INSERT INTO ADMINISTRADORES(id)
VALUES(LAST_INSERT_ID());    

INSERT INTO USERS( usuario, dni, apellido, nombre, domicilio, sexo, email, contrasenia, fechaNacimiento, habilitado) 
VALUES('migue', 2222222, 'Migue', 'Castillo', '20 y 60', 'm', 'sarasa@gmail.com.ar', '1234', '1991-09-03 00:00:00', TRUE);

INSERT INTO USUARIOS(id)
VALUES(LAST_INSERT_ID());  
    
INSERT INTO PRIVACIDADES (nombre) 
VALUES('Privada'), ('Pública');

INSERT INTO FORMATOS (nombre) 
VALUES('Sólo ida '), ('Circular');

INSERT INTO DIFICULTADES (nombre) 
VALUES('Fácil'), ('Moderado'), ('Dificil'),
('Muy Dificil'), ('Sólo Expertos');

INSERT INTO ACTIVIDADES (nombre, habilitado) 
VALUES('Mountain bike', TRUE), ('Ciclismo', TRUE), ('Cicloturismo', TRUE),
('Senderismo', TRUE), ('Carrera por montaña', TRUE), ('Alpinismo', TRUE),
('Motociclismo', TRUE), ('Cuatriciclo', TRUE), ('Esquí', TRUE),
('Kayac', TRUE), ('Vela', TRUE), ('A caballo', TRUE);

INSERT INTO RUTAS (`id`,`descripcion`,`distancia`,`fechaRealizacion`,`nombre`,`tiempoEstimado`,`actividad_id`,`dificultad_id`,`formato_id`,`privacidad_id`,`propietario_id`) VALUES (1,'Es la ruta mas peligrosa de toda America Latina',66.00,'2019-01-03 00:00:00','Ruta 1','2020-04-22 20:00:00',12,4,1,2,2);

INSERT INTO `FOTOS` (`id`,`contentType`,`nombre`,`size`) VALUES (1,'image/jpeg','50741992_2165488810383215_1990899946525556736_n.jpg',147909);
INSERT INTO `FOTOS` (`id`,`contentType`,`nombre`,`size`) VALUES (2,'image/jpeg','32503790_1997596330505798_2344063094252109824_n.jpg',177187);

INSERT INTO `PUNTOS` (`id`,`latitud`,`longitud`) VALUES (1,'-35.79594959492106','-67.80356046523437');
INSERT INTO `PUNTOS` (`id`,`latitud`,`longitud`) VALUES (2,'-32.37575033453042','-62.09066984023437');
INSERT INTO `PUNTOS` (`id`,`latitud`,`longitud`) VALUES (3,'-35.04390446678014','-62.92563077773437');
INSERT INTO `PUNTOS` (`id`,`latitud`,`longitud`) VALUES (4,'-39.44931085538425','-67.84750577773437');
INSERT INTO `PUNTOS` (`id`,`latitud`,`longitud`) VALUES (5,'-37.80150416114822','-63.67270109023437');
INSERT INTO `PUNTOS` (`id`,`latitud`,`longitud`) VALUES (6,'-32.63517587674923','-67.89145109023437');

INSERT INTO `RUTAS_FOTOS` (`ruta_id`,`foto_id`) VALUES (1,1);
INSERT INTO `RUTAS_FOTOS` (`ruta_id`,`foto_id`) VALUES (1,2);

INSERT INTO `RUTAS_PUNTOS` (`ruta_id`,`punto_id`) VALUES (1,1);
INSERT INTO `RUTAS_PUNTOS` (`ruta_id`,`punto_id`) VALUES (1,2);
INSERT INTO `RUTAS_PUNTOS` (`ruta_id`,`punto_id`) VALUES (1,3);
INSERT INTO `RUTAS_PUNTOS` (`ruta_id`,`punto_id`) VALUES (1,4);
INSERT INTO `RUTAS_PUNTOS` (`ruta_id`,`punto_id`) VALUES (1,5);
INSERT INTO `RUTAS_PUNTOS` (`ruta_id`,`punto_id`) VALUES (1,6);