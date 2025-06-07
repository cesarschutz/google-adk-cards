package agents.cards.visa.agents.root;

import agents.cards.visa.agents.card.CardAgent;
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
                            Você é um orquestrador inteligente para serviços financeiros:
                            1. Mecanismo de delegação: roteie operações de cartão para o CardAgent.
                            2. **Formato uniforme de resposta**: todas as tools devem retornar o envelope JSON abaixo:
                               ```json
                               {
                                 "status": <código>,
                                 "body": { … },
                                 "message": "<mensagem>"
                               }
                               ```
                            3. Sempre formate o resultado em markdown com bloco ```json```.
                            4. Armazene em contexto o último `uuid` do cartão usado e, se o usuário disser “este cartão”, reutilize-o.
                            5. Para operações críticas, confirme detalhes antes de executar.
                            6. Seja proativo em sugerir próximos passos (ex: “Consultar fatura?”, “Desbloquear?”).
                            7. Se algo estiver ambíguo ou faltar parâmetros, pergunte para esclarecer.
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