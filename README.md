# Movie List API

## Descrição

O **Movie List API** é uma API desenvolvida para listar produtores de filmes e calcular os intervalos entre os anos dos filmes vencedores do prêmio de "Pior Filme". O endpoint principal da API permite consultar esses intervalos para produtores específicos.

## Endpoint

### `/api/producers/awardWinningInterval`

Este endpoint retorna os produtores com o maior e menor intervalo entre os anos de filmes vencedores do prêmio de "Pior Filme".

- **Método:** `GET`
- **Resposta:** JSON com informações dos produtores e os intervalos entre anos dos filmes vencedores.

## Requisitos

- **Java 17**: A API requer o JDK 17 para ser executada.
- **Maven**: O Maven é utilizado para gerenciamento de dependências e construção do projeto.

## Inicialização

Para iniciar a aplicação, utilize o comando Maven Wrapper:

```bash
./mvnw spring-boot:run
```
Este comando compila o projeto e inicia o servidor Spring Boot.

## Testes

Para testar a aplicação, utilize o seguinte comando:

```bash
./mvnw test
```

## Swagger

A documentação interativa da API pode ser acessada através do Swagger. Após iniciar a aplicação, acesse:

http://localhost:8080/swagger-ui/index.html

## Configuração

### Arquivo de Configuração

A configuração da aplicação pode ser ajustada no arquivo application.properties, localizado em src/main/resources.

Para configurar o arquivo CSV que contém a lista de filmes a ser importada no startup da aplicação, adicione a seguinte linha no application.properties:

```properties
movies.import.file=./movielist.csv
```

## Exemplos

### Exemplo de Requisição

```http
GET /api/producers/awardWinningInterval
Host: localhost:8080
```

### Exemplo de Resposta

```json
{
  "min": [
    {
      "producer": "Joel Silver",
      "interval": 1,
      "previousWin": 1990,
      "followingWin": 1991
    }
  ],
  "max": [
    {
      "producer": "Matthew Vaughn",
      "interval": 13,
      "previousWin": 2002,
      "followingWin": 2015
    }
  ]
}
```