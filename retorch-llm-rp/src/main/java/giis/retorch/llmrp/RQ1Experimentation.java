package giis.retorch.llmrp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class RQ1Experimentation {
    private static final Logger log = LoggerFactory.getLogger(RQ1Experimentation.class);



    private final String outBasePath =  "retorch-llm-rp/target/prompts-input";
    String promptTestCasesFewExample(String userstories, String examples) {
        return STR
                ."GIVEN the following user stories:\n \"\"\"\{userstories}\"\"\",\n AND GIVEN the following examples:\n \"\"\"\{examples}\"\"\" \nGET test scenarios for System Tests";
    }

    static String promptTestCasesZeroShot(String userstories) {
        return STR
                ."GIVEN the following user stories:\n \"\"\" \{userstories}\"\"\",\nGET test scenarios for System Tests";
    }

    public String getOutBasePath() {
        return outBasePath;
    }

    public void main(String[] args) throws IOException {

        GPTHelper helper = new GPTHelper();
        UserStoriesHelper userStories= new UserStoriesHelper();

        String bodyprompt=promptTestCasesZeroShot(userStories.getNLUserStories());
        putOutputToFile(getOutBasePath(),"zero-prompt-NL",bodyprompt);
        bodyprompt=promptTestCasesZeroShot(userStories.getJinjaUserStories());
        putOutputToFile(getOutBasePath(),"zero-prompt-jinja",bodyprompt);

        bodyprompt=promptTestCasesFewExample(userStories.getNLUserStories(),userStories.getNLUserStoriesExamples());
        putOutputToFile(getOutBasePath(),"few-shot-prompt-NL",bodyprompt);
        bodyprompt=promptTestCasesFewExample(userStories.getJinjaUserStories(),userStories.getJinjaUserStoriesExamples());
        putOutputToFile(getOutBasePath(),"few-shot-prompt-jinja",bodyprompt);
        helper.sendChatGPTRequest(bodyprompt);
    }

    public static void putOutputToFile(String filePath,String namePrompt, String output) throws IOException {
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
        try (FileOutputStream outputStream = new FileOutputStream(filePath+"/"+namePrompt+".txt")) {
            byte[] strToBytes = output.getBytes();
            outputStream.write(strToBytes);
        }
    }

}
