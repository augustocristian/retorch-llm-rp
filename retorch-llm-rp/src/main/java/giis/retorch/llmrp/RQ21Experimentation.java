package giis.retorch.llmrp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RQ21Experimentation extends ExperimentationMainClass {
    private static final Logger log = LoggerFactory.getLogger(RQ21Experimentation.class);
    private final String outBasePath = "retorch-llm-rp/target/prompts-input/RQ1-2_1";

    public void main(String[] args) throws IOException {

        GPTHelper helper = new GPTHelper();
        UserStoriesHelper userStories = new UserStoriesHelper();

        String prompt = promptTestScenariosZeroShotNL(userStories.getNLUserStories(), 6);
        putOutputToFile(getOutBasePath(), "zero-prompt-NL", prompt);
        //helper.sendChatGPTRequest(prompt);
        prompt = promptTestScenariosZeroShotJinja(userStories.getJinjaUserStories(), 6);
        putOutputToFile(getOutBasePath(), "zero-prompt-jinja", prompt);
        //helper.sendChatGPTRequest(prompt);

        prompt = promptTestScenariosFewExampleNL(userStories.getNLUserStories(), userStories.getNLUserStoriesExamples(), 6);
        putOutputToFile(getOutBasePath(), "few-shot-prompt-NL", prompt);
        //helper.sendChatGPTRequest(prompt);
        prompt = promptTestScenariosFewExampleJinja(userStories.getJinjaUserStories(), userStories.getJinjaUserStoriesExamples(), 6);
        putOutputToFile(getOutBasePath(), "few-shot-prompt-jinja", prompt);
        //helper.sendChatGPTRequest(prompt);
    }

    static String promptTestScenariosZeroShotNL(String userStories, int numScenarios) {
        return STR
                ."Given the following user stories:\n \"\"\" \{userStories}\"\"\",\nGET \"\"\"\{numScenarios}\"\"\"  test scenarios for system tests";
    }

    public String getOutBasePath() {
        return outBasePath;
    }

    static String promptTestScenariosZeroShotJinja(String userStories, int numScenarios) {
        return STR
                ."GIVEN the user stories:\n{{ \"\"\" \{userStories}\"\"\"}},\nGET \"\"\"\{numScenarios}\"\"\"  test scenarios for System Tests";
    }

    String promptTestScenariosFewExampleNL(String userStories, String examples, int numScenarios) {
        return STR
                ."Given the following user stories:\n \"\"\"\{userStories}\"\"\",\n and the following examples:\n \"\"\"\{examples}\"\"\" \nget \"\"\"\{numScenarios}\"\"\"  test scenarios for system tests";
    }

    String promptTestScenariosFewExampleJinja(String userStories, String examples, int numScenarios) {
        return STR
                ."GIVEN the user stories:\n{{ \"\"\"\{userStories}\"\"\"}},\n AND GIVEN the examples:\n{{ \"\"\"\{examples}\"\"\" }}\nGET \"\"\"\{numScenarios}\"\"\"  test scenarios for System Tests";
    }

}