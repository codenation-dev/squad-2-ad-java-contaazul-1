# Error-Center API
Error-Center é uma API que centraliza e gerencia os logs de erros de microserviços.

_Projeto desenvolvido no programa AceleraDev Java Women oferecido pela Codenation com o apoio da Conta Azul._

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
  Download [aqui](https://maven.apache.org/download.cgi)<br>
  Instalação [aqui](https://maven.apache.org/install.html)

  Para testar a instalação do Maven, no prompt de comando digite mvn:
```bash
$ mvn -v
```

### Clonando o repositório
```bash
$ git clone https://github.com/codenation-dev/squad-2-ad-java-contaazul-1.git
```

### Compilando e empacotando a aplicação
```bash
$ cd squad-2-ad-java-contaazul-1<br>
$ mvn compile<br>
$ mvn package
```

### Testando a aplicação
```bash
$ cd squad-2-ad-java-contaazul-1<br>
$ mvn test
```

### Executando a aplicação
```bash
$ cd  squad-2-ad-java-contaazul-1<br>
$ mvn spring-boot:run
```

### Acessando Endpoints
  Após executar a aplicação, teste os endpoints no Swagger:<br> 
  http://localhost:8080/swagger-ui.html`

#### Login

```txt
User: admin@email.com
Password: admin
```

### Deploy

  Heroku [link](https://error-center-api.herokuapp.com/swagger-ui.html#/)
  
## Autores
  [Alice Borges dos Santos](https://www.linkedin.com/in/alice-borges/)<br>
  [Leticia de Souza Buss](https://www.linkedin.com/in/leticia-d-942652134/)<br>
  [Karina Aparecida de Souza Padilha](https://www.linkedin.com/in/karina-aparecida-de-souza-padilha-143951106/)<br>
  [Marlei Borchardt](https://www.linkedin.com/in/marlei-borchardt)<br>
  [Natalia Suzuki](https://www.linkedin.com/in/natalia-suzuki-210349108/)

## Agradecimentos
  [Codenation](https://www.codenation.dev/)<br>
  [ContaAzul](https://contaazul.com/)
