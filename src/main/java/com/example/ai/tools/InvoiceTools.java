package com.example.ai.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class InvoiceTools {

    @Tool(name = "invoiceDetails", description = "Obtém detalhes da fatura atual de um cartão")
    public String invoiceDetails(String cardId) {
        // Exemplo de chamada a microserviço de faturas
        return "Fatura do cartão " + cardId + ": R$500";
    }

    @Tool(name = "payInvoice", description = "Realiza o pagamento da fatura pelo ID do cartão")
    public String payInvoice(String cardId) {
        // Exemplo de chamada a microserviço de faturas
        return "Pagamento da fatura do cartão " + cardId + " realizado";
    }
}
