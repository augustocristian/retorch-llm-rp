package giis.retorch.llmrp;

import org.apache.commons.io.FileUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.StringTemplate.STR;

public class RQUserStories {

    public RQUserStories() {
    }

    public String getJinjaUserStories() throws IOException {
        return openFileLoadContent("input/userStories_eng.jinja");
    }

    public String getNLUserStories() throws IOException {
        return openFileLoadContent("src\\main\\java\\giis\\retorch\\llmrp\\resources\\input\\userStories_eng.txt");
    }

    private String openFileLoadContent(String route) throws IOException {
        String content = "NOT CONTENT";
        content = FileUtils.readFileToString(new File(route), "utf-8").replace("\r\n", "\n");

        return content;
    }
}


