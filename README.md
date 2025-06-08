# google-adk-cards

> Um agente para operações de cartão usando o Google ADK. 💳🤖

---

## 🔍 Sobre

Este projeto demonstra como **criar e executar** um Multitool Agent em Java usando o Google ADK para gerenciar operações de cartão Visa.  
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

### Configuração de ambiente

Defina as variáveis antes de rodar:

```bash
export GOOGLE_GENAI_USE_VERTEXAI=FALSE
export GOOGLE_API_KEY="PASTE_YOUR_ACTUAL_API_KEY_HERE"
```

### Passo a passo

1. **Clone o repositório**
   ```bash
   git clone https://github.com/cesarschutz/google-adk-cards.git
   cd google-adk-cards
   ```
2. **Compile e execute**
   ```bash
   mvn clean package
   mvn exec:java
   ```
3. **Acesse a GUI**  
   Abra no navegador:
   ```text
   http://localhost:8080
   ```

---

## 📂 Estrutura do Projeto

```
google-adk-cards/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/adk/cards  # Pacotes de agentes e modelos
│       └── resources/    # Configs e assets
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
