CREATE DATABASE tpintegrador_bd;
use tpintegrador_bd;

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
    fecha datetime NOT NULL,
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
    fechaCreacion datetime NOT NULL,
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






