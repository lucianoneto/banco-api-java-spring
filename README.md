# 🏦 Banco - Open API

Um projeto desenvolvido para adquirir experiência com as tecnologias utilizadas.
A princípio era um banco simples para aprender a desenvolver em Java puro, apenas 
com lógica pura e sem grande visão de aplicação. 

Com o passar do tempo e com minha nova visão a respeito do mundo da programação,
tive a ideia de desenvolver uma API com as mesmas funcionalidades do banco feito
em puro Java, ou seja, controle da aplicação por meio do gerente e funções de 
saque, depósito, transferências e checagem de extrato disponibilizadas ao cliente.

## 💻 Tecnologias

Iniciei em 2022 minha carreira de Desenvolvedor de Software pela SeTI, desde então
me deram a oportunidade de começar a estudar novas tecnologias com apoio de 
pessoas com conhecimento. Aproveitei o estudo dessas novas ferramentas para engajar
em projetos a parte, como foi o caso deste Banco.

No desenvolvimento deste software foi utilizado Java como linguagem de programação
e o Spring como framework, sendo eles Spring Data JPA, Spring Boot, Spring Web Services
e por fim o Lombok e Model Mapper para agregar na criação do código. É utilizado Git para versionamento
do programa e Docker para containerização do driver de Banco de Dados, sendo ele
MariaDB.

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
