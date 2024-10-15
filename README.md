# Encurtador de URL

Este projeto é um encurtador de URL desenvolvido em **Spring Boot** e **MySQL**. Ele permite que os usuários enviem URLs longas e recebam uma URL encurtada que redireciona para a URL original.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **MySQL**: Sistema de gerenciamento de banco de dados relacional.
- **Java**: Linguagem de programação utilizada.
- **Maven**: Gerenciador de dependências e construção.

## Funcionalidades

- Encurtar URLs longas.
- Redirecionar para a URL original a partir da URL encurtada.

## Pré-requisitos

Antes de começar, certifique-se de ter os seguintes softwares instalados:

- Java JDK 11 ou superior
- MySQL Server
- Maven
- IDE (como IntelliJ IDEA ou Eclipse)

## Uso da API

Para encurtar uma URL, faça uma requisição `POST` para a seguinte URI em http://localhost:8080/url:

No corpo da requisição, passe um JSON com a URL que deseja encurtar. Por exemplo:

```json
{
  "url": "www.youtube.com"
}
```

A resposta retornará uma URL encurtada que redireciona para o mesmo site que você passou.




