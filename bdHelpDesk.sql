create table UsuariosHD(
uuid varchar2(50) primary key,
nombre varchar2(50) not null,
correo varchar2(50) not null,
contraseña varchar2(50) not null
);

select * from UsuariosHD