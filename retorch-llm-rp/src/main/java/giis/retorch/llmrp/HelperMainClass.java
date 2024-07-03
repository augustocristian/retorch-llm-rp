package giis.retorch.llmrp;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HelperMainClass {

    public HelperMainClass() {
        //This is the default constructor to avoid smells
    }

    public String openFileLoadContent(String route) throws IOException {
        String content;
        content = FileUtils.readFileToString(new File(route), StandardCharsets.UTF_8).replace("\r\n", "\n");

        return content;
    }

}


