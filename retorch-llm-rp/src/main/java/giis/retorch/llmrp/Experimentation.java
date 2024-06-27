package giis.retorch.llmrp;

import java.io.IOException;

public class Experimentation {
    String promptTestCasesFewExample(String userstories, String examples) {
        return STR
                ."GIVEN the following user stories:\n \"\"\"\{userstories}\"\"\",\nGET test scenarios for System Tests AS the following examples \"\"\" \{examples} \"\"\" ";
    }

    static String promptTestCasesZeroShot(String userstories) {
        return STR
                ."GIVEN the following user stories:\n \"\"\" \{userstories}\"\"\",\nGET test scenarios for System Tests";
    }

    public static void main(String[] args) throws IOException {

        GPTHelper helper = new GPTHelper();
        RQUserStories userStories= new RQUserStories();

        String bodyprompt=promptTestCasesZeroShot(userStories.getNLUserStories());
        helper.sendChatGPTRequest(bodyprompt);
    }
}
