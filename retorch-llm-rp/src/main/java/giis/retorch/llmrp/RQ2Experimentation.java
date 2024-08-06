package giis.retorch.llmrp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RQ2Experimentation {
    private static final Logger log = LoggerFactory.getLogger(RQ2Experimentation.class);
    private static final String OUT_BASE_PATH = "retorch-llm-rp/target/prompts-input/RQ2";
    static ExperimentationHelper exHelper;

    public static void main(String[] args) throws IOException {
        exHelper = new ExperimentationHelper();
        String[] testCases = {"user view enrolled courses", "user access courses and view classes", "teacher creates a course", "user can access and view the calendar"};
        //GPT4o mini
        // Process Few-Shot Prompts
        processPrompts(exHelper, testCases, false, "RQ2-few-shot", "gpt-4o-mini-2024-07-18");

        // Process Few-Shot CoT Prompts
        processPrompts(exHelper, testCases, true, "RQ2-few-shot-cot", "gpt-4o-mini-2024-07-18");
        //GPT4o
        // Process Few-Shot Prompts
        processPrompts(exHelper, testCases, false, "RQ2-few-shot", "gpt-4o-2024-05-13");

        // Process Few-Shot CoT Prompts
        processPrompts(exHelper, testCases, true, "RQ2-few-shot-cot", "gpt-4o-2024-05-13");

    }

    private static void processPrompts(ExperimentationHelper exHelper, String[] testCases, boolean isCot, String expName, String model) throws IOException {
        for (int i = 0; i < testCases.length; i++) {
            String testCaseRequired = testCases[i];
            String prompt;

            if (isCot) {
                prompt = promptTestCasesFewShotCoT(exHelper.getTestScenarios(), exHelper.getTestCasesCrossValidation(i + 1), testCaseRequired);
            } else {
                prompt = promptTestCasesFewShot(exHelper.getTestScenarios(), exHelper.getTestCasesCrossValidation(i + 1), testCaseRequired);
            }
            exHelper.putOutputToFile(getOutBasePath(), (isCot ? "few-shot-prompt-cot-" : "few-shot-prompt-") + testCaseRequired, prompt);
            log.debug("The prompt for FewShot {} is: {}", (isCot ? " COT" : ""), prompt);
            // Uncomment the next line to send the prompt to ChatGPT
            RQ2Experimentation.exHelper.sendChatGPTRequest(prompt, model, expName + "-" + testCaseRequired);
        }
    }

    static String promptTestCasesFewShotCoT(String testScenarios, String examples, String nameFunctionality) {
        return "Let’s think step by step, describe the solution by breaking it down into a task list for then generate the code. \n" + promptTestCasesFewShot(testScenarios, examples, nameFunctionality);
    }

    static String promptTestCasesFewShot(String testScenarios, String examples, String nameFunctionality) {
        return "When generating System test that covers \"\"\"" + nameFunctionality + "\"\"\" functionality.\nPlease consider the following test scenarios: :\n \"\"\" " + testScenarios + " \"\"\" \n and the following system test examples: \"\"\"" + examples + "\"\"\"\nDon’t generate the whole test suite, only the required test case.";
    }

    public static String getOutBasePath() {
        return OUT_BASE_PATH;
    }

}