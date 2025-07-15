CREATE TABLE cliente (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    documento VARCHAR(20) NOT NULL UNIQUE,
    pessoa_juridica BOOLEAN NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100),
    endereco TEXT
);

CREATE TABLE veiculo (
    id INT PRIMARY KEY AUTO_INCREMENT,
    placa VARCHAR(10) NOT NULL UNIQUE,
    marca VARCHAR(50),
    modelo VARCHAR(50),
    km VARCHAR(20),
    cor VARCHAR(30),
    ano_fabricacao INT,
    tipo_combustivel VARCHAR(20),
    chassi VARCHAR(30),
    proprietario_id INT,
    FOREIGN KEY (proprietario_id) REFERENCES cliente(id)
);

CREATE TABLE cargo (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    descricao TEXT,
    salario DECIMAL(10,2)
);

CREATE TABLE funcionario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    cargo_id INT NOT NULL,
    FOREIGN KEY (cargo_id) REFERENCES cargo(id)
);

CREATE TABLE ordem_servico (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cliente_id INT NOT NULL,
    veiculo_id INT NOT NULL,
    valor_total DECIMAL(10,2),
    entrada DATE NOT NULL,
    entrega DATE NOT NULL,
    status_os VARCHAR(30) NOT NULL,
    observacoes TEXT,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    FOREIGN KEY (veiculo_id) REFERENCES veiculo(id)
);

CREATE TABLE produto_utilizado (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ordem_servico_id INT NOT NULL,
    categoria VARCHAR(50),
    marca VARCHAR(50),
    descricao TEXT,
    custo_unitario DECIMAL(10,2),
    preco_venda DECIMAL(10,2),
    quantidade INT NOT NULL,
    valor_total DECIMAL(10,2),
    FOREIGN KEY (ordem_servico_id) REFERENCES ordem_servico(id)
);

CREATE TABLE servico (
    id INT PRIMARY KEY AUTO_INCREMENT,
    descricao TEXT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    mecanico_id INT NOT NULL,
    concluido BOOLEAN DEFAULT FALSE,
    observacao TEXT,
    ordem_servico_id INT NOT NULL,
    FOREIGN KEY (mecanico_id) REFERENCES funcionario(id),
    FOREIGN KEY (ordem_servico_id) REFERENCES ordem_servico(id)
);
