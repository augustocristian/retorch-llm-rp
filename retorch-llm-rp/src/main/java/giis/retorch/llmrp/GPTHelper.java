package giis.retorch.llmrp;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class GPTHelper {
    static final String PATH_KEY = "CHATGPT_API_KEY";
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public void sendChatGPTRequest(String body, String model, String experimentname) {

        String apiKey = System.getProperty(PATH_KEY) != null ? System.getProperty(PATH_KEY) : System.getenv(PATH_KEY);
        String urlString = "https://api.openai.com/v1/chat/completions";
        HttpURLConnection conn = null;
        try {
            // Create URL and HttpURLConnection objects
            URI uri = new URI(urlString);
            URL url = uri.toURL();
            conn = (HttpURLConnection) url.openConnection();

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
            data.put("messages", messages);
            data.put("temperature", 0.2); // More info at: https://community.openai.com/t/cheat-sheet-mastering-temperature-and-top-p-in-chatgpt-api/172683
            data.put("max_tokens", 5000); // Upper limit for the anwer.
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
                LocalDateTime now = LocalDateTime.now();

                // Define the desired format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-");

                // Format the current date and time
                String formattedDateTime = now.format(formatter);

                putOutputToFile("retorch-llm-rp/src/main/resources/outputs", experimentname + model + formattedDateTime, responseBody);
                conn.disconnect();
            }

        } catch (IOException | URISyntaxException e) {
            log.error(e.getMessage().toString());
            if (conn != null) {
                conn.disconnect();
            }
            System.exit(-1);
        }

    }

    public void putOutputToFile(String filePath, String namePrompt, String output) throws IOException {
        log.debug("Creating the tmp directory to store methods output");
        File dir = new File(filePath);
        if (dir.exists()) {
            log.debug("The directory already exists: {}", dir.getAbsolutePath());
        } else {
            try {
                boolean isCreated = dir.mkdirs();
                if (isCreated) {
                    log.debug("Directory successfully created: {}", dir.getAbsolutePath());
                } else {
                    log.warn("Not able to create the directory: {}", dir.getAbsolutePath());
                }
            } catch (Exception e) {
                log.error("Something wrong happened creating the directory {} the exception stacktrace is:\n {}",
                        dir.getAbsolutePath(), e.getStackTrace());
            }
        }
        try (FileOutputStream outputStream = new FileOutputStream(filePath + "/" + namePrompt + ".txt")) {
            byte[] strToBytes = output.getBytes();
            outputStream.write(strToBytes);
        }
    }
}
