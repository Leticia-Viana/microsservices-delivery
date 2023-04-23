DROP TABLE IF EXISTS Products;

CREATE TABLE Products (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    nome VARCHAR(250) NOT NULL
);

CREATE TABLE Orders (
    orderId INT AUTO_INCREMENT  PRIMARY KEY,
    clientName VARCHAR(250) NOT NULL,
    deliveryAddress VARCHAR(250) NOT NULL,
    requestDate DATE
);

CREATE TABLE Product_Order (
  id INTEGER PRIMARY KEY,
  idOrder INTEGER REFERENCES Orders(orderId),
  idProd INTEGER REFERENCES Products(idProduct),
  quantity INT
);
