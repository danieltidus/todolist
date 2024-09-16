# Use uma imagem base do Maven para compilar a aplicação
FROM maven:3.8.6-openjdk-21 AS build

# Defina o diretório de trabalho dentro do container
WORKDIR /app

# Copie o arquivo pom.xml e as dependências do Maven
COPY pom.xml .

# Baixe as dependências do Maven
RUN mvn dependency:go-offline

# Copie o código-fonte da aplicação
COPY src ./src

# Compile a aplicação
RUN mvn clean package -DskipTests

# Use uma imagem base do OpenJDK para rodar a aplicação
FROM openjdk:21-jdk-slim

# Defina o diretório de trabalho dentro do container
WORKDIR /app

# Copie o arquivo JAR da aplicação do estágio de build
COPY --from=build /app/target/todolist-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta que a aplicação irá rodar
EXPOSE 8080

# Defina o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]