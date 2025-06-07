package agents.cards.visa.agents.card;

import agents.cards.visa.agents.card.model.CancelCardDto;
import agents.cards.visa.agents.card.model.CardDto;
import agents.cards.visa.agents.shared.ApiResponse;
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

public class CardAgent {

    public static BaseAgent createAgent() {
        return LlmAgent.builder()
                .name("card_agent")
                .model("gemini-2.0-flash")
                .description("Agente especialista em cartões: consulta, bloqueio, desbloqueio e cancelamento.")
                .instruction("""
                **Formato de retorno**: sempre use este envelope em bloco ```json
                {
                  "status": <código>,
                  "body": { … },
                  "message": "<mensagem>"
                }
                ```
                • `status`: código HTTP ou de negócio  
                • `body`: DTO específico (CardDto, BlockCardResponse…)  
                • `message`: texto complementar  
                • Armazene o último `uuid` em contexto para referenciar “este cartão”.  
                • Confirme operações críticas e questione ambiguidades.
            """)
                .tools(
                        FunctionTool.create(CardAgent.class, "block"),
                        FunctionTool.create(CardAgent.class, "unblock"),
                        FunctionTool.create(CardAgent.class, "get"),
                        FunctionTool.create(CardAgent.class, "cancel")
                )
                .build();
    }

    @Annotations.Schema(description = "Bloqueia um cartão")
    public static ApiResponse<String> block(
            @Annotations.Schema(description = "UUID do cartão") String uuid
    ) {
        // 🔧 Aqui você chama sua API REST e preenche o DTO com a resposta real
        String body = "cartão bloqueado";
        return new ApiResponse<>(200, body, "Cartão " + uuid + " bloqueado com sucesso.");
    }

    @Annotations.Schema(description = "Desbloqueia um cartão")
    public static ApiResponse<String> unblock(
            @Annotations.Schema(description = "UUID do cartão") String uuid
    ) {
        String body = "cartão desbloqueado";
        return new ApiResponse<>(200, body, "Cartão " + uuid + " desbloqueado com sucesso.");
    }

    @Annotations.Schema(description = "Consulta dados de um cartão")
    public static ApiResponse<CardDto> get(
            @Annotations.Schema(description = "UUID do cartão") String uuid
    ) {
        // 🔧 Aqui você chama sua API REST e obtém um CardDto real
        CardDto dto = new CardDto(uuid, "4321", 10000L, 2000L, 8000L, 0L, "7345 7485 8347 2486");
        return new ApiResponse<>(200, dto, "Consulta do cartão " + uuid + " realizada.");
    }

    @Annotations.Schema(description = "Cancela um cartão")
    public static ApiResponse<String> cancel(
            @Annotations.Schema(description = "Payload para cancelar cartão") CancelCardDto dto
    ) {
        // 🔧 Aqui você chama sua API REST para cancelar
        String body = "cartão bloqueado";

        return new ApiResponse<>(200, body, "Cartão " + dto.getUuid() + " cancelado.");
    }
}