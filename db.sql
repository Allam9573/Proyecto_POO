create database if not exists db_pos;

use db_pos;

create table tbl_clientes(
		cliente_id int auto_increment not null,
		nombre varchar(50) not null,
        apellido varchar(50) not null,
		correo varchar(50) not null,
        telefono varchar(50) not null,
        direccion varchar(100) not null,
        primary key(cliente_id)
);

create table tbl_proveedores(
	proveedor_id int primary key auto_increment not null,
    nombre varchar(100) not null unique,
    direccion varchar(100) not null,
    telefono varchar(15) not null,
    correo varchar(50) not null
);

create table tbl_productos(
		producto_id int auto_increment not null,
        nombre varchar(50) not null,
        cantidad int not null,
        descripcion varchar(100) not null,
        precio int not null,
        primary key (producto_id)
);
