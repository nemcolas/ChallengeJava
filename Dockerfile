# Usando a imagem do Gradle com JDK 21
FROM gradle:8.10.2-jdk21

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia todo o código-fonte para o contêiner
COPY . .

# Constrói o projeto e ignora os testes
RUN gradle build -x test

# Verifica a presença dos arquivos gerados
RUN ls -la build/libs

# Adiciona um usuário sem senha
RUN adduser --disabled-password appuser

# Define o usuário para executar a aplicação
USER appuser

# Comando para executar a aplicação
CMD ["java", "-jar", "build/libs/ChallengeSprint1-0.0.1-SNAPSHOT.jar"]
