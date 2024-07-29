package giis.retorch.llmrp;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExperimentationHelper {

    public ExperimentationHelper() {
        //This is the default constructor to avoid smells
    }

    public String getTestCasesCrossValidation(int pos) throws IOException {
        String[] rawTestCases = openFileLoadContent("retorch-llm-rp/src/main/resources/input/SystemTestCases.txt").split("//TC");
        List<String> contentList = new ArrayList<>(Arrays.asList(rawTestCases));
        contentList.remove(pos);

        return String.join("", contentList);
    }

    public String openFileLoadContent(String route) throws IOException {
        String content;
        content = FileUtils.readFileToString(new File(route), StandardCharsets.UTF_8).replace("\r\n", "\n");

        return content;
    }

    public String getTestScenarios() throws IOException {
        return openFileLoadContent("retorch-llm-rp/src/main/resources/input/testScenarios.txt");
    }

    public String getUserStories() throws IOException {
        return openFileLoadContent("retorch-llm-rp/src/main/resources/input/userStories_eng.txt");
    }

    public String getUserStoriesExamples() throws IOException {
        return openFileLoadContent("retorch-llm-rp/src/main/resources/input/scenarios_examples.txt");
    }

}


