# MedCollect Backend

Backend da aplicação MedCollect, desenvolvido com Spring Boot e integrado com Firebase Authentication.

## Pré-requisitos

- Java 17 ou superior
- Maven
- Google Cloud CLI
- Conta no Firebase
- Firebase CLI

## Configuração do Firebase

1. Instale o Firebase CLI:
```bash
npm install -g firebase-tools
```

2. Faça login no Firebase:
```bash
firebase login
```

3. Configure o projeto Firebase:
```bash
firebase init
```

4. Configure o Google Cloud CLI:
```bash
# Instalar o gcloud CLI (Mac)
brew install --cask google-cloud-sdk

# Login no Google Cloud
gcloud auth login

# Selecionar o projeto
gcloud config set project [PROJECT_ID]

# Gerar credenciais de serviço
gcloud iam service-accounts create medcollect-service \
    --description="MedCollect Service Account" \
    --display-name="MedCollect Service"

# Baixar a chave JSON
gcloud iam service-accounts keys create \
    src/main/resources/firebase-service-account.json \
    --iam-account=medcollect-service@[PROJECT_ID].iam.gserviceaccount.com
```

## Configuração do Projeto

1. Clone o repositório:
```bash
git clone [URL_DO_REPOSITORIO]
cd backend
```

2. Configure as variáveis de ambiente no `application.properties`:
```properties
# Firebase
firebase.project.id=[PROJECT_ID]
firebase.service.account.path=classpath:firebase-service-account.json

# H2 Database (desenvolvimento)
spring.datasource.url=jdbc:h2:mem:medcollect
spring.datasource.username=sa
spring.datasource.password=password
```

3. Instale as dependências:
```bash
mvn clean install
```

## Executando o Projeto

1. Inicie a aplicação:
```bash
mvn spring-boot:run
```

2. A API estará disponível em `http://localhost:8080`

## Endpoints

### Usuários

- **Criar usuário**
  - POST `/api/users`
  ```json
  {
    "firebaseUid": "string",
    "name": "string"
  }
  ```

- **Buscar usuário por Firebase UID**
  - GET `/api/users/firebase/{firebaseUid}`

## Roles e Claims

O sistema suporta três tipos de roles:
- `ADMIN`
- `DOCTOR`
- `PATIENT`

Para configurar roles via Firebase:

```bash
# Exemplo de como definir roles via Firebase Admin SDK
firebase functions:shell

# Definir role para um usuário
admin.auth().setCustomUserClaims('user-uid', {roles: 'ADMIN,DOCTOR'})
```

## Banco de Dados

Em desenvolvimento, o projeto usa H2 (banco em memória):
- Console H2: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:medcollect`
- Usuário: `sa`
- Senha: `password`

## Desenvolvimento

### Estrutura do Projeto
```
src/main/java/com/medcollect/api/
├── config/          # Configurações (Firebase, etc)
├── controllers/     # Controllers REST
├── models/          # Entidades JPA
├── repositories/    # Repositórios Spring Data
└── services/        # Lógica de negócio
```

### Compilação
```bash
# Compilar e executar testes
mvn clean verify

# Apenas compilar
mvn clean package
```

## Troubleshooting

1. **Erro de autenticação Firebase**
   - Verifique se o arquivo `firebase-service-account.json` está no local correto
   - Confirme se o Project ID está correto no `application.properties`

2. **Erro de banco de dados**
   - Verifique as configurações do H2 no `application.properties`
   - Confirme se o console H2 está acessível

3. **Erro de CORS**
   - Configure o CORS no `WebConfig` se necessário para seu ambiente de desenvolvimento
