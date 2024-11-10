# Migração de infraestrutura
- Migração de infra k8s - docker desktop para nuvem AWS.

# Serviços AWS que serão utilizados
- Banco de dadados: AWS RDS - PostgreSQL, Engine xx: https://aws.amazon.com/pt/free/database/?
- AWS API Gateway: https://aws.amazon.com/pt/api-gateway/
- AWS Cognito: https://aws.amazon.com/pt/pm/cognito/?
- AWS Lambda: https://aws.amazon.com/pt/pm/lambda/?
- AWS EKS: https://aws.amazon.com/pt/eks/ 

# Etapas da migração
 - Região : Norte Virginia
## 1. Configuração de VPC
1. Criação de VPC
  name tag: vpc-dev-nvirginia-ezfastfood
  IPv4 CIDR block: 28.0.0.0/16
  https://www.youtube.com/watch?v=ApGz8tpNLgo
  
  
2. Criação de sub-redes

 2. subnet publicas
 2 subnets privadas
 2 zonas de disponibilidade, logo 1 subnet publica e privada para cada

3. Configurar Internet Gateway (IGW)
4. Configurar rotas
5. Configurar NAT Gateway (Opcional)

## 2. Configuração de Segurança

## 3. Configuração do EKS

## 4. Configuração do BD RDS - PostgreSQL

## 5. Configuração do Armazenamento

## 6. Configuração do ECR

## 7. Configuração de ConfigMaps e Secrets

## 8. Ajustar recursos e configurações de autoscaling

## 9. Implantação e testes

## 10. Configurar Monitoramento e Logging

# Serviços e Disponibilidade no AWS Free Tier

| **Serviço**                                  | **Disponível no Free Tier?** | **Detalhes do Free Tier**                                                                                                                                                                                         |
|----------------------------------------------|------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **VPC (Virtual Private Cloud)**              | Sim                          | A criação e uso básico de VPCs, sub-redes, tabelas de rotas e security groups são gratuitos. No entanto, recursos associados, como NAT Gateways e endpoints VPC, podem gerar custos.                               |
| **IAM (Identity and Access Management)**     | Sim                          | O gerenciamento de usuários, grupos, funções e políticas não tem custo.                                                                                                                                           |
| **RDS (Relational Database Service - PostgreSQL)** | Parcialmente Gratuito        | Inclui até 750 horas mensais de uso de instância db.t2.micro **(apenas nos primeiros 12 meses)**. Até 20 GB de armazenamento de banco de dados e 20 GB de backup. Uso além desses limites gera custos.             |
| **EBS (Elastic Block Store)**                | Parcialmente Gratuito        | Inclui 30 GB de armazenamento de EBS (SSD ou magnético). Qualquer uso além desse limite será cobrado.                                                                                                             |
| **ECR (Elastic Container Registry)**         | Parcialmente Gratuito        | Inclui 500 MB de armazenamento por mês gratuitamente. Uso adicional será cobrado.                                                                                                                                 |
| **EKS (Elastic Kubernetes Service)**         | **Não Gratuito**             | O EKS não faz parte do AWS Free Tier. Há uma cobrança por cluster (aproximadamente **US$ 0,10 por hora**) e pelos recursos subjacentes (instâncias EC2).                                                           |
| **EC2 (Elastic Compute Cloud)**              | Parcialmente Gratuito        | Oferece até 750 horas mensais de instâncias t2.micro ou t3.micro **(apenas nos primeiros 12 meses)**. Aplicável para sistemas operacionais específicos.                                                           |
| **API Gateway**                              | Parcialmente Gratuito        | Inclui 1 milhão de chamadas de API por mês gratuitamente **(por 12 meses)**. Uso além desse limite será cobrado.                                                                                                  |
| **Lambda**                                   | Parcialmente Gratuito        | Inclui 1 milhão de solicitações gratuitas por mês e 400.000 GB-segundos de tempo de computação por mês. Uso além desses limites será cobrado.                                                                      |
| **Cognito**                                  | Parcialmente Gratuito        | Até 50.000 usuários ativos mensais (MAUs) para autenticação de usuários. Uso além desse limite será cobrado.                                                                                                      |
| **CloudWatch**                               | Parcialmente Gratuito        | Inclui 10 métricas personalizadas, 5 GB de armazenamento de logs e 3 dashboards. Uso adicional gera custos.                                                                                                       |
| **CodePipeline, CodeBuild, CodeDeploy**      | **Não Gratuitos**            | Esses serviços não estão incluídos no Free Tier e serão cobrados com base no uso.                                                                                                                                 |
| **SQS (Simple Queue Service) e SNS (Simple Notification Service)** | Parcialmente Gratuito | Incluem 1 milhão de solicitações por mês gratuitamente. Uso além desse limite será cobrado.                                                                                                                       |

# Plano de Implementação na AWS

| Etapa | Descrição | Detalhes |
|-------|-----------|----------|
| **1** | Estruturação de Rede e Segurança | - Criar VPC com sub-redes públicas e privadas.<br> - Configurar IAM e Security Groups para controle de acesso. |
| **2** | Configuração do Banco de Dados e Armazenamento | - Configurar AWS RDS (PostgreSQL) na sub-rede privada.<br> - Definir backup e segurança de dados.<br> - Configurar EBS para armazenamento adicional (se necessário). |
| **3** | Configuração do EKS e Serviços Contêinerizados | - Criar o cluster EKS e grupos de nós.<br> - Definir requests/limits de CPU e memória para pods.<br> - Publicar imagens Docker no ECR e configurar HPA no EKS. |
| **4** | Autenticação e API Gateway | - Configurar AWS API Gateway para roteamento.<br> - Configurar AWS Cognito para autenticação de usuários.<br> - Criar função Lambda para autenticação de CPF. |
| **5** | Integração e Serverless | - Implementar funções serverless adicionais, se necessário.<br> - Configurar SQS/SNS para comunicação entre serviços. |
| **6** | Pipeline de CI/CD e Automação | - Configurar pipelines de CI/CD com CodePipeline/CodeBuild.<br> - Aplicar práticas de CI/CD: padrões de commit, revisão de código e testes automatizados. |
| **7** | Monitoramento e Logging | - Configurar CloudWatch para monitoramento e alertas do EKS, API Gateway e funções Lambda. |

---

## Ordem para Requisitos Específicos

| Prioridade | Requisito | Descrição |
|------------|-----------|-----------|
| **1** | Implementar AWS API Gateway | Configurar API Gateway para roteamento de solicitações. |
| **2** | Criar função Lambda para autenticação de CPF | Implementar autenticação usando CPF via Lambda. |
| **3** | Integrar ao serviço de autenticação (Cognito) | Configurar AWS Cognito para identificação e autenticação de usuários. |
| **4** | Aplicar melhores práticas de CI/CD | Padrões de commit, revisão de código e automação de testes. |
| **5** | Criar pipelines de deploy automatizado | Configurar pipelines de deploy para cada repositório. |
| **6** | Melhorar a estrutura do banco de dados | Utilizar RDS PostgreSQL com práticas de segurança e backup. |
| **7** | Implementar serviços serverless | Funções, bancos de dados gerenciáveis e sistema de autenticação. |

---

Este plano em formato de tabela organiza as tarefas por etapas e prioridade, facilitando o acompanhamento da implementação.
