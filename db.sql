create database if not exists db_tienda;

use db_tienda;

create table cliente(
		cliente_id int primary key not null,
		nombre varchar(50) not null,
        apellido varchar(50) not null,
        contrasenia varchar(50) not null,
        correo varchar(50) not null
);
insert into cliente values(3,"Allam","Argueta","admin","allam@gmail.com","allam45");
create table producto(
		producto_id int primary key not null,
        nombre varchar(50) not null,
        cantidad int not null,
        descripcion varchar(100) not null,
        precio int not null
);
create table pedido(
		pedido_id int primary key not null,
        fecha_pedido date not null,
        cliente_id int not null,
        producto_id int not null,
        foreign key (cliente_id) references cliente(cliente_id),
        foreign key (producto_id) references producto(producto_id)
);
use db_tienda;
insert into cliente values
(11,"Allam","Argueta","allam45","allam@gmail.com","allam9573");
select * from cliente;
