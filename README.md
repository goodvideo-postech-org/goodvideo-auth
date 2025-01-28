# goodvideo-auth

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=goodvideo-postech-org_goodvideo-auth&metric=coverage)](https://sonarcloud.io/summary/new_code?id=goodvideo-postech-org_goodvideo-auth)

#### A documentação completa de todos os repositórios e como se interagem pode ser encontrada por aqui: [Goodvideo](https://github.com/goodvideo-postech-org/goodvideo-doc)

## Sobre o projeto
Goodvideo-auth é uma aplicação que provê um software de gerenciamento de usuários e geração do token.
Junto com os nossos endpoints, temos a documentação do Swagger, que pode ser acessada através do link: http://localhost:8080/swagger-ui/index.html.

Para rodar o projeto, basta rodar o comando `docker-compose up -d`, assim que o download das imagens do banco e da aplicação forem finalizadas, o projeto estará rodando na porta 8080.
Ou via kubernetes com o comando `kubectl apply -f .`, obter o IP a partir do `kubectl get svc` e acessar a URL `http:\\<ip>/swagger-ui/index.html`

Dentre os nossos endpoints, temos:

- **POST /login**: Endpoint responsável por gerar o token de autenticação.
- **POST /usuario**: Endpoint responsável por criar um novo usuário.
- **GET /usuario**: Endpoint responsável por listar todos os usuários.

## Fluxo do processo
- O usuário faz uma requisição POST para o endpoint /usuario informando o email, senha e nome do usuário para realizar um cadastro. 
- O usuário pode realizar uma requisição GET para o endpoint /usuario para listar todos os usuários cadastrados. 
- Após o cadastro, o usuário pode realizar uma requisição POST para o endpoint /login informando o email e senha para gerar o token de autenticação, que será utilizado nas outras aplicações.

# Tecnologias

- Java
- Spring Boot
- Postgre
- Amazon RDS
- Docker
- Kubernetes
- Swagger

