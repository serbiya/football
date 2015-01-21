package servlet;

import action.Action;
import action.ActionFactory;
import action.ActionResult;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Controller.class);
    ActionFactory actionFactory = new ActionFactory();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("Controller started");

        String actionName = req.getParameter("action");
        LOGGER.debug("Request parameter: action -" + actionName);

        Action action = actionFactory.getAction(actionName);
        LOGGER.debug("Get action " + action);

        ActionResult actionResult = action.execute(req, resp);
        LOGGER.debug("actionResult is - " + actionResult.getView());
        if (actionResult.isRedirect()) {
            redirect(actionResult, req, resp);
        } else {
            forward(actionResult, req, resp);
        }
        LOGGER.debug("Controller finished");
    }

    private void redirect(ActionResult actionResult, HttpServletRequest req, HttpServletResponse res) {
        String path = "/webapp/controller?action=" + actionResult.getView();
        LOGGER.debug("redirect() path is - " + path);
        try {
            res.sendRedirect(path);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void forward(ActionResult actionResult, HttpServletRequest req, HttpServletResponse resp) {
        String path = "/WEB-INF/" + actionResult.getView();
        LOGGER.debug("forward() path is - " + path);
        try {
            getServletContext().getRequestDispatcher(path).forward(req, resp);
        } catch (ServletException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
