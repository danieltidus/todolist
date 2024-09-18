# Anotações de Teste no Spring Boot

## `@SpringBootTest`
- **Descrição**: Carrega o contexto completo da aplicação Spring Boot para testes de integração.
- **Uso**: Utilizado para testes que requerem o contexto completo da aplicação, incluindo todos os beans e configurações.

## `@AutoConfigureMockMvc`
- **Descrição**: Configura automaticamente o `MockMvc` para testes de controladores Spring MVC.
- **Uso**: Facilita a simulação de requisições HTTP para testar endpoints de controladores.

## `@ActiveProfiles`
- **Descrição**: Especifica quais perfis ativos devem ser usados ao carregar o contexto da aplicação.
- **Uso**: Utilizado para ativar configurações específicas de perfil, como `test`, durante os testes.

## `@WithMockUser`
- **Descrição**: Simula um usuário autenticado com o nome de usuário especificado.
- **Uso**: Utilizado para testes que requerem autenticação, permitindo simular um usuário logado.

## `@TestMethodOrder`
- **Descrição**: Define a ordem de execução dos métodos de teste.
- **Uso**: Utilizado para garantir que os testes sejam executados em uma ordem específica, como a ordem anotada (`OrderAnnotation.class`).

## `@ExtendWith`
- **Descrição**: Estende a funcionalidade de uma classe de teste com uma ou mais extensões do JUnit 5.
- **Uso**: Utilizado para registrar extensões que adicionam ou modificam o comportamento dos testes, como `SpringExtension` para integração com o Spring.