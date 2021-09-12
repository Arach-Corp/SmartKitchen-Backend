# Backend SmartKitchen

## ESCOPO DE PROJETO
### JUSTIFICATIVA DO PROJETO
   Em um mundo que segundo dados da UNICEF voltou a ver a fome crescer em 2019 ver
mais de um terço dos alimentos serem desperdiçados é um tanto quanto
preocupante. No ano de 2021 os alunos da FIAP do segundo ano de Análise e
Desenvolvimento foram prestigiados com a proposta de desenvolver uma solução
utilizando IoT para melhorar a experiência do usuário. Nós da Arach decidimos trazer
essa melhora no papel social do usuário, criando um sistema de despensa de
alimentos inteligente, visando não só combater o desperdício de alimentos, mas
também facilitar ao usuário o acesso as informações dos produtos, tanto nutricionais,
quantitativas, de validade etc., dos produtos cadastrados.

### FINALIDADE DO PROJETO
   Auxiliar o usuário na gestão e controle dos alimentos presentes em sua dispensa, bem
como facilitar o acesso às informações dos produtos em sua despensa, por meio de
tecnologias de IoT e utilizando uma aplicação mobile.

### OBJETIVO DO PROJETO
- Facilitar o cadastro e controle dos produtos da despensa sem que o usuário
precise preencher longos formulários de cadastro de produtos.
- Permitir ao usuário gerir os produtos de sua dispensa.
- Permitir ao usuário consultar as informações dos produtos em sua dispensa
- Reduzir o desperdício de alimentos notificando o usuário de produtos
próximos ao vencimento.

### DESCRIÇÃO DO PRODUTO
O SmartKitchen consiste em um sistema de um controle de despensa digital inteligente que
se utiliza da tecnologia RFID para adicionar os produtos a dispensa do usuário bem como
trazer ao usuário informações importantes a esses produtos como sua data de validade e suas
informações nutricionais.
A ideia é ter uma base de produtos cadastradas junto com suas informações nutricionais, e
que a essa base seja associada aos lotes do produto e suas informações da validade e então
os produtos seriam etiquetados com uma tag rfid contendo o id do lote para que seja possível
a associação desses dados. Após isso o usuário pode adicionar esses produtos a sua dispensa
digital por meio de um dispositivo IoT, responsável por ler essas informações e adicionar o
produto à despensa, assim que os produtos estiverem cadastrados o usuário poderá ler esses
dados por meio de um aplicativo mobile possibilitando assim ao usuário ter o controle dos
produtos de sua dispensa, o usuário também será notificado sobre os produtos próximos ao
vencimento

## Integrantes
Nome | RM
----|-----
Enrique Oliveira Caribé | RM85584 
Jacó Magalhães I Sem Chen | RM84717
João Victor Pessoa Queiroz | RM85568
Victor Yafusso Sunahara | RM84095

## Principais Funcionalidades Implementadas
### Cadastro de Usuário
Cadastro de novos usuários com validação de campos, validação de email único, criptografia de senhas utilizando BCrypt e salvando as informações em banco de dados, novos usuários cadastrados já recebem a role de CLIENT para que possam utilizar os outros recursos do webservice
![Documentacao Cadastro Usuarip](https://cdn.discordapp.com/attachments/886625962006028288/886744451270213642/unknown.png)

### Login de Usuário
Login de usuários com validação de campos, processo de validação de senha criptografa para autenticação e autorização de usuários utilizando bearer tokens JWT, com autorização de endpoints por roles de usuário tendo por padrão duas roles configuradas CLIENT e ADMIN, token criptografado com chave aleatoria e com tempo de expiração de 30 minutos.
![Documentacao Login de Usuarios](https://media.discordapp.net/attachments/886625962006028288/886746228455514142/unknown.png?width=924&height=670)

### Cadastro de novo dispositivos
Cadastro de novo dispositivo de usuário com validação de campos, validação de Key já registrada, cadastro já atrelando dispositivo ao usuário logado e salvando informações em banco de dados
![Documentacao cadastro de dispositivo](https://media.discordapp.net/attachments/886625962006028288/886749450373042226/unknown.png?width=1229&height=670)

### CRUD de Produtos
CRUD de Produtos e informação nutricional com validação de campos e endpoints protegidos com auntenticação e com resultados de busca paginados, salvando resultado em banco de dados
![Documentacao endpoints CRUD Produtos](https://media.discordapp.net/attachments/886625962006028288/886746936726659072/unknown.png)


## Documentação | Swaggaer UI

Ambiente | URL
----|----
LOCAL|http://localhost:8080/swagger-ui/
DEV|http://localhost:8080/swagger-ui/
PROD|https://arach-smartkitchen.herokuapp.com/swagger-ui/

### Como Fazer a autenticação no swagger
https://youtu.be/2hYCrsU2paw

## Spring Profiles
Profile | Banco de dados
---|---
test | H2 Database(In Memory)
dev | PostgreSQL (local)
prod | PostgreSQL (Heroku Postgres)

OBS: No caso de rodar a aplicação localmente verificar se está no profile de dev ou test, caso queira rodar local com o profile de prod, configuras as properties de conexão com o banco


## Arquitetura da Solução

![Desenho Arquitetura](https://cdn.discordapp.com/attachments/886625962006028288/886731730168479794/arquitetura.png)
![Desenho Arquitetura 2](https://cdn.discordapp.com/attachments/886625962006028288/886732392063197184/unknown.png)

## Funcionalidades
- [x] Cadastro de usuários: Cadastro de novos usuários no sistema, com criptografia de senha, validação de campos, email único
- [x] Login de usuários: Login de clientes utilizando bearer token do tipo JWT, com autorização de endpoints por roles, validação de campos
- [x] CRUD de Produtos
- [x] CRD de Lotes
- [x] CRD de Dispositivos
- [x] Cadastro de dispositovos
- [x] Perfil de usuario
- [x] Notificações do Usuário
- [X] Consulta de produtos na dispensa
- [ ] Integração Node-RED (POC)(Refatoração Em andamento) 

## Tabela de Endpoints
![Tabela parte 1](https://cdn.discordapp.com/attachments/886625962006028288/886731300227149844/unknown.png)
![Tabela parte 2](https://cdn.discordapp.com/attachments/886625962006028288/886731355558400020/unknown.png)

## Tecnologias
- Java
- Maven
- SpringBoot
- Hibernate
- PostgreSQL
- H2
- Heroku
- Git

## Dependencias
- Java
- Maven
- GIT

## HOW TO RUN
    git clone https://github.com/Arach-Corp/SmartKitchen-Backend.git

    cd SmartKitchen-Backend/

    mvn clean install
    mvn spring-boot:run

## Link do Projeto
https://github.com/Arach-Corp/SmartKitchen-Backend