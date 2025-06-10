# Spring AI Multi-Agent Example

Este projeto demonstra uma aplicação simples utilizando **Spring AI** com três agentes:

1. **CardAgent** – ferramentas para operações de cartão (bloqueio, desbloqueio e consulta de limite).
2. **InvoiceAgent** – ferramentas para operações de faturas (detalhes e pagamento).
3. **RouterAgent** – recebe as mensagens do usuário e utiliza o modelo para escolher a melhor ferramenta para responder.

Execute o endpoint `/chat` enviando um texto para conversar com a IA. O modelo escolherá entre as ferramentas disponíveis para responder.

## Requisitos

- Java 21
- Maven
- Definir a variável `OPENAI_API_KEY` com sua chave da API OpenAI.

Para executar os testes:

```bash
mvn test
```
