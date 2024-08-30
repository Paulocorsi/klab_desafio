# Usando uma imagem base do Maven para compilar o projeto com OpenJDK 17
FROM maven:3.8.4-openjdk-17 AS build

# Definindo o diretório de trabalho
WORKDIR /app

# Copiando o arquivo pom.xml e baixando as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiando o código-fonte e construindo o aplicativo
COPY src ./src
RUN mvn clean package -DskipTests

# Usando uma imagem base do OpenJDK 17 para executar o aplicativo
FROM openjdk:17-jdk-alpine

# Definindo o diretório de trabalho
WORKDIR /app

# Copiando o arquivo .jar gerado na etapa de build
COPY --from=build app/target/controle_produtos_pedidos-0.0.1-SNAPSHOT.jar app.jar

# Expondo a porta 8080 para acesso externo
EXPOSE 8080

# Comando para executar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]
