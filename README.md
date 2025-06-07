# agent-visa-unicred-sdk

> Um agente para operações de cartão Visa Unicred usando o Google ADK. 💳🤖

---

## 🔍 Sobre

Este projeto demonstra como **criar e executar** um Multitool Agent em Java usando o Google ADK para gerenciar operações de cartão Visa Unicred.  
Funcionalidades principais:
- Consultar informações do cartão (saldos, limites)
- Bloquear e desbloquear cartão
- Consultar fatura e transações

---

## 🚀 Quickstart

### Pré-requisitos

- **Java 11+**
- **Maven 3.6+**
- **API Key do Google** (para Google ADK)
- **Endpoints da Unicred** (Swagger ou documentação da API)

### Configuração de ambiente

Defina as variáveis antes de rodar:

```bash
export GOOGLE_GENAI_USE_VERTEXAI=FALSE
export GOOGLE_API_KEY="PASTE_YOUR_ACTUAL_API_KEY_HERE"
```

### Passo a passo

1. **Clone o repositório**
   ```bash
   git clone https://github.com/seu-usuario/agent-visa-unicred-sdk.git
   cd agent-visa-unicred-sdk
   ```
2. **Compile e execute**
   ```bash
   mvn clean install
   mvn exec:java      -Dexec.mainClass="com.google.adk.web.AdkWebServer"      -Dexec.args="--adk.agents.source-dir=src/main/java"      -Dexec.classpathScope="compile"
   ```
3. **Acesse a GUI**  
   Abra no navegador:
   ```text
   http://localhost:8080
   ```

---

## 📂 Estrutura do Projeto

```
agent-visa-unicred-sdk/
│
├── src/
│   └── main/
│       ├── java/         # Códigos-fonte dos agentes
│       └── resources/    # Configs e assets (ex.: Swagger da Unicred)
│
├── pom.xml               # Build e dependências Maven
└── README.md             # Este arquivo
```

---

## 🛠️ Plano de Ação (TODO)

- [ ] Criar consulta de cartão, bloqueio e desbloqueio
- [ ] Criar chamadas de endpoint
- [ ] Criar integração com frontend
- [ ] Utilizar IA local
- [ ] Criar gerador de código, que gere um agent a partir de um swager.json

> **Pergunta provocadora:** e se integrássemos notificações automáticas de bloqueio via Slack ou Telegram? 🚀

---

## 🤝 Como Contribuir

1. Fork este repositório
2. Crie uma branch:
   ```bash
   git checkout -b feature/nova-funcionalidade
   ```
3. Commit das mudanças:
   ```bash
   git commit -m "Descrição da feature"
   ```
4. Abra um Pull Request

---

## 📄 Licença

MIT © 2025 — Inove, compartilhe e ajude a comunidade!  
