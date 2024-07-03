package giis.retorch.llmrp;

import java.io.IOException;

public class TestCasesHelper extends HelperMainClass {

    public String getJinjaTestCases() throws IOException {
        return openFileLoadContent("retorch-llm-rp/src/main/resources/input/SystemTestCases.jinja");
    }

    public String getNLTestCases() throws IOException {
        return openFileLoadContent("retorch-llm-rp/src/main/resources/input/SystemTestCases.txt");
    }

    public String getJinjaTestCasesExamples() throws IOException {
        return openFileLoadContent("retorch-llm-rp/src/main/resources/input/SystemTestCases_examples.jinja");
    }

    public String getNLTestCasesExamples() throws IOException {
        return openFileLoadContent("retorch-llm-rp/src/main/resources/input/SystemTestCases_examples.txt");
    }

}


