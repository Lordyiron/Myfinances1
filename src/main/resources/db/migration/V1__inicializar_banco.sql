CREATE SEQUENCE IF NOT EXISTS banco_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS cartao_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS despesa_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS meta_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS investimento_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS aplicacao_investimento_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS receita_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS banco
(
    id     BIGINT  NOT NULL PRIMARY KEY DEFAULT nextval('banco_seq'),
    nome   VARCHAR NOT NULL,
    codigo VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS cartao
(
    id                    BIGINT  NOT NULL PRIMARY KEY DEFAULT nextval('cartao_seq'),
    banco_emissor_id      BIGINT REFERENCES banco (id),
    nome                  VARCHAR NOT NULL,
    tipo_pagamento_cartao VARCHAR NOT NULL,
    limite                DECIMAL,
    bandeira              VARCHAR NOT NULL,
    numero                VARCHAR NOT NULL,
    dia_vencimento_fatura INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS despesa
(
    id              BIGINT  NOT NULL PRIMARY KEY DEFAULT nextval('despesa_seq'),
    cartao_usado_id BIGINT REFERENCES cartao (id),
    descricao       VARCHAR NOT NULL,
    valor           DECIMAL NOT NULL,
    parcelas        INTEGER NOT NULL             DEFAULT 1,
    vencimento      DATE    NOT NULL
);

CREATE TABLE IF NOT EXISTS meta
(
    id              BIGINT  NOT NULL PRIMARY KEY DEFAULT nextval('meta_seq'),
    nome            VARCHAR NOT NULL,
    descricao       VARCHAR NOT NULL,
    valor_objetivo  DECIMAL,
    valor_alcancado DECIMAL

);

CREATE TABLE IF NOT EXISTS investimento
(
    id              BIGINT  NOT NULL PRIMARY KEY DEFAULT nextval('investimento_seq'),
    nome            VARCHAR NOT NULL,
    total_investido DECIMAL NOT NULL             DEFAULT 0,
    data_criacao    DATE    NOT NULL             DEFAULT current_timestamp

);

CREATE TABLE IF NOT EXISTS aplicacao_investimento
(
    id              BIGINT  NOT NULL PRIMARY KEY DEFAULT nextval('aplicacao_investimento_seq'),
    investimento_id BIGINT REFERENCES investimento (id),
    valor_aplicado  DECIMAL NOT NULL,
    data_aplicacao  DATE    NOT NULL             DEFAULT current_timestamp
);

CREATE TABLE IF NOT EXISTS receita
(
    id            BIGINT  NOT NULL PRIMARY KEY DEFAULT nextval('receita_seq'),
    nome          VARCHAR NOT NULL,
    valor_receita DECIMAL NOT NULL             DEFAULT 0,
    data_receita  DATE    NOT NULL             DEFAULT current_timestamp
);
