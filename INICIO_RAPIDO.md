# 🚀 Início Rápido - MyFinances Dashboard

Guia completo para executar a aplicação MyFinances Dashboard em sua máquina.

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- ✅ **Java 21** ou superior ([Download](https://www.oracle.com/java/technologies/downloads/))
- ✅ **Node.js v20.10.0** ou superior ([Download](https://nodejs.org/))
- ✅ **PostgreSQL** ([Download](https://www.postgresql.org/download/))
- ✅ **npm** (vem com Node.js)

### Verificar Instalações

```bash
# Verificar Java
java -version

# Verificar Node.js
node -version

# Verificar npm
npm -version

# Verificar PostgreSQL
psql --version
```

## 🗄️ Passo 1: Configurar o Banco de Dados

### 1.1 Criar o Banco de Dados

Abra o terminal PostgreSQL (psql) ou use uma ferramenta como pgAdmin:

```sql
CREATE DATABASE myfinances;
```

### 1.2 Verificar Credenciais

Abra o arquivo `src/main/resources/application.properties` e verifique/ajuste as credenciais:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/myfinances
spring.datasource.username=postgres
spring.datasource.password=postgres
```

**Importante:** Ajuste o `username` e `password` conforme suas configurações do PostgreSQL.

### 1.3 Migrações Automáticas

Não se preocupe com as tabelas! O Flyway criará automaticamente todas as tabelas necessárias quando você iniciar o backend pela primeira vez.

## 🔧 Passo 2: Executar o Backend (API Spring Boot)

### 2.1 Navegar até a Raiz do Projeto

```bash
cd /Users/knitt/IdeaProjects/Scrap/Myfinances1
```

### 2.2 Compilar o Projeto

```bash
# No macOS/Linux
./gradlew build

# No Windows
gradlew.bat build
```

**Nota:** A primeira compilação pode demorar alguns minutos enquanto o Gradle baixa as dependências.

### 2.3 Executar o Backend

```bash
# No macOS/Linux
./gradlew bootRun

# No Windows
gradlew.bat bootRun
```

### 2.4 Verificar se o Backend Está Rodando

Você verá mensagens no console indicando que a aplicação iniciou. Procure por algo como:

```
Started MyFinancesDashboardApplication in X.XXX seconds
```

O backend estará disponível em: **http://localhost:8080**

**Teste rápido:** Abra o navegador e acesse:
- http://localhost:8080/bancos (deve retornar uma lista vazia `[]`)

## 🎨 Passo 3: Executar o Frontend (Svelte)

### 3.1 Abrir um Novo Terminal

**Importante:** Mantenha o terminal do backend rodando e abra um **novo terminal**.

### 3.2 Navegar até o Diretório do Frontend

```bash
cd /Users/knitt/IdeaProjects/Scrap/Myfinances1/frontend-app
```

### 3.3 Instalar Dependências (Primeira Vez)

```bash
npm install
```

**Nota:** Este comando só precisa ser executado uma vez, ou quando houver novas dependências.

### 3.4 Iniciar o Servidor de Desenvolvimento

```bash
npm run dev
```

### 3.5 Acessar a Aplicação

Você verá uma mensagem como:

```
  VITE v5.0.0  ready in XXX ms

  ➜  Local:   http://localhost:5173/
  ➜  Network: use --host to expose
```

Abra seu navegador e acesse: **http://localhost:5173**

## 🎉 Pronto! A Aplicação Está Rodando

Você deverá ver o dashboard do MyFinances com:

- 📊 **Painel** - Visão geral das finanças
- 💰 **Despesas** - Gerenciar despesas
- 📈 **Investimentos** - Acompanhar investimentos
- 🎯 **Metas** - Definir e monitorar metas
- 📅 **Planejamento** - Visão do planejamento financeiro
- 💳 **Cartões** - Gerenciar cartões
- 🏦 **Bancos** - Gerenciar bancos
- 💵 **Receitas** - Registrar receitas

## 📝 Resumo dos Comandos

### Terminal 1 - Backend
```bash
cd /Users/knitt/IdeaProjects/Scrap/Myfinances1
./gradlew bootRun
```

### Terminal 2 - Frontend
```bash
cd /Users/knitt/IdeaProjects/Scrap/Myfinances1/frontend-app
npm run dev
```

## 🔄 Fluxo de Trabalho Diário

### Iniciar a Aplicação

1. **Iniciar PostgreSQL** (se não estiver rodando automaticamente)
2. **Terminal 1:** Executar o backend com `./gradlew bootRun`
3. **Terminal 2:** Executar o frontend com `npm run dev`
4. **Navegador:** Acessar http://localhost:5173

### Parar a Aplicação

1. **Frontend:** Pressione `Ctrl + C` no terminal do frontend
2. **Backend:** Pressione `Ctrl + C` no terminal do backend

## 🐛 Solução de Problemas Comuns

### Problema: "Port 8080 is already in use"

**Solução:** Outra aplicação está usando a porta 8080.

```bash
# Encontrar o processo usando a porta 8080
lsof -i :8080

# Matar o processo (substitua PID pelo número retornado)
kill -9 PID
```

### Problema: "Port 5173 is already in use"

**Solução:** Outra aplicação está usando a porta 5173.

```bash
# Encontrar o processo usando a porta 5173
lsof -i :5173

# Matar o processo
kill -9 PID
```

### Problema: "Connection refused" ao acessar o banco

**Solução:** PostgreSQL não está rodando.

```bash
# Iniciar PostgreSQL (macOS com Homebrew)
brew services start postgresql

# Verificar status
brew services list
```

### Problema: "FATAL: password authentication failed"

**Solução:** Credenciais incorretas no `application.properties`.

1. Verifique seu usuário e senha do PostgreSQL
2. Atualize o arquivo `src/main/resources/application.properties`
3. Reinicie o backend

### Problema: Frontend não conecta com o Backend

**Solução:** Certifique-se de que:

1. O backend está rodando em http://localhost:8080
2. Você pode acessar http://localhost:8080/bancos no navegador
3. O frontend está configurado corretamente no `vite.config.js`

### Problema: "npm install" falha

**Solução:** Limpe o cache do npm e tente novamente.

```bash
npm cache clean --force
rm -rf node_modules package-lock.json
npm install
```

## 📊 Testando a Aplicação

### 1. Criar um Banco

1. Acesse **Bancos** no menu lateral
2. Clique em **+ Novo Banco**
3. Preencha:
   - Nome: "Banco do Brasil"
   - Código: "001"
4. Clique em **Salvar**

### 2. Criar um Cartão

1. Acesse **Cartões** no menu lateral
2. Clique em **+ Novo Cartão**
3. Preencha os dados do cartão
4. Selecione o banco criado anteriormente
5. Clique em **Salvar**

### 3. Registrar uma Receita

1. Acesse **Receitas** no menu lateral
2. Clique em **+ Nova Receita**
3. Preencha:
   - Nome: "Salário"
   - Valor: 5000
   - Data: Data atual
4. Clique em **Salvar**

### 4. Criar uma Despesa

1. Acesse **Despesas** no menu lateral
2. Clique em **+ Nova Despesa**
3. Preencha os dados
4. Selecione o cartão criado
5. Clique em **Salvar**

### 5. Definir uma Meta

1. Acesse **Metas** no menu lateral
2. Clique em **+ Nova Meta**
3. Preencha:
   - Nome: "Viagem"
   - Valor Objetivo: 10000
   - Valor Alcançado: 2000
   - Descrição: "Viagem para Europa"
4. Clique em **Salvar**

### 6. Verificar o Planejamento

1. Acesse **Planejamento** no menu lateral
2. Veja o resumo financeiro com:
   - Receita Total
   - Despesas Totais
   - Poupança
   - Saldo Livre

## 🎯 Próximos Passos

Agora que a aplicação está rodando, você pode:

- ✅ Explorar todas as funcionalidades
- ✅ Adicionar seus dados financeiros reais
- ✅ Acompanhar suas metas e investimentos
- ✅ Visualizar seu planejamento financeiro

## 📚 Documentação Adicional

- **README.md** - Documentação completa do projeto
- **frontend-app/README.md** - Documentação específica do frontend
- **src/main/resources/db/migration/** - Scripts de migração do banco de dados

## 💡 Dicas

1. **Backup Regular:** Faça backup do banco de dados regularmente
2. **Dados de Teste:** Use dados fictícios primeiro para se familiarizar
3. **Console do Navegador:** Abra o console (F12) para ver logs de erro
4. **Logs do Backend:** Monitore o terminal do backend para erros da API

## 🆘 Precisa de Ajuda?

Se encontrar problemas:

1. Verifique os logs no terminal do backend
2. Verifique o console do navegador (F12)
3. Certifique-se de que todas as dependências estão instaladas
4. Verifique se o PostgreSQL está rodando
5. Confirme que as portas 8080 e 5173 estão livres

---

**Desenvolvido com ❤️ usando Spring Boot e Svelte**

