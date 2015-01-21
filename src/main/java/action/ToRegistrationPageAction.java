package action;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToRegistrationPageAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(ToRegistrationPageAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("ToRegistrationPageAction producing");
        return new ActionResult("register.jsp");
    }
}
