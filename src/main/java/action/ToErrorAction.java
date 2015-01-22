package action;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToErrorAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(ToErrorAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("ToErrorAction producing");
        return new ActionResult("error.jsp");
    }
}
