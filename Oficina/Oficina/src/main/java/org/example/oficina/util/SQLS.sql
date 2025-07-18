CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    documento VARCHAR(20) NOT NULL UNIQUE,
    pessoa_juridica BOOLEAN NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100),
    endereco TEXT
);

CREATE TABLE veiculo (
    id SERIAL PRIMARY KEY,
    placa VARCHAR(10) NOT NULL UNIQUE,
    marca VARCHAR(50),
    modelo VARCHAR(50),
    km VARCHAR(20),
    cor VARCHAR(30),
    ano_fabricacao INT,
    tipo_combustivel VARCHAR(30), -- pode ser uma FK para uma tabela tipo_combustivel se desejar
    chassi VARCHAR(30),
    proprietario_id INT REFERENCES cliente(id)
);

CREATE TABLE cargo (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    descricao TEXT,
    salario NUMERIC(10,2)
);

CREATE TABLE funcionario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    cargo_id INT NOT NULL REFERENCES cargo(id)
);

CREATE TABLE ordem_servico (
    id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL REFERENCES cliente(id),
    veiculo_id INT NOT NULL REFERENCES veiculo(id),
    valor_total NUMERIC(10,2),
    entrada DATE NOT NULL,
    entrega DATE NOT NULL,
    status_os VARCHAR(30) NOT NULL, -- pode ser enum customizado
    observacoes TEXT
);

CREATE TABLE produto_utilizado (
    id SERIAL PRIMARY KEY,
    ordem_servico_id INT NOT NULL REFERENCES ordem_servico(id),
    categoria VARCHAR(50), -- pode virar tabela normalizada também
    marca VARCHAR(50),
    descricao TEXT,
    custo_unitario NUMERIC(10,2),
    preco_venda NUMERIC(10,2),
    quantidade INT NOT NULL,
    valor_total NUMERIC(10,2)
);

CREATE TABLE servico (
    id SERIAL PRIMARY KEY,
    descricao TEXT NOT NULL,
    valor NUMERIC(10,2) NOT NULL,
    mecanico_id INT NOT NULL REFERENCES funcionario(id),
    concluido BOOLEAN DEFAULT FALSE,
    observacao TEXT,
    ordem_servico_id INT NOT NULL REFERENCES ordem_servico(id)
);
