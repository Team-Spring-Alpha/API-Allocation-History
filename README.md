<h1 align="center"> Apollo70 Filmes - API-Allocation-History</h1>

![Badge](http://img.shields.io/static/v1?label=STATUS&message=DEVELOPMENT&color=yellow&style=for-the-badge)
![Badge](http://img.shields.io/static/v1?label=RELEASE%20DATE&message=SEPTEMBER%202022&color=yellow&style=for-the-badge)

<p align="center">O projeto Apollo70 Filmes consiste em uma aplicação de uma locadora de filmes. A aplicação é dividida em microserviços que detém as funcionalidades necessárias para o usuário ter acesso a um catálogo de filmes disponíveis para compra e alocação.<p>

## Fluxo de Funcionamento da Aplicação

O `Allocation-History` recebe por meio do microserviço `API-User` informações sobre a alocação/compra e então salva isso em um banco de dados Mongo. Para a inserção de dados no histórico a aplicação recebe algumas informações por mensageria e caso o usuário queira acessar o histórico ele pode fazer isso por meio de um endpoint GET.

// Descrever melhor a aplicação //

## Funcionalidades

| Método | Caminho | Descrição |
|---|---|---|
| `GET` | /api/allocation-history/{userId} | Busca o histórico de um determinado usuário|

## Tecnologias utilizadas
- Spring Validation
- Spring Web
- Spring Fox
- Spring AMQP
- ModelMapper
- Lombok
- MongoDB
- Swagger

> Para saber mais acesse a [Documentação](https://movieallocationhistory.herokuapp.com/swagger-ui/index.html)
