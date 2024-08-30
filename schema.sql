CREATE TABLE departamentos (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL
);

CREATE TABLE produtos (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    preco     NUMERIC(10,2) NOT NULL,
    departamento_id INT,
    constraint fk_depto foreign key (departamento_id) references departamentos (id)
);

CREATE TABLE pedidos (
    id SERIAL PRIMARY KEY,
    data date NOT NULL
);

CREATE TABLE produto_pedido (
    produto_id INT NOT NULL,
    pedido_id INT NOT NULL,
    quantidade NUMERIC(19, 2),
    valor_venda NUMERIC(19, 2),
    PRIMARY KEY (produto_id, pedido_id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id),
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id)
);

INSERT INTO departamentos (descricao) VALUES ('Eletrônicos');
INSERT INTO departamentos (descricao) VALUES ('Eletrodomésticos');
INSERT INTO departamentos (descricao) VALUES ('Móveis');

INSERT INTO public.produtos (descricao, preco, departamento_id) VALUES ('Smartphone', 1200.00, 1);
INSERT INTO produtos (descricao, preco, departamento_id) VALUES ('Geladeira', 3500.00, 2);
INSERT INTO produtos (descricao, preco, departamento_id) VALUES ('Sofá', 2000.00, 3);
INSERT INTO produtos (descricao, preco, departamento_id) VALUES ('Notebook', 4500.00, 1);
INSERT INTO produtos (descricao, preco, departamento_id) VALUES ('Fogão', 800.00, 2);
INSERT INTO produtos (descricao, preco, departamento_id) VALUES ('Cama', 1500.00, 3);

INSERT INTO pedidos (data) VALUES ('2024-08-01');
INSERT INTO pedidos (data) VALUES ('2024-08-15');
INSERT INTO pedidos (data) VALUES ('2024-08-20');

INSERT INTO produto_pedido (produto_id, pedido_id, quantidade, valor_venda) VALUES (1, 1, 2, 2400.00);
INSERT INTO produto_pedido (produto_id, pedido_id, quantidade, valor_venda) VALUES (2, 1, 1, 3500.00);
INSERT INTO produto_pedido (produto_id, pedido_id, quantidade, valor_venda) VALUES (3, 2, 1, 2000.00);
INSERT INTO produto_pedido (produto_id, pedido_id, quantidade, valor_venda) VALUES (4, 3, 1, 4500.00);
INSERT INTO produto_pedido (produto_id, pedido_id, quantidade, valor_venda) VALUES (5, 3, 2, 1600.00);
