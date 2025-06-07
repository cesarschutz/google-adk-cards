package agents.cards.visa.application;

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
                .name("cards_team")
                .model("gemini-2.0-flash")
                .description("Orquestra agentes internos para gerenciamento de cartões")
                .instruction("""
                          Você é um orquestrador de agentes. 
                          Sempre que usar mais de uma tool, junte as respostas em um único Markdown com emojis. 
                          Formate blocos de JSON com code fences.
                        """)
                .subAgents(Arrays.asList(CardAgent.createAgent()))
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