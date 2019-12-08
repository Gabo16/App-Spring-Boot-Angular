insert into clientes (nombre, apellido , email, created_at, foto, updated_at) values ('Alejandra', 'Hernandez', 'alejandra@qwe.com', NOW(), 'noimage.png', NOW());
insert into clientes (nombre, apellido , email, created_at, foto, updated_at) values ('Eduardo', 'Quinto', 'eduardo@qwe.com', NOW(), 'noimage.png', NOW());
insert into clientes (nombre, apellido , email, created_at, foto, updated_at) values ('Gabriela', 'Ramirez', 'gabriela@qwe.com', NOW(), 'noimage.png', NOW());
insert into clientes (nombre, apellido , email, created_at, foto, updated_at) values ('Hector', 'Lopez', 'hecor@qwe.com', NOW(), 'noimage.png', NOW());
insert into clientes (nombre, apellido , email, created_at, foto, updated_at) values ('Sandra', 'Lopez', 'sandra@qwe.com', NOW(), 'noimage.png', NOW());
insert into clientes (nombre, apellido , email, created_at, foto, updated_at) values ('Alonso', 'Martinez', 'alonso@qwe.com', NOW(), 'noimage.png', NOW());
insert into clientes (nombre, apellido , email, created_at, foto, updated_at) values ('Danae', 'Quinto', 'danae@qwe.com', NOW(), 'noimage.png', NOW());
insert into clientes (nombre, apellido , email, created_at, foto, updated_at) values ('Gabriel', 'Gutierrez', 'gabriel@qwe.com', NOW(), 'noimage.png', NOW());
insert into clientes (nombre, apellido , email, created_at, foto, updated_at) values ('Amaury', 'Ramirez', 'amaury@qwe.com', NOW(), 'noimage.png', NOW());
insert into clientes (nombre, apellido , email, created_at, foto, updated_at) values ('Jessica', 'Alcantara', 'jessica@qwe.com', NOW(), 'noimage.png', NOW());
insert into clientes (nombre, apellido , email, created_at, foto, updated_at) values ('Monserrat', 'Martinez', 'monserrat@qwe.com', NOW(), 'noimage.png', NOW());
insert into clientes (nombre, apellido , email, created_at, foto, updated_at) values ('Esdras', 'Rico', 'esdras@qwe.com', NOW(), 'noimage.png', NOW());
insert into clientes (nombre, apellido , email, created_at, foto, updated_at) values ('Abril', 'Landin', 'abril@qwe.com', NOW(), 'noimage.png', NOW());
insert into clientes (nombre, apellido , email, created_at, foto, updated_at) values ('Julio', 'Melchor', 'julio@qwe.com', NOW(), 'noimage.png', NOW());

INSERT INTO usuarios (username, password, enabled, nombre, apellido, email, created_at, updated_at) VALUES ('user', '$2a$10$BbujXvCJjvoNmiJzUOAvK.OCjodPumhX3zlf9Wckhs99DHfLp1KaG', 1, 'Raul', 'Salazar', 'raul@qwe.com', NOW(), NOW());
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email, created_at, updated_at) VALUES ('admin', '$2a$10$p9lu0mFCiKoCzM2mJISeheJwtPVrGhNO9dmCVb33deC9pMj1gYdrq', 1, 'Oscar', 'Perez', 'orcar@qwe.com', NOW(), NOW());

INSERT INTO roles (nombre, created_at, updated_at) VALUES ('ROLE_USER', NOW(), NOW());
INSERT INTO roles (nombre, created_at, updated_at) VALUES ('ROLE_ADMIN', NOW(), NOW());

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);

insert into productos(nombre, precio, created_at, updated_at) values ('Samsung Galaxy M30 64gb', 5799, NOW(), NOW());
insert into productos(nombre, precio, created_at, updated_at) values ('Galaxy Note 10 256gb', 20155, NOW(), NOW());
insert into productos(nombre, precio, created_at, updated_at) values ('Celular Samsung Galaxy S9 64gb', 18200, NOW(), NOW());
insert into productos(nombre, precio, created_at, updated_at) values ('Xiaomi Mi 9T Pro 128gb', 9175, NOW(), NOW());
insert into productos(nombre, precio, created_at, updated_at) values ('Samsung Galaxy S10+ Dual SIM 128 GB', 16700, NOW(), NOW());
insert into productos(nombre, precio, created_at, updated_at) values ('Huawei P30 lite Dual SIM 128GB', 6299, NOW(), NOW());
insert into productos(nombre, precio, created_at, updated_at) values ('Motorola G7 Power Dual SIM 64 GB', 4345, NOW(), NOW());
insert into productos(nombre, precio, created_at, updated_at) values ('Apple iPhone 8 64 GB Gris espacial', 8999, NOW(), NOW());
insert into productos(nombre, precio, created_at, updated_at) values ('Motorola One Vision Dual SIM 128 GB Azul', 6918, NOW(), NOW());
insert into productos(nombre, precio, created_at, updated_at) values ('Samsung Galaxy J8 32 GB Dorado', 7558, NOW(), NOW());
insert into productos(nombre, precio, created_at, updated_at) values ('Samsung Galaxy Note 10+ Plus 256gb', 27999, NOW(), NOW());
insert into productos(nombre, precio, created_at, updated_at) values ('Huawei Y7 2019 32 GB Azul', 3500, NOW(), NOW());
insert into productos(nombre, precio, created_at, updated_at) values ('Huawei Mate 20 lite Dual SIM 64 GB', 8999, NOW(), NOW());

insert into facturas(descripcion, observacion, cliente_id, created_at, updated_at) values ('descripción de la factura', null, 1, NOW(), NOW());
insert into facturas_items(cantidad, factura_id, producto_id, created_at, updated_at) values (2, 1, 1, NOW(), NOW());
insert into facturas_items(cantidad, factura_id, producto_id, created_at, updated_at) values (1, 1, 3, NOW(), NOW());
insert into facturas_items(cantidad, factura_id, producto_id, created_at, updated_at) values (2, 1, 4, NOW(), NOW());
insert into facturas_items(cantidad, factura_id, producto_id, created_at, updated_at) values (1, 1, 5, NOW(), NOW());

insert into facturas(descripcion, observacion, cliente_id, created_at, updated_at) values ('***', 'alguna nota importante', 1, NOW(), NOW());
insert into facturas_items(cantidad, factura_id, producto_id, created_at, updated_at) values (2, 2, 6, NOW(), NOW());