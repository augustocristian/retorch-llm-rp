package giis.retorch.llmrp;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class UserStoriesHelper {

    public UserStoriesHelper() {
    }

    public String getJinjaUserStories() throws IOException {
        return openFileLoadContent("retorch-llm-rp/src/main/resources/input/userStories_eng.jinja");
    }

    public String getNLUserStories() throws IOException {
        return openFileLoadContent("retorch-llm-rp/src/main/resources/input/userStories_eng.txt");
    }

    public String getJinjaUserStoriesExamples() throws IOException {
        return openFileLoadContent("retorch-llm-rp/src/main/resources/input/scenarios_examples.jinja");
    }

    public String getNLUserStoriesExamples() throws IOException {
        return openFileLoadContent("retorch-llm-rp/src/main/resources/input/scenarios_examples.txt");
    }

    private String openFileLoadContent(String route) throws IOException {
        String content;
        content = FileUtils.readFileToString(new File(route), StandardCharsets.UTF_8).replace("\r\n", "\n");

        return content;
    }

}


