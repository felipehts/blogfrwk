# Projeto Blog

Este projeto foi criado com o [Spring Boot](https://start.spring.io/) versão 2.3.5.


## Escopo

Criar um projeto utilizando Spring Boot, com as seguintes funcionalidades:

- Segurança:

- Permitir o cadastro de usuários e login com autenticação via token JWT.

- Post:

- Permitir o cadastro e consulta de posts com texto, imagens e links.
- Apenas o criador do post poderá ter permissão para excluí-lo.

- Comentários:

- Suportar a adição e exclusão de comentários em posts. Os posts
- poderão ser visíveis a todos os usuários. Apenas o criador do comentário poderá ter permissão para excluí-lo.

- Fotos:

- Permitir a criação de álbuns de fotos. As fotos dos álbuns poderão ser visíveis a todos os usuários. Apenas o dono de um álbum poderá excluí-lo.

## Tecnologias

- Java 8+
- Spring Boot 2.3.5
- Banco de Dados Postgresql
- API Restful
- Maven
- Tomcat 9

## Como instalar

- Baixe ou clone este repositório usando `git clone https://github.com/felipehts/blogfrwk.git`;

## Como compilar/construir/executar

Execute `mvn spring-boot:run` para executar a versão de desenvolvimento. Depois acesse `http://localhost:8080/`.

Para executar o servidor web, acesse: [Blogfwrk-client](https://github.com/felipehts/blogfrwk-client).


## Dúvidas
Caso há alguma dúvida em relação a este repositório, envie para contato@fhtsistemas.com.br.