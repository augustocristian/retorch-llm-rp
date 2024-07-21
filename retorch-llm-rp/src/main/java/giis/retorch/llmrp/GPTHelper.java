package giis.retorch.llmrp;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class GPTHelper {
    static final String PATH_KEY = "CHATGPT_API_KEY";
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public void sendChatGPTRequest(String body, String model) {

        String apiKey = System.getProperty(PATH_KEY) != null ? System.getProperty(PATH_KEY) : System.getenv(PATH_KEY);
        String urlString = "https://api.openai.com/v1/chat/completions";
        try {
            // Create URL and HttpURLConnection objects
            URI uri = new URI(urlString);
            URL url = uri.toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set request method and headers
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + apiKey);
            conn.setDoOutput(true);

            // Create JSON request body
            JSONObject data = new JSONObject();
            data.put("model", model);

            // Create the message content
            JSONObject messageContent = new JSONObject();
            messageContent.put("type", "text");
            messageContent.put("text", body);

            // Create the user message
            JSONObject userMessage = new JSONObject();
            userMessage.put("role", "user");
            userMessage.put("content", new JSONArray().put(messageContent));

            // Add the user message to the messages array
            JSONArray messages = new JSONArray();
            messages.put(userMessage);

            // Create JSON request body
            data.put("messages", new org.json.JSONArray());
            data.put("temperature", 1);
            data.put("max_tokens", 256);
            data.put("top_p", 1);
            data.put("frequency_penalty", 0);
            data.put("presence_penalty", 0);

            // Write JSON data to output stream
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = data.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Get response
            int responseCode = conn.getResponseCode();
            log.debug("Response Code:{} ", responseCode);

            try (Scanner scanner = new Scanner(conn.getInputStream())) {
                String responseBody = scanner.useDelimiter("\\A").next();
                log.debug("Response Body: {}", responseBody);
            }

        } catch (IOException | URISyntaxException e) {
            log.error(Arrays.toString(e.getStackTrace()));
            System.exit(-1);
        }

    }
}
