## Central de Erros

### Objetivo
O objetivo do projeto é criar uma API para fazer o gerenciamento de erros de uma aplicação.

### Contextualização
Em projetos modernos é cada vez mais comum o uso de arquiteturas 
baseadas em serviços ou microsserviços. Nestes ambientes complexos, 
erros podem surgir em diferentes camadas da aplicação (backend, 
frontend, mobile, desktop) e mesmo em serviços distintos. Desta forma, 
é muito importante que os desenvolvedores possam centralizar todos os 
registros de erros em um local, de onde podem monitorar e tomar 
decisões mais acertadas. Neste projeto vamos implementar um sistema 
para centralizar registros de erros de aplicações.

### Requisitos técnicos obrigatórios
Para o projeto se faz necessário usar os conhecimentos repassados
durante o AceleraDev, portanto tem alguns requisitos solicitados:
* Criar endpoints para serem usados pelo frontend da aplicação;
* Criar um endpoint que será usado para gravar os logs de erro em um banco de dados relacional;
* A API deve ser segura, permitindo acesso apenas com um token de autenticação válido;

### Definições do sistema
![Captura de tela de 2020-05-11 23-12-36](https://user-images.githubusercontent.com/45111422/81630820-f1f9f580-93dc-11ea-98ad-a4510d5eead6.png)
* UC01: O usuário digita: email e senha
* UC04: Sistema verifica se o email é válido, se a senha é válida, se o email já existe
* UC02: Usuário digita user e senha. 
* UC04: Sistema verifica se o email é válido, se a senha é válida, se o email já existe
* UC03: Usuário quando logado, Sistema apresenta uma tela de logs que existem no em seu nome
* UC05: Usuário seleciona opções de filtro.
* UC05: Sistema carrega a busca
* UC06: Usuário seleciona salvar, deletar, enviar por email
* UC07: Usuário clica em sair
* UC07: Sistema volta para a tela de login

### Equipe do projeto

|Nome   |  Linkedin |  GitHub |  
|---|---|---|---|
| Leticia de Souza Buss  | https://www.linkedin.com/in/leticia-d-942652134/| https://github.com/leticiabuss  |   
| Alice Borges dos Santos |  https://www.linkedin.com/in/alice-borges/ | https://github.com/aliceborges |  
| Natalia Suzuki | https://www.linkedin.com/in/natalia-suzuki-210349108/  | https://github.com/nataliasuzuki |   
| Marlei Borchardt | https://www.linkedin.com/in/marlei-borchardt  | https://github.com/marleiSilveira| 
| Karina Aparecida de Souza Padilha | https://www.linkedin.com/in/karina-aparecida-de-souza-padilha-143951106/  | - | 

