package com.example.adk.cards.root;

import com.example.adk.cards.card.CardAgent;
import com.google.adk.agents.BaseAgent;
import com.google.adk.agents.LlmAgent;
import com.google.adk.events.Event;
import com.google.adk.runner.InMemoryRunner;
import com.google.adk.sessions.Session;
import com.google.genai.types.Content;
import com.google.genai.types.Part;
import io.reactivex.rxjava3.core.Flowable;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class RootAgent {

    private static String USER_ID = "student";
    private static String NAME = "multi_tool_agent";

    public static BaseAgent ROOT_AGENT = initAgent();

    public static BaseAgent initAgent() {
        return LlmAgent.builder()
                .name("financial_services_orchestrator")
                .model("gemini-2.0-flash")
                .description("Orquestrador principal para serviços financeiros: cartões e faturas.")
                .instruction("""
                           Você é o orquestrador principal para serviços financeiros (cartões e faturas). \s
                           - Sempre solicite o `uuid` do cartão de forma gentil (“Pode me passar o UUID do cartão, por favor?”) e, a partir daí, memorize e reutilize esse valor sempre que o usuário disser “este cartão”. \s
                           - Use os códigos HTTP (200, 404 etc.) apenas internamente para guiar sua lógica; **não** mostre o status ao usuário. \s
                           - Quando for devolver dados, entregue somente o objeto JSON do `body` (por ex.: os campos do cartão ou da fatura). Se for só uma mensagem de texto, responda em linguagem natural, sem blocos JSON. \s
                           - Antes de qualquer operação crítica (bloquear, cancelar), peça confirmação e motivo: \s
                           “Você tem certeza que quer bloquear o cartão XXXX? Qual o motivo?” \s
                           - Após cada ação concluída, sugira sempre duas próximas interações relevantes, em tom amigável e descontraído: \s
                           - **Consulta** → “Quer bloquear este cartão?” e “Quer ver a fatura?” \s
                           - **Bloqueio** → “Deseja desbloquear mais tarde?” e “Quer ver a fatura?” \s
                           - **Cancelamento** → “Quer emitir uma nova via?” e “Deseja consultar outro cartão?” \s
                           - Sempre que algo ficar ambíguo, questione de forma simpática: “Você se refere ao último cartão usado?” \s
                           - Mantenha um tom coloquial, encorajador, descontraído e prático, com um leve toque de humor.
                        """)
                .subAgents(Arrays.asList(
                        CardAgent.createAgent()
                ))
                .build();
    }

    public static void main(String[] args) throws Exception {
        InMemoryRunner runner = new InMemoryRunner(ROOT_AGENT);

        final Session session = runner.sessionService().createSession(NAME, USER_ID).blockingGet();

        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            while (true) {
                System.out.print("\nYou > ");
                String userInput = scanner.nextLine();

                if ("quit".equalsIgnoreCase(userInput)) {
                    break;
                }

                Content userMsg = Content.fromParts(Part.fromText(userInput));
                Flowable<Event> events = runner.runAsync(USER_ID, session.id(), userMsg);

                System.out.print("\nAgent > ");
                events.blockingForEach(event -> System.out.println(event.stringifyContent()));
            }
        }
    }
}