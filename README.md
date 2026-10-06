# FinTrack

Aplicação desktop para controle financeiro pessoal, desenvolvida em **Java**, utilizando **JavaFX** para a interface gráfica e **MySQL** para armazenamento dos dados.

O sistema permite cadastrar, atualizar e remover transações financeiras, além de apresentar um resumo das receitas, despesas e saldo.

---

## Tecnologias utilizadas

* Java 21
* JavaFX 21.0.8
* Maven
* MySQL
* MySQL Connector/J
* FXML
* JDBC

---

## Funcionalidades

* Cadastro de transações
* Definição do tipo da transação:

  * Receita
  * Despesa
* Alteração de transações
* Remoção de transações
* Visualização das transações em tabela
* Seleção de uma transação para edição
* Cálculo automático de:

  * Total de receitas
  * Total de despesas
  * Saldo
* Exibição dos valores com indicação de entrada (`+`) ou saída (`-`)
* Ordenação das transações por data

---

# Requisitos

Antes de executar o projeto, instale:

### 1. Java JDK 21

O projeto utiliza Java 21.

Verifique a instalação com:

```bash
java -version
```

O resultado deve indicar uma versão 21.

---

### 2. Maven

O projeto utiliza Maven para gerenciamento das dependências.

Verifique com:

```bash
mvn -version
```

Também é possível executar o projeto utilizando o Maven integrado à IDE.

---

### 3. MySQL

É necessário ter o **MySQL Server** instalado e em execução.

Você pode utilizar o MySQL Workbench para administrar o banco de dados.

---

### 4. IDE

O projeto pode ser aberto em uma IDE compatível com Maven e Java 21.

O desenvolvimento deste projeto foi realizado utilizando:

* NetBeans 31

---

# Configuração do banco de dados

Depois de instalar e iniciar o MySQL, crie o banco de dados:

```sql
CREATE DATABASE fintrack;
```

Depois:

```sql
USE fintrack;
```

A tabela de transações pode ser criada com:

```sql
CREATE TABLE transacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data DATE NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    tipo VARCHAR(20) NOT NULL
);
```

O projeto também possui a criação automática da tabela através do `TransacaoDAO`.

---

# Configuração da conexão

A conexão com o MySQL está localizada em:

```text
src/main/java/com/fintrack/database/Conexao.java
```

A configuração padrão é:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/fintrack"
        + "?useSSL=false"
        + "&serverTimezone=UTC"
        + "&allowPublicKeyRetrieval=true";

private static final String USUARIO = "root";

private static final String SENHA = "";
```

Caso o seu usuário `root` tenha uma senha, altere:

```java
private static final String SENHA = "";
```

para:

```java
private static final String SENHA = "sua_senha";
```

---

# Instalação das dependências

As dependências são gerenciadas pelo Maven através do arquivo:

```text
pom.xml
```

Entre as principais dependências estão:

```xml
<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-controls</artifactId>
    <version>21.0.8</version>
</dependency>

<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-fxml</artifactId>
    <version>21.0.8</version>
</dependency>

<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>9.4.0</version>
</dependency>
```

Ao abrir o projeto como um projeto Maven, a IDE deve baixar automaticamente as dependências.

---

# Como executar

## Pelo NetBeans

1. Abra o projeto no NetBeans.
2. Aguarde o Maven carregar as dependências.
3. Certifique-se de que o MySQL Server está executando.
4. Confira as configurações de usuário e senha em `Conexao.java`.
5. Faça **Clean and Build**.
6. Execute o projeto.

O projeto utiliza o plugin:

```text
javafx:run
```

para iniciar a aplicação JavaFX.

---

# Como utilizar

Ao iniciar o sistema, será exibida a tela principal do FinTrack.

## Adicionar uma transação

Preencha:

* Data
* Descrição
* Valor
* Tipo

Exemplo:

```text
Data:       06/10/2026
Descrição:  Salário
Valor:      2500
Tipo:       Receita
```

Depois clique em:

**Adicionar**

A transação será armazenada no MySQL e exibida na tabela.

---

## Atualizar uma transação

1. Clique em uma transação na tabela.
2. Os dados serão carregados nos campos.
3. Altere as informações desejadas.
4. Clique em **Atualizar**.

A alteração será salva no banco de dados.

---

## Remover uma transação

1. Selecione uma transação na tabela.
2. Clique em **Remover**.

A transação será excluída do banco de dados.

---

# Resumo financeiro

Na parte inferior da aplicação são apresentados:

```text
Receitas
Despesas
Saldo
```

O saldo é calculado como:

```text
Saldo = Receitas - Despesas
```

Por exemplo:

```text
Receitas: R$ 3.000,00
Despesas: R$ 1.200,00
Saldo:    R$ 1.800,00
```

---

# Estrutura do projeto

```text
FinTrack
│
├── pom.xml
│
└── src
    └── main
        ├── java
        │   └── com
        │       ├── finTrack
        │       │   ├── Main.java
        │       │   └── FinTrackController.java
        │       │
        │       └── fintrack
        │           ├── dao
        │           │   └── TransacaoDAO.java
        │           │
        │           ├── database
        │           │   └── Conexao.java
        │           │
        │           ├── model
        │           │   └── Transacao.java
        │           │
        │           ├── service
        │           │   └── RepositorioGenerico.java
        │           │
        │           └── FinController.java
        │
        └── resources
            └── com
                └── finTrack
                    └── fintrack.fxml
```

---

# Arquitetura

O projeto utiliza uma separação simples entre interface, modelo e acesso ao banco.

### Model

Responsável pela representação dos dados:

```text
Transacao
```

### DAO

Responsável pelas operações no banco de dados:

```text
TransacaoDAO
```

Inclui operações de:

* INSERT
* SELECT
* UPDATE
* DELETE

### Database

Responsável pela conexão com o MySQL:

```text
Conexao
```

### Controller

Responsável pela interação entre a interface JavaFX e as funcionalidades do sistema:

```text
FinTrackController
```

### View

A interface gráfica é construída utilizando:

```text
fintrack.fxml
```

---

# Banco de dados

O projeto utiliza:

```text
MySQL
```

Banco:

```text
fintrack
```

Tabela:

```text
transacoes
```

Estrutura:

| Campo     | Tipo          | Descrição                  |
| --------- | ------------- | -------------------------- |
| id        | INT           | Identificador da transação |
| data      | DATE          | Data da transação          |
| descricao | VARCHAR(255)  | Descrição                  |
| valor     | DECIMAL(10,2) | Valor financeiro           |
| tipo      | VARCHAR(20)   | Receita ou Despesa         |

---

# Observações

* O MySQL Server precisa estar em execução para utilizar o sistema.
* As informações cadastradas ficam armazenadas no banco de dados MySQL.
* O projeto não utiliza SQLite.
* As dependências do JavaFX e MySQL são gerenciadas pelo Maven.
* O projeto foi desenvolvido para Java 21.

---

## Autor

**Bruno Oliveira**

Projeto desenvolvido para estudo e prática de:

* Java
* JavaFX
* FXML
* Maven
* JDBC
* MySQL
* Padrão DAO
* Programação orientada a objetos
