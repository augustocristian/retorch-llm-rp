package giis.retorch.llmrp;

import java.io.IOException;

public class UserStoriesHelper extends HelperMainClass {

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

}


