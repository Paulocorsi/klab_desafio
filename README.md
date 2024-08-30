# Controle de Produtos/Pedidos

Este projeto é uma aplicação Spring Boot para gerenciar produtos e pedidos. Foi desenvolvido como parte do KLAB desafio desenvolvedor pleno backend.

## Funcionalidades

- **Departamentos**
    - Listagem de produtos por departamento.
    - Filtragem por código de departamento.
- **Pedidos**
    - Inserção de novos pedidos.
    - Atualização de pedidos existentes (inclusão/removal de produtos, alteração de quantidade e valores).
    - Exclusão de pedidos.
    - Consulta de pedidos por ID.
    - Listagem de pedidos por intervalo de datas.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker & Docker Compose
- Swagger
- Maven

## Configuração do Ambiente

### Pré-requisitos

- Docker e Docker Compose instalados na sua máquina.

### Executando a aplicação

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/seu-usuario/controle-produtos-pedidos.git
   cd controle-produtos-pedidos


### 2. **Instruções para Rodar o Projeto**

1. **Garanta que você clonou o repositório e está no diretório do projeto.**
2. **Construa e inicie os containers com Docker Compose** usando o comando:

   ```bash
   docker-compose up --build


## Documentação da API

A documentação da API está disponível via Swagger.

- **Swagger UI:** `http://localhost:8080/swagger-ui/`

