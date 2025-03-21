# ODONTOPREV DATA MANAGEMENT

## Membros
- Luana Sousa Matos RM552621
  - Tarefas: Desenvolvimento
- Nicolas Martins RM553478
  - Tarefas: Planejamento, revisão, testes e documentação

## Link para o vídeo do projeto funcionando
- https://drive.google.com/file/d/1zHTwSEC9zAQ1Q3i1FELoXE0edE_fHmyS/view?usp=sharing

Este projeto é uma aplicação Spring Boot para gerenciar consultas odontológicas, pacientes, dentistas e sinistros. tem como funcionalidade gerenciar 
todo o ecossistema de uma consulta odontologica para pegar esses dados e ver a possibilidade de sinistros do plano de saúde.

## Pré-requisitos

- Java 17 ou superior
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

## Novas páginas de CRUD com thymeleaf
- Consultas: http://localhost:8080/consulta/lista
- Sinistros: http://localhost:8080/sinistro/lista
 
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

- **POST /pacientes**: Cria um novo paciente.
- **GET /pacientes**: Retorna todos os pacientes.
- **GET /pacientes/{id}**: Retorna um paciente pelo ID.
- **PUT /pacientes/{id}**: Atualiza um paciente existente.
- **DELETE /pacientes/{id}**: Deleta um paciente.

### Tratamentos

- **POST /tratamentos**: Cria um novo tratamento.
- **GET /tratamentos**: Retorna todos os tratamentos.
- **GET /tratamentos/{id}**: Retorna um tratamento pelo ID.
- **PUT /tratamentos/{id}**: Atualiza um tratamento existente.
- **DELETE /tratamentos/{id}**: Deleta um tratamento.

### Sinistros

- **POST /sinistros**: Cria um novo sinistro.
- **GET /sinistros**: Retorna todos os sinistros.
- **GET /sinistros/{id}**: Retorna um sinistro pelo ID.
- **PUT /sinistros/{id}**: Atualiza um sinistro existente.
- **DELETE /sinistros/{id}**: Deleta um sinistro.

### Generos

- **POST /genero**: Cria um novo genero.
- **GET /genero**: Retorna todos os generos.
- **GET /genero/{id}**: Retorna um genero pelo ID.
- **PUT /genero/{id}**: Atualiza um genero existente.
- **DELETE /genero/{id}**: Deleta um genero.

### Pais

- **POST /pais**: Cria um novo pais.
- **GET /pais**: Retorna todos os paises.
- **GET /pais/{id}**: Retorna um pais pelo ID.
- **PUT /pais/{id}**: Atualiza um pais existente.
- **DELETE /pais/{id}**: Deleta um pais.

### Estado

- **POST /estado**: Cria um novo estado.
- **GET /estado**: Retorna todos os estados.
- **GET /estado/{id}**: Retorna um estado pelo ID.
- **PUT /estado/{id}**: Atualiza um estado existente.
- **DELETE /estado/{id}**: Deleta um estado.

### Cidade

- **POST /cidade**: Cria uma nova cidade.
- **GET /cidade**: Retorna todas as cidades.
- **GET /cidade/{id}**: Retorna uma cidade pelo ID.
- **PUT /cidade/{id}**: Atualiza uma cidade existente.
- **DELETE /cidade/{id}**: Deleta uma cidade.

### Bairro

- **POST /bairro**: Cria um novo bairro.
- **GET /bairro**: Retorna todos os bairros.
- **GET /bairro/{id}**: Retorna um bairro pelo ID.
- **PUT /bairro/{id}**: Atualiza um bairro existente.
- **DELETE /bairro/{id}**: Deleta um bairro.

### Endereco

- **POST /endereco**: Cria um novo endereco.
- **GET /endereco**: Retorna todos os enderecos.
- **GET /endereco/{id}**: Retorna um endereco pelo ID.
- **PUT /endereco/{id}**: Atualiza um endereco existente.
- **DELETE /endereco/{id}**: Deleta um endereco.
  
## Swagger

`http://localhost:8080/swagger-ui.html`

## Testes
- Vá até collections no postman e importe o arquivo .json

https://github.com/nemcolas/ChallengeJava/blob/master/documentos/testes_api_postman.json

## Deploy e Testes

Este guia descreve o passo a passo para realizar o deploy e testar a aplicação.

### 1. Ambiente Local
1. Clone o repositório:
   ```sh
   git clone <URL_DO_REPOSITORIO>
   cd <NOME_DO_PROJETO>
   ```
2. Compile a aplicação utilizando Gradle para gerar o arquivo JAR:
   ```sh
   ./gradlew build
   ```
3. Execute a aplicação localmente e acesse a porta 8080 para validar o funcionamento:
   ```sh
   java -jar build/libs/<NOME_DO_ARQUIVO>.jar
   ```

### 2. Containerização
1. Construa a imagem Docker da aplicação:
   ```sh
   docker build -t challengejava .
   ```
2. Execute o container localmente para validar a imagem:
   ```sh
   docker run -p 8080:8080 challengejava
   ```

### 3. Azure Container Registry (ACR)
1. Crie um Resource Group na região `brazilsouth`:
   ```sh
   az group create --name ChallengeSprint3 --location brazilsouth
   ```
2. Crie um ACR chamado `challengesprint3`:
   ```sh
   az acr create --resource-group ChallengeSprint3 --name challengesprint3 --sku Basic
   ```
3. Faça login no ACR:
   ```sh
   az acr login --name challengesprint3
   ```
4. Tagueie a imagem Docker com o endereço do ACR:
   ```sh
   docker tag challengejava challengesprint3.azurecr.io/challengejava:v1
   ```
5. Envie a imagem para o ACR:
   ```sh
   docker push challengesprint3.azurecr.io/challengejava:v1
   ```

### 4. Azure Container Instances (ACI)
1. Crie uma instância de container usando a imagem do ACR:
   ```sh
   az container create --resource-group ChallengeSprint3 \
     --name challengejava-instance \
     --image challengesprint3.azurecr.io/challengejava:v1 \
     --cpu 1 --memory 1.5 \
     --dns-name-label challengejava-aci \
     --ports 8080 
   ```
2. Verifique se a instância está rodando:
   ```sh
   az container show --resource-group ChallengeSprint3 --name challengejava-instance --query instanceView.state
   ```
3. Obtenha o FQDN para acessar a aplicação:
   ```sh
   az container show --resource-group ChallengeSprint3 --name challengejava-instance --query ipAddress.fqdn --output tsv
   ```

### 5. Teste e Monitoramento
1. Acesse a aplicação pelo navegador ou via curl usando o FQDN obtido:
   ```sh
   curl http://challengejava-aci.brazilsouth.azurecontainer.io:8080
   ```
2. Verifique os logs do container:
   ```sh
   az container logs --resource-group ChallengeSprint3 --name challengejava-instance
   ```
3. Se necessário, reinicie a instância:
   ```sh
   az container restart --resource-group ChallengeSprint3 --name challengejava-instance
   ```
4. Para testar a API, os JSONs com os dados necessários estão disponíveis no Swagger da aplicação.



## Diagrama de Entidade e Relacionamento

![diagrama](Relacional.jpg)

## Diagramas de Classes de Entidade

### Controllers

![diagrama](controllers.png)

### Dtos

![diagrama](dtos.png)

### Exception

![diagrama](exception.png)

### Mappers

![diagrama](mappers.png)

### Repositories

![diagrama](repositories.png)

### Models

![diagrama](models.png)
