# Microservices

Este projeto foi criado para cumprir as exigências da matéria de MICROSERVICES DEVELOPMENT do MBA em FULL STACK DEVELOPER - MICROSERVICES, CLOUD E IOT da FIAP.
Foi criada uma plataforma para facilitar o encontro e a comunicação entre os clientes e os profissionais da área de direito.
A lista de serviços criados pode ser visualizada abaixo:

## Front-end

O front-end foi criado em Vue JS versão 3 e integrado com os microserviços de usuários para realizar as operações básicas de usuários (CRUD).
Possui uma página home, uma área logada e uma página de cadastro.
Na área logada para um usuário comun é possível consultar os profissionais e editar o seu perfil.
Para o advogado, por enquanto, é possível editar o seu perfil.


## API Users Microservice

Este microserviço foi criado em python e foi utilizado o framework FastAPI e banco de dados Postgre SQL para criar os endpoints.
A documentação da API pode ser visualizada após subir o serviço no link http://IP:8001/docs
Os testes foram criados no arquivo test_main na pasta raiz do projeto.
E execução dos testes pode ser feita através do comando "pytest".

## API Contact Microservice

Este microserviço foi criado em Java utilizando o framework Quarkus para gerenciamento das mensagens entre o cliente e o advogado.
Foi utilizado o banco de dados MongoDB para a persistência dos dados.
A documentação da API pode ser visualizada após subir o serviço no link http://IP:8081/q/swagger-ui 
A imagem da aplicação está como pública no DockerHub disponível com o nome: brenomsf/api_contact_jvm.
Foi criado um processo assíncrono para o armazenamento da mensagem em uma fila do Kafka.
O tópico criado foi: contact-messages.

## Docker

Foram criadas as seguintes imagens:

- brenomsf/api_contact_jvm:latest
- fcrodrigues/vuejs-frontend-prod:2.0
- fcrodrigues/python-api:latest

No projeto existem dois arquivos do docker-compose sendo o docker-compose.yml para subir o ambiente de desenvolvimento e o docker-compose-prod.yml para subir o ambiente de produção.

No projeto front-end existe um arquivo Dockerfile.prod que contém uma variável de ambiente (VUE_APP_BACKEND_API) com a URL da API do back-end.
Caso queira subir algum ambiente que não seja na máquina local, deve-se alterar a URL para uma que corresponda ao ambiente.
Após a alteração da URL é necessária a execução do build da imagem do front-end.

Ex.: Play With Docker

ENV VUE_APP_BACKEND_API=http://ip172-18-0-52-c1bq9t3e75e000ar0vu0-80.direct.labs.play-with-docker.com
Para desenvolvimento local não é necessária nenhuma alteração, o sistema já tenta a conexão direta com o localhost.
