# 🐧 Processo Seletivo ASCII 25.2 - Desafio Backend 🐧

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white)


Projeto de desenvolvimento de uma API RESTful como parte da etapa 2 do Processo Seletivo da Empresa Junior de Tecnologia ASCII da Faculdade de Computação (FACOM) da Universidade Federal de Uberlândia (UFU).


## Índice
- [Tecnologias utilizadas](#tecnologias-utilizadas)
- [Instalação](#instalação)
- [Configuração](#configuração)
- [Uso](#uso)
- [API Endpoints](#api-endpoints)
- [Banco de Dados e Postman](#banco-de-dados-e-postman)
- [Contribuição e Agradecimentos](#contribuição-e-agradecimentos)


## Tecnologias utilizadas
Esse processo envolveu o uso de **Java 24 com Spring Boot** no Backend, **PostgreSQL** como Banco de Dados, **Postman** para testar as requisições.
Além disso, especificar dependências aqui


## Instalação

1. Primeiramente você deve ter a [JDK](https://www.oracle.com/java/technologies/downloads/) e [PostgreSQL](https://www.postgresql.org/) instalados para que seja possível executar o projeto.

2. Clone o repositório:

```bash
git clone https://github.com/kkademorais/ascii_backend.git
```

3. Já dentro da sua IDE de preferência (utilizei o IntelliJ da JetBrains), instale as dependências necessárias por meio do Maven.


## Configuração

Configure o Banco de Dados dentro do **application.properties** correspondente do projeto.
Devemos especificar tanto as configs do nosso BD - para que o Hibernate possa realizar a persistência e manipulação dos dados - como do JPA, para especificar a forma em que tal dependência realizará o mapeamento das classes (ORM).
Vale ressaltar que, para que as configurações do BD estejam corretas, é necessário criar a Database e o usuário responsável (se não for utilizar o padrão postgres) a serem utilizados no projeto, dentro do Postgres.
```bash
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/nome_database
spring.datasource.username=adm_database
spring.datasource.password=senhaAdmDatabase

# JPA
spring.jpa.hibernate.ddl-auto=update 
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

## Uso

1. Execute a aplicação com o Maven dentro da IDE
2. A API estará disponível em http://localhost:8080


## API Endpoints
Os endpoints disponíveis na API são os seguintes:

```markdown
POST /api/produtos - Cria um novo produto e insere no banco de dados

GET /api/produtos - Retorna a lista de todos os produtos registrados no banco de dados

GET /api/produtos/{id} - Retorna o produto a partir do seu ID especificado

GET /api/produtos/categoria/{categoria} - Retorna a lista de todos os produtos com a categoria especificada 

PUT /api/produtos/{id} - Atualiza os atributos passados na requisição do produto com o ID especificado

DELETE /api/produtos/{id} - Remove o produto do banco de dados, buscando pelo seu ID
```

## Banco de Dados e Postman
Esse projeto foi desenvolvido utilizando **PostgreSQL** como Banco de Dados Relacional, além do **Postman** para testar cada endpoint criado.
Você também pode testar as requisições por ele, seguindo os passo-a-passo abaixo:
1. Instale o [Postman](https://www.postman.com/downloads/)
2. Dentro de *Collections*, crie uma nova collection em branco para armazenar cada endpoint do projeto.
3. Crie uma nova requisição HTTP e insira a URL correspondente.
4. Para requisições POST e PUT para inserção de dados, clique em *Body* e logo embaixo em *raw* para escrever a requisição em formato JSON.


## Contribuição e Agradecimentos

Gostaria de agradecer imensamente à ASCII pela oportunidade do Processo Seletivo. Só de já ter chego até essa fase já fico feliz, e caso me vejam como uma boa adição ao time, ficarei muito honrado em participar da equipe e contribuir no que puder. 🐧

Contribuições e Feedbacks são totalmente bem-vindos! Envie uma issue ou submita um PR para que possamos crescer juntos.
