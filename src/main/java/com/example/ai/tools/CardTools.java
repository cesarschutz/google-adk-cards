package com.example.ai.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class CardTools {

    @Tool(name = "blockCard", description = "Bloqueia temporariamente um cartão pelo ID")
    public String blockCard(String cardId) {
        // Exemplo de chamada a microserviço de cartões
        return "Cartão " + cardId + " bloqueado";
    }

    @Tool(name = "unblockCard", description = "Desbloqueia um cartão pelo ID")
    public String unblockCard(String cardId) {
        // Exemplo de chamada a microserviço de cartões
        return "Cartão " + cardId + " desbloqueado";
    }

    @Tool(name = "checkCardLimit", description = "Consulta o limite disponível de um cartão")
    public String checkCardLimit(String cardId) {
        // Exemplo de chamada a microserviço de cartões
        return "Limite disponível do cartão " + cardId + ": R$1000";
    }
}
