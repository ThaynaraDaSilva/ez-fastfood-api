# EZ-Fast-Food - Clean Architecture e Kubernetes 

## Contextualização

O **EZ-Fast-Food API** é uma solução desenvolvida para uma lanchonete em expansão, utilizando a arquitetura limpa (*clean architecture*) para assegurar uma separação clara entre a lógica de negócios e os detalhes de infraestrutura. A API é implantada com AWS EKS.

## Desenho de arquitetura
![desenho-arquitetura-aws](https://github.com/user-attachments/assets/0ec3731b-c3d8-4db6-a890-15ee26e05b00)

## Apresentação da arquitetura: 
https://www.youtube.com/watch?v=MhPpoUZhlFs

## Estrutura de diretórios do projeto
```
br.com.fiap.ez.fastfood
├── adapters
│   ├── in
│   │   └── controller
│   └── out
│       └── repository
├── application
│   ├── usecases
│   └── dto
├── domain
│   ├── model
│   └── repository
├── frameworks
│   ├── config
│   └── exception
├── infrastructure
│   ├── config
│   ├── mapper
│   └── persistence
├── APIApplication.java
k8s/                      # Arquivos de manifesto Kubernetes
postman-jmeter/            # Collection para testes no Postman e Apache JMeter
```

## Modelagem do banco de dados

![database-ez-fastfood](https://github.com/user-attachments/assets/5e32efb7-7d2d-4a72-b56d-24cb6d74b3fd)

## Pré requisitos:
Criar a infraestrutura nessa ordem:

1. Network: https://github.com/ThaynaraDaSilva/ez-fastfood-network
2. RDS: https://github.com/ThaynaraDaSilva/ez-fastfood-database
3. EKS:https://github.com/ThaynaraDaSilva/ez-fastfood-eks
4. Lambda: https://github.com/ThaynaraDaSilva/ez-fastfood-authentication 
5. APIs: https://github.com/ThaynaraDaSilva/ez-fastfood-api

## Tecnologias utilizadas

- Java 17
- Spring Boot 3.3.1
- Hibernate
- RDS PostgreSQL 13.17
- DockerHub (https://hub.docker.com/repository/docker/dasilvathaynara/ez-fast-food-api/general)
- Kubernetes
- OpenApi

## Endpoints

### Cliente
- **Cadastro do cliente** (http://localhost:30000/customers/create-new)
- **Identificação do cliente via CPF** (http://localhost:30000/customers/find-by-cpf/{cpf})
- **Login** (http://localhost:30000/customers/customers/login)
  
### Produto
- **Criar produto** (http://localhost:30000/products/create-new)
- **Editar produto** (http://localhost:30000/products/update-by-id/{id})
- **Remover produto** (http://localhost:30000/products/delete-by-id/{id})
  - regra 1: não é permitido remover um produto que está associado a pedido.
- **Buscar produtos por categoria** (http://localhost:30000/products/find-by-category-id/{id})

### Pedido
- **Fake checkout** (http://localhost:30000/orders/checkout): envio dos produtos escolhidos para a fila (checkout é a finalização do pedido).
- **Listar pedidos não finalizados** (http://localhost:30000/orders/list-uncompleted-orders)
  - regra 1: considerar somente pedidos com os status **READY** , **IN_PREPARATION** e **RECEIVED**.
  - regra 2: pedidos mais antigos devem aparecer primeiro.
  
### Pagamento (Webhook)
- **Atualizar status do pagamento** (http://localhost:30000/payments/webhook/status):
  - regra 1: somente permitido atualizar o status para **OK** ou **CANCELLED** se o pagamento estiver com o status: **PENDING**

Esses endpoints foram implementados para facilitar as validações. 
### Cliente
- **Filtrar cliente por CPF** (http://localhost:30000/customers/find-by-cpf/{cpf})
- **Listar todos os clientes** (http://localhost:30000/customers/list-all) 

### Produto
- **Listar todos os produtos** (http://localhost:30000/products/list-all)

### Pedidos
- **Listar todos os pedidos** (http://localhost:30000/orders/list-all)

### Pagamento
- **Verificar status do pagamento** (http://localhost:30000/payments/check-status?paymentId={id})

### Como compilar o projeto (caso necessário)
```sh
git clone https://github.com/ThaynaraDaSilva/ez-fast-food-clean-architecture.git
cd ez-fast-food-clean-architecture
### Compilação local
mvn clean package -Pdev
## Compilação para publicação com K8s
### Desta forma o 'application.properties' terá parametros que serão injetados com valores no momento de subir os pods.
mvn clean package -Pprd
```

## Documentação das APIs

1. A documentação das APIs está disponível no Swagger e pode ser acessada em http://localhost:30000/swagger-ui/index.html.
2. Foi disponibilizado **duas collections** do **postman**, no diretório postman-jmeter/ deste repositório.
- Collection da jornada: em ordem de execução para apoio na simulação de pedido.
- Collection geral: em ordem de execução para validar todas as funcionalidades.

OBS...: substituir o localhost por novo host do AWS EKS.

### Validação da API com Postman

### 1. Baixar a Collection:
```sh
1.1. Navegue até o diretório e baixe o arquivo .json correspondente à collection de endpoints.
```

### 2. Importar a collection no postman:
```sh
2.1. Inicie o postman.
2.2. No canto superior esquerdo, clique em import.
2.3. Arraste e solte o arquivo .json ou selecione-o manualmente para importar a collection.
2.4. Substituir o localhost por novo host da AWS EKS.
```

**Observações**:
1. Já deixamos uma massa de dados automatizada para que você somente execute os endpoints.
2. O fluxo mapeado não representa a navegação via frontend. Há endpoints tais como 'listar clientes' que estão presentes nesta jornada do backend apenas para apoiar o usuário final na validação.
3. Substituir o localhost por novo host do AWS EKS.

##  Apache JMeter
Essa ferramenta foi utilizada para estressar a API durante nossas validações de HPA.

- Link para download: https://dlcdn.apache.org//jmeter/binaries/apache-jmeter-5.6.3.zip
- Arquivo "ez-fast-food-api.jmx" disponível no diretório: postman-jmeter/ deste repositório.

## Desenvolvido por:
@tchfer : RM357414<br>
@ThaynaraDaSilva : RM357418<br>
