# Music Manager API

## Visão Geral
A **Music Manager API** é uma API REST desenvolvida em **Java com Spring Boot**, voltada para o gerenciamento de **Artistas** e **Álbuns**.  
O projeto foi construído com foco em **qualidade de código**, **segurança**, **organização arquitetural** e **boas práticas corporativas**, conforme exigido no edital do processo seletivo.

A aplicação permite o cadastro, edição, listagem e remoção de artistas e seus respectivos álbuns, incluindo **upload de imagens de capa** para armazenamento em Object Storage (**MinIO**) e a geração de **URLs assinadas (Presigned URLs)** para acesso seguro às imagens.

## Arquitetura Adotada
O projeto segue uma **Arquitetura em Camadas (Layered Architecture)**, promovendo separação de responsabilidades, manutenibilidade e facilidade de testes.

### Camadas
- **Controller**  
  Responsável por expor os endpoints REST, receber requisições HTTP e validar os dados de entrada e saída por meio de DTOs.

- **Service**  
  Contém a lógica de negócio da aplicação, regras, validações e orquestração das operações.

- **Repository**  
  Camada de persistência de dados, utilizando **Spring Data JPA** para abstração do acesso ao banco.

- **Entity (Domínio)**  
  Representa o modelo de dados e o mapeamento objeto-relacional (ORM) das tabelas do banco.

### Padrões e Princípios Utilizados
- DTO (Data Transfer Object)
- Repository Pattern
- Dependency Injection (Spring IoC)
- Princípios SOLID
- Separação de responsabilidades

## Tecnologias Utilizadas
- Java 17 (LTS)
- Spring Boot 3.2.2
- PostgreSQL
- Flyway
- MinIO
- Spring Security + JWT
- Bucket4j
- Docker & Docker Compose
- Swagger / OpenAPI
- Lombok
- JUnit 5 & Mockito

## Pré-requisitos
- Docker e Docker Compose instalados  
- (Opcional) Java 17+ e Maven

## Como Executar o Projeto

### Com Docker
```bash
docker compose up --build
```

API disponível em:
```
http://localhost:8080
```

### Execução Local
```bash
docker compose up postgres minio createbuckets -d
./mvnw spring-boot:run
```

## Documentação
Swagger disponível em:
```
http://localhost:8080/swagger-ui.html
```

## Autenticação
Usuário padrão:
- username: admin
- password: admin

## Upload de Imagens
As imagens são armazenadas no MinIO e acessadas via URLs assinadas com validade de 30 minutos.

## Considerações Finais
Projeto desenvolvido com foco em clareza, organização e aderência total ao edital.
