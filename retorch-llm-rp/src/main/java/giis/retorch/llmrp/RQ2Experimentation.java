package giis.retorch.llmrp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RQ2Experimentation extends ExperimentationMainClass {
    private static final Logger log = LoggerFactory.getLogger(RQ2Experimentation.class);
    private static final String OUT_BASE_PATH = "retorch-llm-rp/target/prompts-input/RQ2";

    public void main() throws IOException {

        String testCaseRequired = "Create an entry in the Class Forum";
        String prompt = promptTestCasesZeroShotJinja(exhelper.getTestScenarios(), testCaseRequired);
        putOutputToFile(getOutBasePath(), "zero-prompt", prompt);
        log.debug("The prompt for ZeroShot is: {}", prompt);
        gptHelper.sendChatGPTRequest(prompt);

        prompt = promptTestCasesFewExampleJinja(exhelper.getTestScenarios(), exhelper.getTestCases(), testCaseRequired);
        putOutputToFile(getOutBasePath(), "few-shot-prompt", prompt);
        log.debug("The prompt for FewShot is: {}", prompt);
        gptHelper.sendChatGPTRequest(prompt);
    }

    static String promptTestCasesZeroShotJinja(String testScenarios, String nameFunctionality) {
        return "GIVEN the test scenarios:\n{{ \"\"\" " + testScenarios + "\"\"\"}},\nGET a test case that checks \"\"\"" + nameFunctionality + "\"\"\" functionality";
    }

    public String getOutBasePath() {
        return OUT_BASE_PATH;
    }

    String promptTestCasesFewExampleJinja(String testScenarios, String examples, String nameFunctionality) {
        return "GIVEN the test scenarios:\n{{ \"\"\" " + testScenarios + "\"\"\"}},\n AND \nGIVEN the following examples:\n{{ \"\"\" " + examples + " \"\"\" }}\nGET a test case that checks \"\"\"" + nameFunctionality + "\"\"\" functionality";
    }

}