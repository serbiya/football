package action;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static final Logger LOGGER = Logger.getLogger(ActionFactory.class);
    private Map<String, Action> actions;

    public ActionFactory() {
        actions = new HashMap<String, Action>();
        actions.put("register", new RegistrationAction());
        actions.put("toError", new ToErrorAction());
        actions.put("toRegister", new ToRegistrationPageAction());
        //actions.put("login", new LoginAction());
    }

    public Action getAction(String actionName) {
        if (!actions.containsKey(actionName)) {
            LOGGER.debug("No action: " + actionName);
            return actions.get("toError");
        }
        return actions.get(actionName);
    }
}
