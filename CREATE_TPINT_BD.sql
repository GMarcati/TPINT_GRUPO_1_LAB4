drop database tpintegrador_bd;
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

-- Se insertan tipo de usuario--
INSERT tipoUsuarios(descripcion) VALUES ('Admin');
INSERT tipoUsuarios(descripcion) VALUES ('Cliente');

-- Se insertan tipo de movimientos--
INSERT tipoMovimientos(descripcion) VALUES ('Alta de cuenta');
INSERT tipoMovimientos(descripcion) VALUES ('Alta de un préstamo');
INSERT tipoMovimientos(descripcion) VALUES ('Pago de préstamo');
INSERT tipoMovimientos(descripcion) VALUES ('Transferencia');

-- Se insertan tipo de cuentas--
INSERT tipoCuentas(descripcion) VALUES ('Caja de ahorro');
INSERT tipoCuentas(descripcion) VALUES ('Cuenta corriente');

-- Se insertan los estados--
INSERT estados(descripcion) VALUES ('Activo');
INSERT estados(descripcion) VALUES ('Inactivo');
INSERT estados(descripcion) VALUES ('Pendiente');

-- Se insertan las nacionalides--
INSERT nacionalidades(descripcion) VALUES ('Argentina');
INSERT nacionalidades(descripcion) VALUES ('Chile');
INSERT nacionalidades(descripcion) VALUES ('Uruguay');
INSERT nacionalidades(descripcion) VALUES ('Peru');
INSERT nacionalidades(descripcion) VALUES ('Bolivia');
INSERT nacionalidades(descripcion) VALUES ('Brasil');
INSERT nacionalidades(descripcion) VALUES ('Colombia');
INSERT nacionalidades(descripcion) VALUES ('Venezuela');
INSERT nacionalidades(descripcion) VALUES ('Paraguay');
INSERT nacionalidades(descripcion) VALUES ('Ecuador');
INSERT nacionalidades(descripcion) VALUES ('Mexico');
INSERT nacionalidades(descripcion) VALUES ('Estados Unidos');
INSERT nacionalidades(descripcion) VALUES ('Nicaragua');
INSERT nacionalidades(descripcion) VALUES ('Guatemala');
INSERT nacionalidades(descripcion) VALUES ('Cuba');

-- Se insertan las provincias--
INSERT provincias(descripcion) VALUES ('Buenos Aires');
INSERT provincias(descripcion) VALUES ('Catamarca');
INSERT provincias(descripcion) VALUES ('EjemploProvinciaChile');
INSERT provincias(descripcion) VALUES ('EjemploProvinciaUruguay');
INSERT provincias(descripcion) VALUES ('EjemploProvinciaPeru');
INSERT provincias(descripcion) VALUES ('EjemploProvinciaBolivia');
INSERT provincias(descripcion) VALUES ('EjemploProvinciaBrasil');
INSERT provincias(descripcion) VALUES ('EjemploProvinciaColombia');
INSERT provincias(descripcion) VALUES ('EjemploProvinciaVenezuela');
INSERT provincias(descripcion) VALUES ('EjemploProvinciaParaguay');
INSERT provincias(descripcion) VALUES ('EjemploProvinciaEcuador');
INSERT provincias(descripcion) VALUES ('EjemploProvinciaMexico');
INSERT provincias(descripcion) VALUES ('EjemploProvinciaEEUU');
INSERT provincias(descripcion) VALUES ('EjemploProvinciaNicaragua');
INSERT provincias(descripcion) VALUES ('EjemploProvinciaGuatemala');
INSERT provincias(descripcion) VALUES ('EjemploProvinciaCuba');
-- Se insertan las localidades--
INSERT localidades(descripcion) VALUES ('San Fernando');
INSERT localidades(descripcion) VALUES ('Andalgalá');
INSERT localidades(descripcion) VALUES ('sarasa1');
INSERT localidades(descripcion) VALUES ('sarasa2');
INSERT localidades(descripcion) VALUES ('sarasa3');
INSERT localidades(descripcion) VALUES ('sarasa4');
INSERT localidades(descripcion) VALUES ('sarasa5');
INSERT localidades(descripcion) VALUES ('sarasa6');
INSERT localidades(descripcion) VALUES ('sarasa7');
INSERT localidades(descripcion) VALUES ('sarasa8');
INSERT localidades(descripcion) VALUES ('sarasa9');
INSERT localidades(descripcion) VALUES ('sarasa10');
INSERT localidades(descripcion) VALUES ('sarasa11');
INSERT localidades(descripcion) VALUES ('sarasa12');
INSERT localidades(descripcion) VALUES ('sarasa13');

-- Se insertan el usuario admin--
INSERT usuarios(usuario,contrasenia,idTipoUsuario,DNI,CUIL,nombre,apellido,sexo,fechaNacimiento,direccion,idLocalidad,idNacionalidad,idProvincia,mail,telefono,idEstado) 
VALUES ('admin','admin',1,35615294,20356152946,'admin','admin','admin','1997-02-20','admin 123',1,1,1,'admin@gmail.com',4883959844,1);

-- Se insertan usuarios de prueba--
INSERT usuarios(usuario,contrasenia,idTipoUsuario,DNI,CUIL,nombre,apellido,sexo,fechaNacimiento,direccion,idLocalidad,idNacionalidad,idProvincia,mail,telefono,idEstado) 
VALUES ('MFernandez','MFernandez',2,30115594,20301155949,'Maximiliano','Fernandez','Masculino','1980-12-10','Alvear 123',1,1,1,'mfernandez@gmail.com',4283959844,1);

INSERT usuarios(usuario,contrasenia,idTipoUsuario,DNI,CUIL,nombre,apellido,sexo,fechaNacimiento,direccion,idLocalidad,idNacionalidad,idProvincia,mail,telefono,idEstado) 
VALUES ('THerrera','THerrera',2,36115555,20361155551,'Tamara','Herrera','Femenino','1990-06-25','Peron 523',1,1,1,'therrera@gmail.com',4283567222,1);




