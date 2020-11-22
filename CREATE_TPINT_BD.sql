drop database tpintegrador_bd;
CREATE DATABASE tpintegrador_bd;
use tpintegrador_bd;

SET GLOBAL time_zone = '-3:00'; -- para que no tire problemas la app por el problema de la zona horaria

CREATE TABLE tipoUsuarios(
	idTipoUsuario int NOT NULL auto_increment,
    descripcion varchar(50) NOT NULL,
    primary key (idTipoUsuario)
);

CREATE TABLE tipoMovimientos(
	idTipoMovimiento int NOT NULL auto_increment,
    descripcion varchar(50) NOT NULL,
    primary key (idTipoMovimiento)
);

CREATE TABLE tipoCuentas(
	idTipoCuenta int NOT NULL auto_increment,
    descripcion varchar(50) NOT NULL,
    primary key (idTipoCuenta)
);

CREATE TABLE provincias(
	idProvincia int NOT NULL auto_increment,
    descripcion varchar(50) NOT NULL,
    primary key (idProvincia)
);

CREATE TABLE localidades(
	idLocalidad int NOT NULL auto_increment,
    descripcion varchar(50) NOT NULL,
    primary key (idLocalidad)
);

CREATE TABLE nacionalidades(
	idNacionalidad int NOT NULL auto_increment,
    descripcion varchar(50) NOT NULL,
    primary key (idNacionalidad)
);

CREATE TABLE usuarios(
	idUsuario bigint NOT NULL auto_increment,
	usuario varchar(50) NOT NULL unique,
    contrasenia varchar(50) NOT NULL,
    idTipoUsuario int NOT NULL,
    DNI VARCHAR(8) NOT NULL unique,
    CUIL VARCHAR(11) NOT NULL,
    nombre VARCHAR(100) NULL,
    apellido VARCHAR(100) NULL,
    sexo VARCHAR(20) NULL,
    fechaNacimiento date NULL,
    direccion VARCHAR(100) NULL,
    idLocalidad int NULL,
    idNacionalidad int NULL,
    idProvincia int NULL,
    mail VARCHAR(100) NOT NULL unique,
    telefono VARCHAR(50) NULL,
    idEstado bit NOT NULL,
    primary key (idUsuario),
    constraint foreign key (idLocalidad) references localidades (idLocalidad),
    constraint foreign key (idNacionalidad) references nacionalidades (idNacionalidad),
    constraint foreign key (idProvincia) references provincias (idProvincia),
    constraint foreign key (idTipoUsuario) references tipoUsuarios (idTipoUsuario)
);

CREATE TABLE cuentas(
	idCuenta bigint NOT NULL auto_increment,
    idUsuario bigint null, -- agregada ultima actualizacion
    numeroCuenta bigint NOT NULL unique,
    idTipoCuenta int NOT NULL,
    fechaCreacion date NOT NULL,
    CBU bigint NOT NULL unique,
    saldo decimal NOT NULL,
    idEstado bit NOT NULL,
    primary key (idCuenta),
    constraint foreign key (idTipoCuenta) references tipoCuentas (idTipoCuenta),
    constraint foreign key (idUsuario) references usuarios (idUsuario) -- agregada ultima actualizacion
);

CREATE TABLE movimientos(
	idMovimiento bigint NOT NULL auto_increment,
    idCuenta bigint not null,  -- agregada ultima actualizacion
    idTipoMovimiento int NOT NULL,
    fecha date NOT NULL,
    detalle varchar(500) NOT NULL,
    importe decimal NOT NULL,
    cuentaDestino bigint NOT NULL,
    primary key (idMovimiento),
    constraint foreign key (idTipoMovimiento) references tipoMovimientos (idTipoMovimiento),
    constraint foreign key (idCuenta) references cuentas (idCuenta) -- agregada ultima actualizacion
);

CREATE TABLE prestamos(
	idPrestamo bigint NOT NULL auto_increment,
    idCuenta bigint not null, -- agregada ultima actualizacion
    importeAdevolver decimal NOT NULL,
    fecha date NOT NULL,
    montoSolicitado decimal NOT NULL,
    cantidadMeses int NOT NULL,
    valorCuota decimal NOT NULL,
    estado varchar(15) NOT NULL, -- cambio actualizacion 22/11 estados= pendiente, aprobado, rechazado
    primary key (idPrestamo),
    constraint foreign key (idCuenta) references cuentas (idCuenta) -- agregada ultima actualizacion
);

/* ------------------------------------------
             PRECARGA DE DATOS
---------------------------------------------*/

insert into tipoUsuarios (descripcion) values ('Administrador');
insert into tipoUsuarios (descripcion) values ('Usuario');

insert into tipoCuentas (descripcion) values ('Caja de ahorro');
insert into tipoCuentas (descripcion) values ('Cuenta corriente');

insert into tipoMovimientos(descripcion) values ('Alta de cuenta');
insert into tipoMovimientos(descripcion) values ('Alta de un préstamo');
insert into tipoMovimientos(descripcion) values ('Pago de préstamo');
insert into tipoMovimientos(descripcion) values ('Transferencia');

insert into provincias (descripcion) values('Buenos Aires');
insert into provincias (descripcion) values('Salta');
insert into provincias (descripcion) values('Jujuy');
insert into provincias (descripcion) values('Entre Rios');
insert into provincias (descripcion) values('Misiones');
insert into provincias (descripcion) values('Mendoza');
insert into provincias (descripcion) values('Corrientes');
insert into provincias (descripcion) values('La Rioja');
insert into provincias (descripcion) values('La Pampa');
insert into provincias (descripcion) values('Chubut');
insert into provincias (descripcion) values('Rio Negro');
insert into provincias (descripcion) values('Formosa');
insert into provincias (descripcion) values('Chaco');
insert into provincias (descripcion) values('Neuquen');
insert into provincias (descripcion) values('Tierra del Fuego');

insert into localidades (descripcion) values ('San Miguel');
insert into localidades (descripcion) values ('Bella Vista');
insert into localidades (descripcion) values ('Caseros');
insert into localidades (descripcion) values ('Pilar');
insert into localidades (descripcion) values ('Derqui');
insert into localidades (descripcion) values ('Manzanares');
insert into localidades (descripcion) values ('Zelaya');
insert into localidades (descripcion) values ('Tigre');
insert into localidades (descripcion) values ('Merlo');
insert into localidades (descripcion) values ('Moreno');
insert into localidades (descripcion) values ('Lujan');
insert into localidades (descripcion) values ('San Martin');
insert into localidades (descripcion) values ('Tapiales');
insert into localidades (descripcion) values ('Sarandi');
insert into localidades (descripcion) values ('Haedo');

insert into nacionalidades (descripcion) values ('Argentina');
insert into nacionalidades (descripcion) values ('Brasil');
insert into nacionalidades (descripcion) values ('Chile');
insert into nacionalidades (descripcion) values ('Uruguay');
insert into nacionalidades (descripcion) values ('Paraguay');
insert into nacionalidades (descripcion) values ('Venezuela');
insert into nacionalidades (descripcion) values ('Guatemala');
insert into nacionalidades (descripcion) values ('Peru');
insert into nacionalidades (descripcion) values ('Colombia');
insert into nacionalidades (descripcion) values ('Ecuador');
insert into nacionalidades (descripcion) values ('Nicaragua');
insert into nacionalidades (descripcion) values ('Panama');
insert into nacionalidades (descripcion) values ('Honduras');
insert into nacionalidades (descripcion) values ('Mexico');
insert into nacionalidades (descripcion) values ('Canada');

insert into usuarios (usuario, contrasenia, idTipoUsuario, DNI, CUIL, nombre, apellido, sexo, fechaNacimiento, direccion, idLocalidad, idNacionalidad, idProvincia, mail, telefono, idEstado) 
values('admin', 'admin', 1, '11111111', '20111111111', 'admin', 'admin', 'Indefinido', '1980-12-10', 'calleadmin', 1, 1, 1, 'admin@gmail.com', '44444',1);

insert into usuarios (usuario, contrasenia, idTipoUsuario, DNI, CUIL, nombre, apellido, sexo, fechaNacimiento, direccion, idLocalidad, idNacionalidad, idProvincia, mail, telefono, idEstado) 
values ('usu', '123', 2, '222', '2222', 'nombreUsuario', 'apellidoUsuario', 'masculino', '1980-12-10', 'calleFalsa', 1, 1, 1, 'mailfake@gmail.com', '44556677',1);

INSERT into usuarios(usuario,contrasenia,idTipoUsuario,DNI,CUIL,nombre,apellido,sexo,fechaNacimiento,direccion,idLocalidad,idNacionalidad,idProvincia,mail,telefono,idEstado) 
VALUES ('MFernandez','MFernandez',2,30115594,20301155949,'Maximiliano','Fernandez','Masculino','1980-12-10','Alvear 123',1,1,1,'mfernandez@gmail.com',4283959844,1);

INSERT into usuarios(usuario,contrasenia,idTipoUsuario,DNI,CUIL,nombre,apellido,sexo,fechaNacimiento,direccion,idLocalidad,idNacionalidad,idProvincia,mail,telefono,idEstado) 
VALUES ('LRodriguez','LRodriguez',2,33333333,20361151551,'Leandro','Rodriguez','Masculino','1991-06-25','Sarasa 53',4,1,1,'sarasa@gmail.com',4983567222,1);

INSERT into usuarios(usuario,contrasenia,idTipoUsuario,DNI,CUIL,nombre,apellido,sexo,fechaNacimiento,direccion,idLocalidad,idNacionalidad,idProvincia,mail,telefono,idEstado) 
VALUES ('JRubino','JRubino',2,26115555,26261155555,'Josue','Rubino','Masculino','1985-09-15','Arias 832',8,1,1,'josue_capo@gmail.com',4233367222,1);

INSERT into usuarios(usuario,contrasenia,idTipoUsuario,DNI,CUIL,nombre,apellido,sexo,fechaNacimiento,direccion,idLocalidad,idNacionalidad,idProvincia,mail,telefono,idEstado) 
VALUES ('RGimenez','RGimenez',2,30115556,20301155561,'Roberto','Gimenez','Masculino','1999-03-10','sarasa 1',4,6,3,'rgimenez@gmail.com',4283967222,1);

INSERT into usuarios(usuario,contrasenia,idTipoUsuario,DNI,CUIL,nombre,apellido,sexo,fechaNacimiento,direccion,idLocalidad,idNacionalidad,idProvincia,mail,telefono,idEstado) 
VALUES ('COlivera','COlivera',2,36115502,20361155021,'Carla','Olivera','Femenino','1982-10-20','Sarasa 605',2,3,1,'genio@gmail.com',4235467222,1);

INSERT into usuarios(usuario,contrasenia,idTipoUsuario,DNI,CUIL,nombre,apellido,sexo,fechaNacimiento,direccion,idLocalidad,idNacionalidad,idProvincia,mail,telefono,idEstado) 
VALUES ('FPerez','FPerez',2,36255555,20362555559,'Fernando','Perez','Masculino','1990-01-01','sarasa 523',5,2,9,'kpo@gmail.com',4327897222,1);

INSERT into usuarios(usuario,contrasenia,idTipoUsuario,DNI,CUIL,nombre,apellido,sexo,fechaNacimiento,direccion,idLocalidad,idNacionalidad,idProvincia,mail,telefono,idEstado) 
VALUES ('NDiaz','NDiaz',2,36116000,20361160001,'Nacho','Diaz','Masculino','1992-12-20','sara 1',2,2,2,'harrypotter@gmail.com',1204567222,1);

INSERT into usuarios(usuario,contrasenia,idTipoUsuario,DNI,CUIL,nombre,apellido,sexo,fechaNacimiento,direccion,idLocalidad,idNacionalidad,idProvincia,mail,telefono,idEstado) 
VALUES ('AGutierrez','AGutierrez',2,40260541,21402605418,'Ailen','Gutierrez','Femenino','1999-08-15','Sarasa 10',3,1,8,'samid@gmail.com',3904567222,1);

INSERT into usuarios(usuario,contrasenia,idTipoUsuario,DNI,CUIL,nombre,apellido,sexo,fechaNacimiento,direccion,idLocalidad,idNacionalidad,idProvincia,mail,telefono,idEstado) 
VALUES ('MQuiroga','MQuiroga',2,40115555,20401155552,'Marcelo','Quiroga','Masculino','1986-04-05','Sarasa 43',8,3,1,'elboga@gmail.com',6024567222,1);

INSERT into usuarios(usuario,contrasenia,idTipoUsuario,DNI,CUIL,nombre,apellido,sexo,fechaNacimiento,direccion,idLocalidad,idNacionalidad,idProvincia,mail,telefono,idEstado) 
VALUES ('SDelboni','SDelboni',2,36116666,20361166668,'Salvador','Delboni','Masculino','1990-08-10','Belgrano 666',9,10,4,'tortuga@gmail.com',7540567222,1);

INSERT into usuarios(usuario,contrasenia,idTipoUsuario,DNI,CUIL,nombre,apellido,sexo,fechaNacimiento,direccion,idLocalidad,idNacionalidad,idProvincia,mail,telefono,idEstado) 
VALUES ('TLedesma','TLedesma',2,39666555,22396665556,'Tomas','Ledesma','Masculino','1991-01-05','Sarasa 500',15,15,15,'cabezon@gmail.com',3024567222,1);

INSERT into usuarios(usuario,contrasenia,idTipoUsuario,DNI,CUIL,nombre,apellido,sexo,fechaNacimiento,direccion,idLocalidad,idNacionalidad,idProvincia,mail,telefono,idEstado) 
VALUES ('RBarili','RBarili',2,43135698,26431356987,'Rodolfo','Barili','Masculino','1996-07-16','Sarsa 113',12,13,10,'riverplate@gmail.com',6054567222,1);

INSERT into usuarios(usuario,contrasenia,idTipoUsuario,DNI,CUIL,nombre,apellido,sexo,fechaNacimiento,direccion,idLocalidad,idNacionalidad,idProvincia,mail,telefono,idEstado) 
VALUES ('MJackson','MJackson',2,37225555,20372255556,'Michael','Jackson','Masculino','1910-10-15','Sarsa 300',10,14,11,'jackson_90@gmail.com',9504567222,1);

INSERT into usuarios(usuario,contrasenia,idTipoUsuario,DNI,CUIL,nombre,apellido,sexo,fechaNacimiento,direccion,idLocalidad,idNacionalidad,idProvincia,mail,telefono,idEstado) 
VALUES ('WBlanco','WBlanco',2,36119999,2036119991,'Walter','Blanco','Masculino','1969-11-07','Peron 100',15,12,10,'elraton666@gmail.com',3650567222,1);

insert into cuentas (idUsuario, numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado) values (2, 11111111, 1, '2020/10/21', 11111111, 10000 ,1);
insert into cuentas (idUsuario, numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado) values (3, 22222222, 1, '2020/10/22', 22222222, 10000 ,1);
insert into cuentas (idUsuario, numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado) values (3, 33333333, 1, '2020/10/23', 33333333, 10000 ,1);
insert into cuentas (idUsuario, numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado) values (4, 44444444, 1, '2020/10/24', 44444444, 10000 ,1);
insert into cuentas (idUsuario, numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado) values (2, 55555555, 1, '2020/10/25', 55555555, 10000 ,1);

insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (2, 1, '2020-10-21', 'blablablablablabla', 5000, 123456789);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (1, 2, '2020-09-22', 'blablablablablabla', 4000, 123456789);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (2, 3, '2020-08-23', 'blablabla', 3000, 123456789);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (2, 4, '2020-07-24', 'blablablablablablablablablablablabla', 2000, 123456789);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (1, 1, '2020-06-25', 'blablablablablabla', 1000, 123456789);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (5, 2, '2020-06-25', 'blablablablablabla', 5000, 123456789);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (5, 3, '2020-06-25', 'blablablablablabla', 10000, 123456789);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (5, 4, '2020-06-25', 'bla', 15000, 123456789);

insert into prestamos (idCuenta, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, estado) values(1,20000, '2020/10/21', 10000, 3, 10000, 'pendiente');
insert into prestamos (idCuenta, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, estado) values(2, 40000, '2020/10/22', 20000, 6, 10000, 'pendiente');
insert into prestamos (idCuenta, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, estado) values(3, 60000, '2020/10/23', 30000, 18, 10000, 'pendiente');
insert into prestamos (idCuenta, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, estado) values(4, 80000, '2020/10/24', 40000, 12, 10000, 'pendiente');
insert into prestamos (idCuenta, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, estado) values(5, 100000, '2020/10/25', 50000, 24, 10000, 'pendiente');