# Coupon API

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-green)
![Maven](https://img.shields.io/badge/Maven-3.x-blue)
![Build](https://img.shields.io/github/actions/workflow/status/fernandavsp/coupon-api/maven.yml?branch=main)
![License](https://img.shields.io/badge/License-MIT-yellow)

API REST para gerenciamento de cupons, desenvolvida em **Spring Boot 3** com **Java 17**, utilizando **H2** como banco em memória. A aplicação implementa regras de negócio de criação e deleção de cupons, incluindo validação de código, valor mínimo de desconto e data de expiração.

---

## Funcionalidades

- Criar cupons com validações:
    - Código alfanumérico de 6 caracteres (removendo caracteres especiais automaticamente)
    - Descrição obrigatória
    - Valor de desconto mínimo de 0.5
    - Data de expiração futura
    - Publicado ou não
- Deletar cupons (**soft delete**)
- Documentação **Swagger / OpenAPI**
- Testes unitários e de integração com H2

---

## Tecnologias

- Java 17
- Spring Boot 3.x
- Spring Web MVC
- Spring Data JPA
- H2 Database
- Springdoc OpenAPI 2.6.6 (Swagger UI)
- Maven
- JUnit + Mockito
- Docker & Docker Compose

---

## Rodando a aplicação

### 1️⃣ Localmente com Maven

Na raiz do projeto:

```bash
./mvnw clean spring-boot:run
```

A aplicação será iniciada em:
http://localhost:8080

### 2️⃣ Com Docker Compose

Na raiz do projeto:
docker-compose up --build

Porta exposta: 8080

Swagger UI: http://localhost:8080/swagger-ui/index.html

## Testes

Unitários: JUnit + Mockito

Integração: MockMvc + H2 Database


### Executar todos os testes:

```bash
./mvnw test

```

## API Endpoints

### Criar cupom
#### POST /coupons

### Exemplo de request JSON

```json
{
  "code": "ABC-12@3",
  "description": "Teste cupom",
  "discountValue": 10.0,
  "expirationDate": "2030-12-31",
  "published": true
}
```

### Deletar cupom
#### DELETE /coupons/{id}

Soft delete: marca o cupom como deletado sem removê-lo do banco.

Retorna 409 Conflict se já estiver deletado.


### Exemplo de resposta ao tentar deletar um cupom já deletado:

```json
{
"timestamp": "2026-01-03T12:00:00",
"status": 409,
"error": "Conflict",
"message": "Coupon already deleted",
"path": "/coupons/1"
}
```

## Swagger
Documentação interativa:

http://localhost:8080/swagger-ui/index.html

### JSON da API:
http://localhost:8080/v3/api-docs

## Banco de dados

H2 Database em memória.

Console H2:

http://localhost:8080/h2-console

