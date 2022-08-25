# 🏦 Banco - Open API

Um projeto desenvolvido para adquirir experiência com as tecnologias utilizadas.
A princípio era um banco simples para aprender a desenvolver em Java puro apenas 
com lógica e sem grande visão de aplicações maiores, como por exemplo desenvolvimento
do front-end, APIs para requisições, segurança e etc. 

Com o passar do tempo e com minha nova perspectiva a respeito do mundo da programação,
tive a ideia de desenvolver uma API com as mesmas funcionalidades do banco feito
em puro Java, ou seja, um projeto em que ocorre o controle da aplicação por meio do gerente
e o mesmo é capaz de gerenciar e registrar clientes, podendo inativá-los e também voltarem a ativa.
Estes clientes são livres para organizar sua vida financeira através de funções de saque, depósito, 
transferências e checagem de extrato.

## 💻 Tecnologias

Iniciei em 2022 minha carreira de Desenvolvedor de Software pela SeTi (Secretaria de Tecnologia e Informação),
desde então me deram a oportunidade de começar a estudar novas tecnologias com apoio de pessoas com conhecimento.
Aproveitei o estudo dessas novas ferramentas para engajar em projetos a parte, como foi o caso deste Banco.

No desenvolvimento deste software foi utilizado Java como linguagem de programação
e o Spring como framework, sendo eles Spring Data JPA, Spring Boot, Spring Web Services
e por fim o Lombok e Model Mapper para agregar na criação do código. É utilizado Git para versionamento
do programa e Docker para containerização do driver de Banco de Dados, sendo ele MariaDB.

- Java 11
- Spring Boot
- Spring Data JPA
- Spring Web Services
- Docker
- MariaDB

## ❔ Como utilizar a aplicação

Para rodar esse projeto, você vai precisar adicionar as seguintes variáveis de ambiente no seu .env:

`DATASOURCE_URL` - URL de acesso ao seu banco de dados.

`DATASOURCE_USER` - USER para acessar seu banco de dados.

`DATASOURCE_PASSWORD` - PASSWORD para acessar seu banco de dados.

`DOCKER_PORTSDB` - PORTA utilizada para rodar container do Docker.
