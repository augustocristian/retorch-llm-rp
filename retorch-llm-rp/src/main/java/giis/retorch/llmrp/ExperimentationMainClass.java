package giis.retorch.llmrp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExperimentationMainClass {
    private static final Logger log = LoggerFactory.getLogger(ExperimentationMainClass.class);
    ExperimentationHelper exhelper;
    GPTHelper gptHelper;

    public ExperimentationMainClass() {
        exhelper = new ExperimentationHelper();
        gptHelper = new GPTHelper();
    }

    public static void putOutputToFile(String filePath, String namePrompt, String output) throws IOException {
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
        try (FileOutputStream outputStream = new FileOutputStream(filePath + "/" + namePrompt + ".txt")) {
            byte[] strToBytes = output.getBytes();
            outputStream.write(strToBytes);
        }
    }
}