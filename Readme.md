# ChallengeSprint1

Este projeto é uma aplicação Spring Boot para gerenciar consultas odontológicas, pacientes, dentistas e sinistros. tem como funcionalidade gerenciar 
todo o ecossistema de uma consulta odontologica para pegar esses dados e ver a possibilidade de sinistros do plano de saúde.

## Pré-requisitos

- Java 11 ou superior
- Gradle
- IDE de sua escolha (recomendado: IntelliJ IDEA)

## Instalação

1. Clone o repositório:
    ```bash
    git clone https://github.com/nemcolas/ChallengeSprint1.git
    ```
2. Navegue até o diretório do projeto:
    ```bash
    cd ChallengeSprint1
    ```
3. Compile o projeto usando Gradle:
    ```bash
    ./gradlew build
    ```
4. Execute a aplicação:
    ```bash
    ./gradlew bootRun
    ```

## Uso

A aplicação estará disponível em `http://localhost:8080`. Você pode usar ferramentas como Postman ou cURL para interagir com a API.

## Endpoints

### Consultas

- **POST /consulta**: Cria uma nova consulta.
- **GET /consulta**: Retorna todas as consultas.
- **GET /consulta/{id}**: Retorna uma consulta pelo ID.
- **PUT /consulta/{id}**: Atualiza uma consulta existente.
- **DELETE /consulta/{id}**: Deleta uma consulta.

### Dentistas

- **POST /dentista**: Cria um novo dentista.
- **GET /dentista**: Retorna todos os dentistas.
- **GET /dentista/{id}**: Retorna um dentista pelo ID.
- **PUT /dentista/{id}**: Atualiza um dentista existente.
- **DELETE /dentista/{id}**: Deleta um dentista.

### Pacientes

- **POST /paciente**: Cria um novo paciente.
- **GET /paciente**: Retorna todos os pacientes.
- **GET /paciente/{id}**: Retorna um paciente pelo ID.
- **PUT /paciente/{id}**: Atualiza um paciente existente.
- **DELETE /paciente/{id}**: Deleta um paciente.

### Tratamentos

- **POST /tratamento**: Cria um novo tratamento.
- **GET /tratamento**: Retorna todos os tratamentos.
- **GET /tratamento/{id}**: Retorna um tratamento pelo ID.
- **PUT /tratamento/{id}**: Atualiza um tratamento existente.
- **DELETE /tratamento/{id}**: Deleta um tratamento.

proximos passos:

Criar o Controller para o Sinistro e tratamento. fazer os relacionamentos entre as entidades.
