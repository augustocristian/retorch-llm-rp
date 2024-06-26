package giis.retorch.llmrp;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RQUserStories {
    private RQUserStories(){}
    public static String getJinjaUserStories() {
        String titleID="title";
        String descriptionID="description";
        String acceptanceCriteriaID="acceptanceCriteria";
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setTemplateMode("HTML");
        resolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);

        // Datos de ejemplo
        Map<String, Object> userStory1 = new HashMap<>();
        userStory1.put(titleID, "User Login");
        userStory1.put(descriptionID, "As a user, I want to log into the system so that I can access my personal dashboard.");
        userStory1.put(acceptanceCriteriaID, Arrays.asList(
                "Given that the user has valid credentials, when they enter their username and password, then they should be logged in.",
                "Given that the user has invalid credentials, when they try to log in, then they should see an error message."
        ));


        Map<String, Object> userStory2 = new HashMap<>();
        userStory2.put(titleID, "View Profile");
        userStory2.put(descriptionID, "As a user, I want to view my profile information so that I can see my personal details and update them if needed.");
        userStory2.put(acceptanceCriteriaID, Arrays.asList(
                "Given that the user is logged in, when they navigate to the profile page, then they should see their personal information.",
                "Given that the user updates their information, when they save the changes, then the updated information should be displayed."
        ));

        Map<String, Object> userStory3 = new HashMap<>();
        userStory3.put(titleID, "Password Reset");
        userStory3.put(descriptionID, "As a user, I want to reset my password so that I can regain access to my account if I forget my password.");
        userStory3.put(acceptanceCriteriaID, Arrays.asList(
                "Given that the user has forgotten their password, when they request a password reset, then they should receive an email with reset instructions.",
                "Given that the user follows the reset instructions, when they enter a new password, then their password should be updated."
        ));

        List<Map<String, Object>> userStories = Arrays.asList(userStory1, userStory2, userStory3);

        // Contexto de Thymeleaf
        Context context = new Context();
        context.setVariable("userStories", userStories);

        // Procesar la plantilla

        // Imprimir el contenido HTML generado
        return templateEngine.process("userStories", context);

    }



}


