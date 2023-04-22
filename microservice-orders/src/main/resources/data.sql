insert into Products (id, nome) values (1, 'bolacha');
insert into Products (id, nome) values (2, 'biscoito');

insert into Orders(requestDate, clientName, deliveryAddress, orderId)
values ('2023-04-22','Diego', 'Avenida Paulista', 1), ('2023-04-19','Luiz', 'Avenida Paulista', 2);

INSERT INTO Product_Order (id, order, product, quantity)
VALUES(1, 1, 'Produto 1', 'bolacha', 5),
 (2, 2, 'Produto 2', 'biscoito', 7);