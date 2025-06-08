package com.example.adk.cards.card;

import com.example.adk.cards.card.model.CancelCardDto;
import com.example.adk.cards.card.model.CardDto;
import com.example.adk.cards.shared.ApiResponse;
import com.google.adk.agents.BaseAgent;
import com.google.adk.agents.LlmAgent;
import com.google.adk.tools.Annotations;
import com.google.adk.tools.FunctionTool;

/**
 * Agent responsible for card operations such as block, unblock and cancel.
 */

public class CardAgent {

    private static final CardService SERVICE = new CardServiceStub();

    public static BaseAgent createAgent() {
        return LlmAgent.builder()
                .name("card_agent")
                .model("gemini-2.0-flash")
                .description("Agente especialista em cartões: consulta, bloqueio, desbloqueio e cancelamento.")
                .instruction("""
                        Você é o agente encarregado de executar operações de cartão de crédito. \s
                        1. Receba o `uuid` e demais parâmetros; se faltar algo, pergunte com educação: “Qual o UUID ou que dado está em falta?” \s
                        2. Use os códigos HTTP (200, 404 etc.) apenas internamente; **não** exiba o status para o usuário. \s
                        3. Ao retornar dados, envie só o objeto JSON do corpo (se houver). Se for só texto, responda em linguagem natural, sem usar JSON. \s
                        4. Para ações críticas (bloqueio, cancelamento), confirme sempre os detalhes antes de executar: peça motivo e um “Sim/Não”. \s
                        5. Armazene o último `uuid` usado e interprete “este cartão” para reutilizá-lo automaticamente. \s
                        6. Depois de cada operação, sugira duas próximas ações no mesmo estilo acolhedor: \s
                           - **Consulta** → “Bloquear este cartão?” / “Ver fatura?” \s
                           - **Bloqueio/Desbloqueio** → “Consultar status deste cartão?” / “Ver fatura?” \s
                        7. Se algo estiver vago, questione gentilmente: “Você quis dizer o cartão com final 0444?” \s
                        8. Mantenha um tom coloquial, encorajador, prático e descontraído, com pitadas de humor.
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
        return SERVICE.block(uuid);
    }

    @Annotations.Schema(description = "Desbloqueia um cartão")
    public static ApiResponse<String> unblock(
            @Annotations.Schema(description = "UUID do cartão") String uuid
    ) {
        return SERVICE.unblock(uuid);
    }

    @Annotations.Schema(description = "Consulta dados de um cartão")
    public static ApiResponse<CardDto> get(
            @Annotations.Schema(description = "UUID do cartão") String uuid
    ) {
        return SERVICE.get(uuid);
    }

    @Annotations.Schema(description = "Cancela um cartão")
    public static ApiResponse<String> cancel(
            @Annotations.Schema(description = "Payload para cancelar cartão") CancelCardDto dto
    ) {
        return SERVICE.cancel(dto);
    }
}