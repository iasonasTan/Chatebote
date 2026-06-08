package org.chatebote.service;

import java.net.http.*;
import java.net.URI;
import com.owlike.genson.Genson;
import java.util.Map;
import java.util.List;
import java.io.IOException;

public class MessageService {
    private final String API_KEY;

    public MessageService() {
        API_KEY = System.getenv("GROQ_API_KEY");
    }

    /**
     * This method sends given question to the AI model and returns the answer.
     * @param question Question to the AI as string.
     * @return The answer AI gave as string.
     */
    @SuppressWarnings("unchecked")
    public String ask(String question) {
        Genson genson = new Genson();
        Map<String, Object> payload = Map.of(
            "model", "llama-3.3-70b-versatile",
            "messages", List.of(
                Map.of("role", "user", "content", question)
            )
        );
        String json = genson.serialize(payload);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.groq.com/openai/v1/chat/completions"))
            .header("Authorization", "Bearer " + API_KEY)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();
    
        HttpResponse<String> response = null;
        try {
            response = client.send(
                request, 
                HttpResponse.BodyHandlers.ofString()
            );
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
        client.close();

        if(response != null) {
            Map<String, Object> json_obj = genson.deserialize(response.body(), Map.class);
            List<Map<String, Object>> choices = (List<Map<String, Object>>) json_obj.get("choices");
            Map<String, Object> message = (Map<String, Object>) choices.getFirst().get("message");
            return (String)message.get("content");
        } else {
            return "Error!";
        }
    }

}