package com.example.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.example.ai.tools.CardTools;
import com.example.ai.tools.InvoiceTools;

@Service
public class ChatAgentService {

    private final ChatClient chatClient;
    private final CardTools cardTools;
    private final InvoiceTools invoiceTools;

    public ChatAgentService(ChatClient chatClient, CardTools cardTools, InvoiceTools invoiceTools) {
        this.chatClient = chatClient;
        this.cardTools = cardTools;
        this.invoiceTools = invoiceTools;
    }

    public String chat(String message) {
        return chatClient.prompt()
                .system("Você é um roteador de agentes que decide qual ferramenta usar para responder perguntas sobre cartões ou faturas.")
                .user(message)
                .tools(cardTools, invoiceTools)
                .call()
                .content();
    }
}
