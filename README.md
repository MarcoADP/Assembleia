# Assembleia

API para realizar uma assembleia com criação da pauta, sessão, voto e resultado.

Stack:
- Java
- Spring
- Maven
- Lombok
- OpenAPI
- Postgre

Comandos:
- Run: mvn spring-boot:run
- Test: mvn test
- OpenAPI: http://localhost:8080/swagger-ui/index.html#/
- OpenAPI Cloud: http://assembleia.sa-east-1.elasticbeanstalk.com/swagger-ui/index.html#/
- URL Base: http://assembleia.sa-east-1.elasticbeanstalk.com


Telas:
- Tela de Seleção de Pautas
- Tela de Criação de Pauta
- Tela de Criação de Sessão
- Tela de Votação
- Tela de Resultado

Ações possíveis na Tela de Seleção de Pautas
- Ir para a Tela de Criação de Pautas
- Ir para a Tela de Criação de Sessão caso a pauta selecionada não tenha uma sessão criada
- Ir para a Tela de Votação caso a pauta selecionada tenha uma sessão ativa
- Ir para a Tela de Resultado caso a pauta selecionada tenha uma sessão finalizada

Collection Postman
- O arquivo *Assembleia.postman_collection.json* em *src/main/resources* é uma coleção do Postman com as principais requisições do sistema.
- Basta importar a coleção no Postman para visualizar e testar as requisições. 
