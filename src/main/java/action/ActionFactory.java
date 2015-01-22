package action;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static final Logger LOGGER = Logger.getLogger(ActionFactory.class);
    private static Map<String, Action> actions = new HashMap<String, Action>();

    static {
        actions.put("GET/home", new ShowPageAction("main.jsp"));
        actions.put("GET/register", new ShowPageAction("register.jsp"));
        actions.put("POST/login", new LoginAction());
        actions.put("POST/register", new RegistrationAction());
    }

    public static Action getAction(String actionName) {
        if (!actions.containsKey(actionName)) {
            LOGGER.info("There is no action: " + actionName);
            return actions.get("toError");//todo
        }
        return actions.get(actionName);
    }
}
