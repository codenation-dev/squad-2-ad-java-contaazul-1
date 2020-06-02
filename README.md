# Error-Center API
Error-Center é uma API que centraliza e gerencia os logs de erros de microserviços. <br>
*Projeto desenvolvido no programa AceleraDev Java Women oferecido pela Codenation com o apoio da Conta Azul.* <br>
[LIVE DEMO](https://error-center-api.herokuapp.com/swagger-ui.html#/) [ Wiki](https://github.com/codenation-dev/squad-2-ad-java-contaazul-1.wiki.git) <br> 

## Tecnologias
    Eclipse (4.14.0)
    Java (1.8)
    Spring Boot (2.1.6)
    Maven (3.5.1)
    Apache Tomcat (9.0.2)
    Spring Data JPA (2.1.6)
    Postgresql 10
    Mockito (2.23.4)
    JUnit 5 
    Swagger (2.9.2) 
    Heroku

## Instalação

### Instalando Maven
  [Download](https://maven.apache.org/download.cgi) <br>
  [Instalação](https://maven.apache.org/install.html)

  Para testar a instalação do Maven, no prompt de comando digite mvn:
```bash
$ mvn -v
```

### Clonando o repositório
```bash
$ git clone https://github.com/codenation-dev/squad-2-ad-java-contaazul-1.git
```

### Escolhendo o banco de dados
Para testar a aplicação localmente deve ter um banco de dados pré configurado. No arquivo application.properties tem duas opções, banco Postgres e o banco H2.

#### Optando pelo banco Postgres:
Instale o banco [Postgres](https://www.postgresql.org/download/) e o [pgAdmin](https://www.pgadmin.org/download/)

Caso precise, este vídeo ensina como instalar e configurar:
[How Install](https://www.youtube.com/watch?v=e1MwsT5FJRQ)

Após a instalação crie um banco de dados no Postgres.

No projeto clonado do GitHub navegue até a pasta resources, no arquivo application.properties coloque nas linhas abaixo o nome, a senha e o user do banco de dados criado no item anterior:
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/coloque aqui o nome do banco
spring.datasource.username=user do banco
spring.datasource.password=senha do banco
```
Visualizar as tabelas do projeto no banco é possível após executar o item “Executando a aplicação” deste documento. 

#### Optando pelo banco H2:
No projeto clonado do GitHub navegue até a pasta resources, no arquivo application.properties comente o banco ativo, postgres:
```bash
#Datasource Postgresql
#spring.datasource.driver-class-name=org.postgresql.Driver
#spring.datasource.url=jdbc:postgresql://localhost:5432/ErrorCenterV2
#spring.datasource.username=postgres
#spring.datasource.password=Urso32306

#JPA para Postgresql
#spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL9Dialect
#spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false
```

Feito isso, retire o comentário do banco H2:
```bash
# datasource H2
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.url=jdbc:h2:mem:squad2-errorcenter
spring.datasource.username=sa
spring.datasource.password=

# jpa
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# h2 banco de dados
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

O acesso ao banco é possível após executar o item “Executando a aplicação” deste documento. 
```bash
Acesse em: http://localhost:8080/h2-console/
Banco: mem:squad2-errorcenter
Usuário: sa
Senha:
```

### Compilando e empacotando a aplicação
```bash
$ cd squad-2-ad-java-contaazul-1
$ mvn compile
$ mvn package
```

### Testando a aplicação
```bash
$ cd squad-2-ad-java-contaazul-1
$ mvn test
```

### Executando a aplicação
```bash
$ cd  squad-2-ad-java-contaazul-1
$ mvn spring-boot:run
```

### Acessando endpoints
  Após executar a aplicação, teste os endpoints no Swagger:
  http://localhost:8080/swagger-ui.html

#### Login

```txt
User: admin@email.com
Password: admin
```

### Deploy

  [Heroku](https://error-center-api.herokuapp.com/swagger-ui.html#/)
  
### Doc
[ClassDiagram](https://github.com/codenation-dev/squad-2-ad-java-contaazul-1/blob/master/doc/ClassDiagram.jpg) <br>
[UserCase](https://github.com/codenation-dev/squad-2-ad-java-contaazul-1/blob/master/doc/UserCase.JPG) <br>
[MER](https://github.com/codenation-dev/squad-2-ad-java-contaazul-1/blob/master/doc/MER.JPG) <br>
[Manual](https://github.com/codenation-dev/squad-2-ad-java-contaazul-1/blob/master/doc/Como%20testar%20a%20API.pdf) <br>
  
## Autores
  [Alice Borges dos Santos](https://www.linkedin.com/in/alice-borges/) <br>
  [Leticia de Souza Buss](https://www.linkedin.com/in/leticia-d-942652134/) <br>
  [Karina Aparecida de Souza Padilha](https://www.linkedin.com/in/karina-aparecida-de-souza-padilha-143951106/) <br>
  [Marlei Borchardt](https://www.linkedin.com/in/marlei-borchardt) <br>
  [Natalia Suzuki](https://www.linkedin.com/in/natalia-suzuki-210349108/) <br>

## Agradecimentos
  [Codenation](https://www.codenation.dev/)<br>
  [ContaAzul](https://contaazul.com/)
