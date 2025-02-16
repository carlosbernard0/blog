# Projeto Blog API - Java + Spring Boot

## Ferramentas Utilizadas: 
* Java 17
* Spring 3.3.4
* MySql 8.0.39
* Maven 3.9.9
* Demais bibliotecas descritas no `pom.xml`
* MySQL Workbench

## Setup Banco de Dados
* Baixar MySQL e MySQL Workbench
* Abra o MySQL Workbench e crie um schema chamado `blog` : `create schema blog;`


## Setup Java
* Clonar o projeto
* Baixar intellij community ou ultimate
* Abrir o projeto
* Abra o arquivo `application.properties` e faça a configuração das variaveis de ambiente conforme o video [VARIÁVEIS DE AMBIENTE NO SPRING BOOT](https://youtu.be/GyaE1-vbjf4?si=xTJlRYnIjZKA2_J8)
* Rode o projeto no arquivo `BlogApiApplication`

##Teste de funcionalidades
* Necessário um usuário para fazer o login obter o token 
* [Link do Swagger](http://localhost:8080/swagger-ui/index.html#/)
 ![image](https://github.com/user-attachments/assets/307abab1-b313-4ead-bacd-72d9a0f3a75f)




## Complementos -> [Documentação Blog API - Java + Spring Boot](https://sweet-result-019.notion.site/Documenta-o-Blog-API-Java-Spring-Boot-16e63ae14d7b8022b433c4ac7393fe35)
* Página no notion sobre o projeto e funcionalidades:
   *  Entidades
   *  Mapstruct
   *  Relacionamentos
   *  Autenticação
   *  Encriptação de senha
   *  Criação de usuário
   *  Implementação de cargos
   *  Envio de email para recuperação de senha
   *  A autenticação de dois fatores (2FA) via email
   *  Recuperação de usuario através do token
