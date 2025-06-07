package agents.cards.visa.agents.card;

import agents.cards.visa.agents.card.model.CancelCardDto;
import agents.cards.visa.agents.card.model.CardDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.adk.agents.BaseAgent;
import com.google.adk.agents.LlmAgent;
import com.google.adk.tools.Annotations;
import com.google.adk.tools.FunctionTool;
import com.google.adk.tools.Annotations.Schema;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CardAgent {

    public static BaseAgent createAgent() {
        return LlmAgent.builder()
                .name("card_agent")
                .model("gemini-2.0-flash")
                .description("Gerencia cards e card-accounts")
                .instruction("""
                          Você é um agent de cartão.
                          Se faltar parâmetro, solicite.
                          Quando retornar JSON, formate corretamente no corpo da resposta.
                        """)
                .tools(
                        FunctionTool.create(CardAgent.class, "block"),
                        FunctionTool.create(CardAgent.class, "unblock"),
                        FunctionTool.create(CardAgent.class, "get"),
                        FunctionTool.create(CardAgent.class, "cancel"))
                .build();
    }

    @Annotations.Schema(description = "Bloqueia um cartão")
    public static Map<String, String> block(
            @Schema(description = "uuid do cartão para bloquear") String uuid
    ) {
        System.out.println("passou no block");
        return Map.of("message", "Cartão com uuid " + uuid + " bloqueado");
    }

    @Annotations.Schema(description = "Desbloqueia um cartão")
    public static Map<String, String> unblock(
            @Schema(description = "uuid do cartão para desbloquear") String uuid
    ) {
        System.out.println("passou no unblock");
        return Map.of("message", "Cartão com uuid " + uuid + " desbloqueado");
    }

    @Annotations.Schema(description = "Consulta um cartão e o retorno deve ser o cartao apresentado como JSON formatado")
    public static Map<String, Object> get(
            @Schema(description = "uuid do cartão para consultar") String uuid
    ) throws JsonProcessingException {
        //consulta cartão
        CardDto cardDto = new CardDto("123", "4321", 1L, 2L, 5L, 5L, "7345 7485 8347 2486");

        // transforma seu DTO em mapa
        Map<String, Object> cardMap = new ObjectMapper().convertValue(cardDto, new TypeReference<Map<String, Object>>() {
        });

        System.out.println("passou no get: " + cardMap.toString());

        return Map.of("cartao", cardMap);
    }

    @Annotations.Schema(description = "Cancela um cartão")
    public static Map<String, String> cancel(
            @Schema(description = "Payload para cancelar cartão") CancelCardDto dto
    ) {
        System.out.println("passou no cancel");
        StringBuilder msg = new StringBuilder("Cartão com uuid " + dto.getUuid() + " cancelado");
        if (dto.isGerarNovaVia()) msg.append("\nGerada nova via do cartão. Uuid da nova via: 1234567890-0987654321");
        return Map.of("message", msg.toString());
    }
}
