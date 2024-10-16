# SPACE PROBE

## 🚀 Como rodar

### 📝 Requisitos
- Java 21
- Maven 3.9.8

### ⬆️ Iniciando a aplicação

1. Execute `./mvnw clean package` para construir a aplicação
1. Execute `./mvnw spring-boot:run` para iniciar a aplicação
2. Acesse http://localhost:8080

## 📄 Swagger
Existe um swagger rodando em http://localhost:8080/swagger-ui/index.html


## Funcionalidades

- Criar planetas com dimensões (largura e altura) e obstáculos.
- Criar sondas associadas a um planeta e definir suas posições e direções iniciais.
- Enviar comandos para movimentar as sondas (virar para esquerda/direita e mover para frente).
- Detectar colisões entre sondas e obstáculos.
- Impedir que sondas se movimentem para fora dos limites do planeta.

### Comandos

Os comandos disponíveis para as sondas são:

- **L**: Girar a sonda 90 graus para a esquerda.
- **R**: Girar a sonda 90 graus para a direita.
- **M**: Movimentar a sonda 1 unidade na direção que ela está apontando.

## Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto.
- **Spring Boot**: Framework utilizado para criar a API REST.
- **JPA/Hibernate**: Para persistência de dados e mapeamento objeto-relacional.
- **JUnit 5**: Para testes unitários.
- **Mockito**: Para mocks em testes unitários.
- **H2 Database**: Banco de dados em memória utilizado para testes e desenvolvimento.
- **Swagger**: Documentação e interface de teste da API.
