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
    saldo decimal(10,2) NOT NULL,
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
    importe decimal(10,2) NOT NULL,
    cuentaDestino bigint NULL,
    primary key (idMovimiento),
    constraint foreign key (idTipoMovimiento) references tipoMovimientos (idTipoMovimiento),
    constraint foreign key (idCuenta) references cuentas (idCuenta) -- agregada ultima actualizacion
);

CREATE TABLE estadosPrestamos(
	idEstadoPrestamo int auto_increment not null,
    descripcion varchar(50) NOT NULL, -- estados= 1-pendiente, 2-aprobado, 3-rechazado
    primary key (idEstadoPrestamo)
);

CREATE TABLE prestamos(
	idPrestamo bigint NOT NULL auto_increment,
    idCuenta bigint not null, -- agregada ultima actualizacion
    idUsuario bigint not null, -- para el listado de prestamos
    importeAdevolver decimal(10,2) NOT NULL,
    fecha date NOT NULL,
    montoSolicitado decimal(10,2) NOT NULL,
    cantidadMeses int NOT NULL,
    valorCuota decimal(10,2) NOT NULL,
    idEstadoPrestamo int not null,
    idEstado bit NOT NULL, 
    primary key (idPrestamo),
    constraint foreign key (idCuenta) references cuentas (idCuenta), -- agregada ultima actualizacion
	constraint foreign key (idUsuario) references usuarios (idUsuario),
    constraint foreign key (idEstadoPrestamo) references estadosPrestamos(idEstadoPrestamo)
);

CREATE TABLE cuotasPrestamo(
    cuota bigint not null,
	idPrestamo bigint not null,
    fechaPago date,
    idEstado bit not null,
    primary key(cuota,idPrestamo),
    constraint foreign key (idPrestamo) references prestamos (idPrestamo)
);

/* ------------------------------------------
             PRECARGA DE DATOS
---------------------------------------------*/

insert into tipoUsuarios (descripcion) values ('Administrador');
insert into tipoUsuarios (descripcion) values ('Usuario');

insert into tipoCuentas (descripcion) values ('Caja de ahorro');
insert into tipoCuentas (descripcion) values ('Cuenta corriente');

insert into estadosPrestamos (descripcion) values ('Pendiente');
insert into estadosPrestamos (descripcion) values ('Aprobado');
insert into estadosPrestamos (descripcion) values ('Rechazado');

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
values ('usu', '123', 2, '22222222', '22222222222', 'nombreUsuario', 'apellidoUsuario', 'masculino', '1980-12-10', 'calleFalsa', 1, 1, 1, 'mailfake@gmail.com', '44556677',1);

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

insert into cuentas (idUsuario, numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado) values (2, 11111111, 1, '2020/10/21', 11111111111, 10000 ,1);
insert into cuentas (idUsuario, numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado) values (2, 22222222, 1, '2020/10/22', 22222222222, 10000 ,1);
insert into cuentas (idUsuario, numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado) values (3, 33333333, 1, '2020/10/23', 33333333333, 10000 ,1);
insert into cuentas (idUsuario, numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado) values (4, 44444444, 1, '2020/10/24', 44444444444, 10000 ,1);
insert into cuentas (idUsuario, numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado) values (5, 55555555, 1, '2020/10/25', 55555555555, 10000 ,1);


insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (1, 1, '2020-09-21', 'Apertura de cuenta', 10000, 11111111);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (2, 1, '2020-10-22', 'Apertura de cuenta', 10000, 22222222);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (3, 1, '2020-10-23', 'Apertura de cuenta', 10000, 33333333);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (4, 1, '2020-10-24', 'Apertura de cuenta', 10000, 44444444);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (5, 1, '2020-10-25', 'Apertura de cuenta', 10000, 55555555);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (2, 3, '2020-08-23', 'blablabla', 3000, 123456789);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (2, 4, '2020-07-24', 'blablablablablablablablablablablabla', 2000, 123456789);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (1, 4, '2020-06-25', 'blablablablablabla', 1000, 123456789);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (5, 2, '2020-06-25', 'blablablablablabla', 5000, 123456789);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (5, 3, '2020-06-25', 'blablablablablabla', 10000, 123456789);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (1, 4, '2020-06-10', 'blasadasd', 11000, 55555555);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (1, 4, '2020-06-23', 'bladsadasd', 10000, 44444444);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (1, 4, '2020-06-29', 'blasdad', 18000, 22222222);

/*Movimientos del alta de prestamos*/
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (1, 2, '2020/10/21', 'prestamo', 10000, 11111111);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (2, 2, '2020/10/22', 'prestamo', 20000, 22222222);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (3, 2, '2020/10/23', 'prestamo', 30000, 33333333);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (4, 2, '2020/10/24', 'prestamo', 40000, 44444444);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (5, 2, '2020/10/25', 'prestamo', 50000, 55555555);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (1, 2, '2020/10/25', 'prestamo', 50000, 11111111);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (5, 2, '2020/10/25', 'prestamo', 50000, 55555555);
insert into movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (1, 2, '2020/10/25', 'prestamo', 9500, 11111111);

insert into prestamos (idCuenta, idUsuario, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstadoPrestamo, idEstado) values(1,2,20000, '2020/10/21', 10000, 3, 10000,2,1);
insert into prestamos (idCuenta, idUsuario, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstadoPrestamo, idEstado) values(2,2, 40000, '2020/10/22', 20000, 6, 10000,2,1);
insert into prestamos (idCuenta, idUsuario, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstadoPrestamo, idEstado) values(3,2, 60000, '2020/10/23', 30000, 6, 10000,2,1);
insert into prestamos (idCuenta, idUsuario, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstadoPrestamo, idEstado) values(4,2, 80000, '2020/10/24', 40000, 3, 10000,2,1);
insert into prestamos (idCuenta, idUsuario, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstadoPrestamo, idEstado) values(5,5, 100000, '2020/10/25', 50000, 3, 10000,2,1);
insert into prestamos (idCuenta, idUsuario, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstadoPrestamo, idEstado) values(1,2, 150000, '2020/10/25', 50000, 3, 10000,2,1);
insert into prestamos (idCuenta, idUsuario, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstadoPrestamo, idEstado) values(5,5, 100000, '2020/10/25', 50000, 12, 10000,2,1);
insert into prestamos (idCuenta, idUsuario, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstadoPrestamo, idEstado) values(1,2, 10000, '2020/10/25', 9500, 3, 3333.33,2,1);

insert into prestamos (idCuenta, idUsuario, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstadoPrestamo, idEstado) values(4,4, 10000, '2020/10/25', 9500, 3, 3333.33,1,1);
insert into prestamos (idCuenta, idUsuario, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstadoPrestamo, idEstado) values(4,4, 10000, '2020/10/25', 9500, 3, 3333.33,1,1);
insert into prestamos (idCuenta, idUsuario, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstadoPrestamo, idEstado) values(5,5, 10000, '2020/10/25', 9500, 3, 3333.33,1,1);
insert into prestamos (idCuenta, idUsuario, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstadoPrestamo, idEstado) values(5,5, 10000, '2020/10/25', 9500, 3, 3333.33,1,1);
insert into prestamos (idCuenta, idUsuario, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstadoPrestamo, idEstado) values(3,3, 10000, '2020/10/25', 9500, 3, 3333.33,3,1);
insert into prestamos (idCuenta, idUsuario, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstadoPrestamo, idEstado) values(4,4, 10000, '2020/10/25', 9500, 3, 3333.33,3,1);
insert into prestamos (idCuenta, idUsuario, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstadoPrestamo, idEstado) values(5,5, 10000, '2020/10/25', 9500, 3, 3333.33,3,1);

/*Se generan las cuotas de los prestamos*/
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (1,1,'2020/11/21',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (2,1,'2020/12/21',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (3,1,'2021/01/21',1);

Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (1,2,'2020/11/22',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (2,2,'2020/12/22',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (3,2,'2021/01/22',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (4,2,'2021/02/22',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (5,2,'2021/03/22',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (6,2,'2021/04/22',1);

Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (1,3,'2020/11/23',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (2,3,'2020/12/23',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (3,3,'2021/01/23',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (4,3,'2021/02/23',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (5,3,'2021/03/23',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (6,3,'2021/04/23',1);

Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (1,4,'2020/11/24',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (2,4,'2020/12/24',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (3,4,'2021/01/24',1);

Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (1,5,'2020/11/25',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (2,5,'2020/12/25',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (3,5,'2021/01/25',1);

Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (1,6,'2020/11/25',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (2,6,'2020/12/25',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (3,6,'2021/01/25',1);

Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (1,7,'2020/11/25',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (2,7,'2020/12/25',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (3,7,'2021/01/25',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (4,7,'2021/02/25',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (5,7,'2021/03/25',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (6,7,'2021/04/25',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (7,7,'2021/05/25',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (8,7,'2021/06/25',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (9,7,'2021/07/25',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (10,7,'2021/08/25',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (11,7,'2021/09/25',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (12,7,'2021/10/25',1);

Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (1,8,'2020/11/25',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (2,8,'2020/12/25',1);
Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values (3,8,'2021/01/25',1);

select p.*, c.numeroCuenta from prestamos as p
inner join cuentas as c on c.idCuenta= p.idCuenta;

select * from cuentas;