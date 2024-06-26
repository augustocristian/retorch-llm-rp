package giis.retorch.llmrp;

public class Experimentation {

    public static void main(String[] args) {
        GPTHelper helper = new GPTHelper();
        helper.sendChatGPTRequest(RQUserStories.getJinjaUserStories());
    }
}
