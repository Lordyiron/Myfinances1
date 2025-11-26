# MyFinances Dashboard

Sistema completo de gestão financeira com backend Spring Boot e frontend Svelte.

## Visão Geral

MyFinances Dashboard é uma aplicação abrangente de gestão de finanças pessoais que ajuda você a rastrear despesas, gerenciar investimentos, definir metas financeiras e monitorar sua saúde financeira geral.

## Funcionalidades

### Backend (Spring Boot)
- ✅ API RESTful com operações CRUD completas
- ✅ Banco de dados PostgreSQL com migrações Flyway
- ✅ JPA/Hibernate para persistência de dados
- ✅ Gerenciamento de entidades para:
  - Bancos
  - Cartões
  - Despesas
  - Metas
  - Investimentos
  - Aplicações de Investimento
  - Receitas
  - Planejamento Financeiro

### Frontend (Svelte)
- ✅ Interface moderna e responsiva com Svelte 4
- ✅ Roteamento client-side com svelte-routing
- ✅ Integração de dados em tempo real com a API backend
- ✅ Dashboards e gráficos interativos
- ✅ Formulários baseados em modais para entrada de dados
- ✅ Acompanhamento de progresso para metas
- ✅ Indicadores de saúde financeira

## Stack Tecnológico

### Backend
- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **PostgreSQL**
- **Flyway** (Migrações de banco de dados)
- **Gradle** (Ferramenta de build)

### Frontend
- **Svelte 4.2.0**
- **Vite 5.0.0**
- **svelte-routing 2.0.0**
- **CSS Moderno** com fonte Poppins

## Pré-requisitos

- **Java 21** ou superior
- **Node.js v20.10.0** ou superior
- **PostgreSQL** banco de dados
- **npm** (versão mais recente recomendada)

## Configuração do Banco de Dados

1. Crie um banco de dados PostgreSQL chamado `myfinances`:

```sql
CREATE DATABASE myfinances;
```

2. Atualize as credenciais do banco de dados em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/myfinances
spring.datasource.username=postgres
spring.datasource.password=postgres
```

3. O Flyway executará automaticamente as migrações na inicialização da aplicação.

## Configuração do Backend

1. Compile o projeto:

```bash
./gradlew build
```

2. Execute a aplicação Spring Boot:

```bash
./gradlew bootRun
```

A API backend estará disponível em `http://localhost:8080`

## Configuração do Frontend

1. Navegue até o diretório do frontend:

```bash
cd frontend-app
```

2. Instale as dependências:

```bash
npm install
```

3. Inicie o servidor de desenvolvimento:

```bash
npm run dev
```

O frontend estará disponível em `http://localhost:5173`

## Endpoints da API

### Bancos
- `GET /bancos` - Listar todos os bancos
- `GET /bancos/{id}` - Obter banco por ID
- `POST /bancos` - Criar novo banco
- `DELETE /bancos/{id}` - Deletar banco

### Cartões
- `GET /cartoes` - Listar todos os cartões
- `GET /cartoes/{id}` - Obter cartão por ID
- `POST /cartoes` - Criar novo cartão
- `DELETE /cartoes/{id}` - Deletar cartão

### Despesas
- `GET /despesas` - Listar todas as despesas
- `GET /despesas/{id}` - Obter despesa por ID
- `POST /despesas` - Criar nova despesa
- `DELETE /despesas/{id}` - Deletar despesa

### Metas
- `GET /metas` - Listar todas as metas
- `GET /metas/{id}` - Obter meta por ID
- `POST /metas` - Criar/Atualizar meta
- `DELETE /metas/{id}` - Deletar meta

### Investimentos
- `GET /investimentos` - Listar todos os investimentos
- `GET /investimentos/{id}` - Obter investimento por ID
- `POST /investimentos` - Criar novo investimento
- `DELETE /investimentos/{id}` - Deletar investimento

### Aplicações de Investimento
- `GET /investimentos/{id}/aplicacoes` - Listar aplicações de um investimento
- `POST /aplicacoes` - Criar nova aplicação
- `DELETE /aplicacoes/{id}` - Deletar aplicação

### Receitas
- `GET /receitas` - Listar todas as receitas
- `GET /receitas/{id}` - Obter receita por ID
- `POST /receitas` - Criar nova receita
- `DELETE /receitas/{id}` - Deletar receita

### Planejamento
- `GET /planejamento` - Obter resumo do planejamento financeiro

## Estrutura do Projeto

```
Myfinances1/
├── src/main/java/com/jhony/myfinancesdashboard/
│   ├── controladores/       # Controladores REST
│   ├── servicos/            # Serviços de lógica de negócio
│   ├── repositorios/        # Repositórios JPA
│   ├── modelos/             # Modelos de entidade
│   └── dtos/                # Objetos de Transferência de Dados
├── src/main/resources/
│   ├── application.properties
│   └── db/migration/        # Migrações Flyway
├── frontend-app/
│   ├── src/
│   │   ├── lib/
│   │   │   ├── components/  # Componentes Svelte reutilizáveis
│   │   │   ├── services/    # Camada de serviço da API
│   │   │   └── utils.js     # Funções utilitárias
│   │   ├── routes/          # Componentes de página
│   │   ├── App.svelte       # Componente principal da aplicação
│   │   ├── main.js          # Ponto de entrada
│   │   └── app.css          # Estilos globais
│   ├── index.html
│   ├── vite.config.js
│   └── package.json
├── build.gradle
└── README.md
```

## Fluxo de Trabalho de Desenvolvimento

1. **Iniciar o backend**: Execute `./gradlew bootRun` na raiz do projeto
2. **Iniciar o frontend**: Execute `npm run dev` no diretório `frontend-app`
3. **Acessar a aplicação**: Abra `http://localhost:5173` no seu navegador

## Compilação para Produção

### Backend

```bash
./gradlew build
java -jar build/libs/MyFinancesDashboard-0.0.1-SNAPSHOT.jar
```

### Frontend

```bash
cd frontend-app
npm run build
```

A build de produção estará em `frontend-app/dist/`

## Modelos de Dados

### Banco
- `id`: Long
- `nome`: String
- `codigo`: String

### Cartao (Cartão)
- `id`: Long
- `bancoEmissor`: Banco
- `nome`: String
- `tipoPagamentoCartao`: Enum (DEBITO, CREDITO, DEBITO_E_CREDITO)
- `limite`: BigDecimal
- `bandeira`: String
- `numero`: String
- `diaVencimentoFatura`: int

### Despesa
- `id`: Long
- `cartaoUsado`: Cartao
- `descricao`: String
- `valor`: BigDecimal
- `parcelas`: int
- `vencimento`: LocalDate

### Meta
- `id`: Long
- `nome`: String
- `valorObjetivo`: BigDecimal
- `valorAlcancado`: BigDecimal
- `descricao`: String

### Investimento
- `id`: Long
- `nome`: String
- `totalInvestido`: BigDecimal
- `dataCriacao`: LocalDate
- `aplicacoes`: List<AplicacaoInvestimento>

### AplicacaoInvestimento (Aplicação de Investimento)
- `id`: Long
- `investimento`: Investimento
- `valorAplicado`: BigDecimal
- `dataAplicacao`: LocalDate

### Receita
- `id`: Long
- `nome`: String
- `valorReceita`: BigDecimal
- `dataReceita`: LocalDate

## Contribuindo

Este é um projeto de gestão de finanças pessoais. Sinta-se livre para fazer fork e personalizar conforme suas necessidades.

## Licença

Este projeto é para uso educacional e pessoal.

## Observações

- Este é um projeto **sem segurança** - adequado apenas para uso local/pessoal
- As credenciais do banco de dados devem ser atualizadas para seu ambiente
- O frontend usa proxy Vite em desenvolvimento para evitar problemas de CORS
- Todos os valores monetários usam formatação de Real Brasileiro (R$)
- Formatos de data usam o padrão brasileiro (DD/MM/YYYY)

