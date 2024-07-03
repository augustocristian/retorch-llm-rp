package giis.retorch.llmrp;

import java.io.IOException;

public class RQ22Experimentation extends ExperimentationMainClass {
    private final String outBasePath = "retorch-llm-rp/target/prompts-input/RQ1-2_2";

    public void main(String[] args) throws IOException {

        GPTHelper helper = new GPTHelper();
        TestCasesHelper testCases = new TestCasesHelper();
String testCaseRequired="Create an entry in the Class Forum";
        String prompt = promptTestCasesZeroShotNL(testCases.getNLTestCases(), testCaseRequired);
        putOutputToFile(getOutBasePath(), "zero-prompt-NL", prompt);
        //helper.sendChatGPTRequest(prompt);
        prompt = promptTestCasesZeroShotJinja(testCases.getJinjaTestCases(), testCaseRequired);
        putOutputToFile(getOutBasePath(), "zero-prompt-jinja", prompt);
        //helper.sendChatGPTRequest(prompt);

        prompt = promptTestCasesFewExampleNL(testCases.getNLTestCases(), testCases.getNLTestCasesExamples(), testCaseRequired);
        putOutputToFile(getOutBasePath(), "few-shot-prompt-NL", prompt);
        //helper.sendChatGPTRequest(prompt);
        prompt = promptTestCasesFewExampleJinja(testCases.getJinjaTestCases(), testCases.getJinjaTestCasesExamples(), testCaseRequired);
        putOutputToFile(getOutBasePath(), "few-shot-prompt-jinja", prompt);
        //helper.sendChatGPTRequest(prompt);
    }

    static String promptTestCasesZeroShotNL(String testCases, String nameFunctionality) {
        return STR
                ."Given the following system test cases:\n \"\"\" \{testCases}\"\"\",\nget an additional test case that checks the  \"\"\"\{nameFunctionality}\"\"\" functionality";
    }

    public String getOutBasePath() {
        return outBasePath;
    }

    static String promptTestCasesZeroShotJinja(String testCases, String nameFunctionality) {
        return STR
                ."GIVEN the system test cases:\n{{ \"\"\" \{testCases}\"\"\"}},\nGET a test case that checks \"\"\"\{nameFunctionality}\"\"\" functionality";
    }

    String promptTestCasesFewExampleNL(String testCases, String examples, String nameFunctionality) {
        return STR
                ."Given the following system test cases:\n \"\"\"\{testCases}\"\"\",\n and the following example:\n \"\"\"\{examples}\"\"\" \nget an additional test case that checks the  \"\"\"\{nameFunctionality}\"\"\"  functionality";
    }

    String promptTestCasesFewExampleJinja(String testCases, String examples, String nameFunctionality) {
        return STR
                ."GIVEN the system test cases:\n{{ \"\"\"\{testCases}\"\"\"}},\n AND \nGIVEN the following examples:\n{{ \"\"\"\{examples}\"\"\" }}\nGET a test case that checks \"\"\"\{nameFunctionality}\"\"\"  functionality";
    }

}