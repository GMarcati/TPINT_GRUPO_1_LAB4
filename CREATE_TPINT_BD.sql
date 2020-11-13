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

CREATE TABLE estados(
	idEstado int NOT NULL auto_increment,
    descripcion varchar(50) NOT NULL,
    primary key (idEstado)
);

CREATE TABLE movimientos(
	idMovimiento bigint NOT NULL auto_increment,
    idTipoMovimiento int NOT NULL,
    fecha date NOT NULL,
    detalle varchar(500) NOT NULL,
    importe decimal NOT NULL,
    cuentaDestino bigint NOT NULL,
    primary key (idMovimiento),
    constraint foreign key (idTipoMovimiento) references tipoMovimientos (idTipoMovimiento)
);

CREATE TABLE cuentas(
	idCuenta bigint NOT NULL auto_increment,
    numeroCuenta bigint NOT NULL,
    idTipoCuenta int NOT NULL,
    fechaCreacion date NOT NULL,
    CBU bigint NOT NULL,
    saldo decimal NOT NULL,
    idEstado int NOT NULL,
    primary key (idCuenta),
    constraint foreign key (idTipoCuenta) references tipoCuentas (idTipoCuenta),
    constraint foreign key (idEstado) references estados (idEstado)
);

CREATE TABLE usuarios(
	idUsuario bigint NOT NULL auto_increment,
	usuario varchar(50) NOT NULL unique,
    contrasenia varchar(50) NOT NULL,
    idTipoUsuario int NOT NULL,
    DNI VARCHAR(8) NOT NULL,
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
    idEstado int NOT NULL,
    primary key (idUsuario),
    constraint foreign key (idLocalidad) references localidades (idLocalidad),
    constraint foreign key (idNacionalidad) references nacionalidades (idNacionalidad),
    constraint foreign key (idProvincia) references provincias (idProvincia),
    constraint foreign key (idEstado) references estados (idEstado),
    constraint foreign key (idTipoUsuario) references tipoUsuarios (idTipoUsuario)
);

CREATE TABLE prestamos(
	idPrestamo bigint NOT NULL auto_increment,
    importeAdevolver decimal NOT NULL,
    fecha date NOT NULL,
    montoSolicitado decimal NOT NULL,
    cantidadMeses bigint NOT NULL,
    valorCuota decimal NOT NULL,
    idEstado int NOT NULL,
    primary key (idPrestamo),
    constraint foreign key (idEstado) references estados (idEstado)
);

CREATE TABLE movimientos_x_cuenta(
	idMovimiento bigint NOT NULL,
    idCuenta bigint NOT NULL,
	primary key (idMovimiento,idCuenta),
    constraint foreign key (idMovimiento) references movimientos (idMovimiento),
    constraint foreign key (idCuenta) references cuentas (idCuenta)
);

CREATE TABLE cuentas_x_cliente(
	idCuenta bigint NOT NULL,
    idUsuario bigint NOT NULL,
	primary key (idCuenta,idUsuario),
    constraint foreign key (idCuenta) references cuentas (idCuenta),
    constraint foreign key (idUsuario) references usuarios (idUsuario)
);

CREATE TABLE prestamos_x_cliente(
	idPrestamo bigint NOT NULL,
    idUsuario bigint NOT NULL,
	primary key (idPrestamo,idUsuario),
    constraint foreign key (idPrestamo) references prestamos (idPrestamo),
    constraint foreign key (idUsuario) references usuarios (idUsuario)
);

CREATE TABLE prestamos_x_cuenta(
	idPrestamo bigint NOT NULL,
    idCuenta bigint NOT NULL,
	primary key (idPrestamo,idCuenta),
    constraint foreign key (idPrestamo) references prestamos (idPrestamo),
    constraint foreign key (idCuenta) references cuentas (idCuenta)
);

/* ------------------------------------------
             PRECARGA DE DATOS
---------------------------------------------*/

insert into tipoUsuarios (descripcion) values ('administrador');
insert into tipoUsuarios (descripcion) values ('usuario');

insert into tipoCuentas (descripcion) values ('Caja de ahorro');
insert into tipoCuentas (descripcion) values ('Cuenta corriente');

insert into tipoMovimientos(descripcion) values ('Alta cuenta');
insert into tipoMovimientos(descripcion) values ('Alta prestamo');
insert into tipoMovimientos(descripcion) values ('Pago prestamo');
insert into tipoMovimientos(descripcion) values ('Transferecnia');

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

insert into estados (descripcion) values ('Activo');
insert into estados (descripcion) values ('Inactivo');

insert into movimientos(idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (1, '2020/10/21', 'pago atrasado cuota', 5000, 123456789);
insert into movimientos(idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (2, '2020/09/22', 'pago atrasado cuota', 4000, 123456789);
insert into movimientos(idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (3, '2020/08/23', 'pago atrasado cuota', 3000, 123456789);
insert into movimientos(idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (4, '2020/07/24', 'pago atrasado cuota', 2000, 123456789);
insert into movimientos(idTipoMovimiento, fecha, detalle, importe, cuentaDestino) values (1, '2020/06/25', 'pago atrasado cuota', 1000, 123456789);

insert into cuentas (numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado) values (11111111, 1, '2020/10/21', 123456789, 10000, 1);
insert into cuentas (numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado) values (22222222, 2, '2020/10/21', 987654321, 20000, 1);
insert into cuentas (numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado) values (33333333, 1, '2020/10/21', 123456789, 30000, 1);
insert into cuentas (numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado) values (444444444, 2, '2020/10/21', 987654321, 40000, 1);
insert into cuentas (numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado) values (55555555, 1, '2020/10/21', 123456789, 50000, 1);

insert into cuentas (numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado) values (11111111, 1, '2020/10/21', 11111111, 10000 ,1);
insert into cuentas (numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado) values (22222222, 1, '2020/10/22', 22222222, 10000 ,1);
insert into cuentas (numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado) values (33333333, 1, '2020/10/23', 33333333, 10000 ,1);
insert into cuentas (numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado) values (44444444, 1, '2020/10/24', 44444444, 10000 ,1);
insert into cuentas (numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado) values (55555555, 1, '2020/10/25', 55555555, 10000 ,1);

insert into usuarios (usuario, contrasenia, idTipoUsuario, DNI, CUIL, mail, idEstado) 
values('admin', 'admin', 1, '111', '1111', 'mailadm@fake', 1);
insert into usuarios (usuario, contrasenia, idTipoUsuario, DNI, CUIL, nombre, apellido, sexo, fechaNacimiento, direccion, idLocalidad, idNacionalidad, idProvincia, mail, telefono, idEstado) 
values ('usu', '123', 2, '222', '2222', 'nombreUsuario', 'apellidoUsuario', 'masculino', null, 'calleFalsa', 1, 1, 1, 'mailfake@gm', '44556677',1);

insert into prestamos (importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstado) values(20000, '2020/10/21', 10000, 2, 10000,1);
insert into prestamos (importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstado) values(40000, '2020/10/22', 20000, 3, 10000,1);
insert into prestamos (importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstado) values(60000, '2020/10/23', 30000, 4, 10000,1);
insert into prestamos (importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstado) values(80000, '2020/10/24', 40000, 5, 10000,1);
insert into prestamos (importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstado) values(100000, '2020/10/25', 50000, 6, 10000,1);

