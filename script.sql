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