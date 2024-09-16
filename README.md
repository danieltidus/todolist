# Todolist Application

## Requisitos

- Java 21
- Maven 3.6+
- PostgreSQL (para ambiente de produção)

## Configuração

### Perfis do Spring Boot

A aplicação utiliza perfis do Spring Boot para diferenciar entre os ambientes de desenvolvimento e produção.

### Arquivos de Configuração

- `src/main/resources/application.properties`: Configurações comuns e de desenvolvimento.
- `src/main/resources/application-prod.properties`: Configurações específicas para produção.

## Levantando a Aplicação

### Ambiente de Desenvolvimento

1. **Clone o repositório:**

    ```sh
    git clone https://github.com/seu-usuario/todolist.git
    cd todolist
    ```

2. **Compile e execute a aplicação:**

    ```sh
    mvn clean install
    mvn spring-boot:run
    ```

    A aplicação estará disponível em `http://localhost:8080`.

### Ambiente de Produção

1. **Configure o banco de dados PostgreSQL:**

    Certifique-se de que o PostgreSQL está instalado e configurado corretamente. Atualize as credenciais no arquivo `src/main/resources/application-prod.properties` conforme necessário.

2. **Compile a aplicação:**

    ```sh
    mvn clean install
    ```

3. **Execute a aplicação com o perfil de produção:**

    ```sh
    java -jar -Dspring.profiles.active=prod target/todolist-0.0.1-SNAPSHOT.jar
    ```

    A aplicação estará disponível na URL configurada no arquivo `application-prod.properties`.

## Rodando os Testes

1. **Rodar todos os testes:**

    Para rodar todos os testes do projeto, execute o seguinte comando no terminal:

    ```sh
    mvn test
    ```

2. **Rodar testes específicos:**

    Para rodar testes específicos, você pode usar o parâmetro `-Dtest` seguido do nome da classe de teste. Por exemplo, para rodar a classe de teste `MyTestClass`, use:

    ```sh
    mvn -Dtest=MyTestClass test
    ```

3. **Rodar métodos de teste específicos:**

    Para rodar métodos de teste específicos dentro de uma classe, use o parâmetro `-Dtest` seguido do nome da classe e do método. Por exemplo, para rodar o método `myTestMethod` na classe `MyTestClass`, use:

    ```sh
    mvn -Dtest=MyTestClass#myTestMethod test
    ```

## Dependências

A aplicação utiliza as seguintes dependências principais:

- Spring Boot
- Spring Data JPA
- Spring Security
- PostgreSQL Driver
- H2 Database (para desenvolvimento)
- ModelMapper
- JJWT

## Contato

Para mais informações, entre em contato com [seu-email@dominio.com](mailto:seu-email@dominio.com).