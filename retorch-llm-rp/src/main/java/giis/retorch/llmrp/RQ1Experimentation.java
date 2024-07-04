package giis.retorch.llmrp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RQ1Experimentation extends ExperimentationMainClass {
    private static final Logger log = LoggerFactory.getLogger(RQ1Experimentation.class);
    private static final String OUT_BASE_PATH = "retorch-llm-rp/target/prompts-input/RQ1";

    public void main() throws IOException {

        String prompt = promptTestScenariosZeroShotJinja(exhelper.getUserStories());
        putOutputToFile(getOutBasePath(), "zero-prompt", prompt);
        gptHelper.sendChatGPTRequest(prompt);
        log.debug("The prompt for ZeroShot is: {}", prompt);

        prompt = promptTestScenariosFewExampleJinja(exhelper.getUserStories(), exhelper.getUserStoriesExamples());
        log.debug("The prompt for Few Shot is: {}", prompt);
        putOutputToFile(getOutBasePath(), "few-shot-prompt", prompt);
        gptHelper.sendChatGPTRequest(prompt);

    }

    static String promptTestScenariosZeroShotJinja(String userStories) {
        return "GIVEN the system requirements:\n{{ \"\"\" " + userStories + "\"\"\"}},\nGET test scenarios for System Tests";
    }

    public String getOutBasePath() {
        return OUT_BASE_PATH;
    }

    String promptTestScenariosFewExampleJinja(String userStories, String testScenariosExamples) {
        return "GIVEN the system requirements:\n{{ \"\"\" " + userStories + "\"\"\"}},\n AND GIVEN the examples:\n{{ \"\"\" " + testScenariosExamples + " \"\"\" }}\nGET test scenarios for System Tests";
    }

}