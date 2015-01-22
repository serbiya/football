package servlet;

import action.Action;
import action.ActionFactory;
import action.ActionResult;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Controller", urlPatterns = "/do/*")
public class Controller extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Controller.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        LOGGER.info("Start controller");

        String pathInfo = req.getPathInfo();
        LOGGER.info("Got path info: " + pathInfo);

        String method = req.getMethod();
        LOGGER.info("Got method: " + method);

        Action action = ActionFactory.getAction(method + pathInfo);
        LOGGER.info("Got action: " + action + " by action name: " + method + pathInfo);

        ActionResult actionResult = action.execute(req, resp);
        LOGGER.info("Got action result: " + actionResult.getView());

        if (actionResult.isRedirect()) redirect(actionResult, req, resp);
        else forward(actionResult, req, resp);
        LOGGER.info("Controller provides: " + (actionResult.isRedirect() ? "redirecting" : "forwarding"));

        //todo what is that?
        /*RequestDispatcher dispatcher = req.getRequestDispatcher(actionResult.getView());
        dispatcher.forward(req, resp);*/

        LOGGER.info("Finish controller");
    }

    private void redirect(ActionResult actionResult, HttpServletRequest req, HttpServletResponse res) {
        LOGGER.info("Start redirecting");

        String path = "/webapp/do?action=" + actionResult.getView();
        LOGGER.info("Redirect by path: " + path);

        try {
            res.sendRedirect(path);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.info("Finish redirecting");
    }

    private void forward(ActionResult actionResult, HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("Start forwarding");

        String path = "/WEB-INF/" + actionResult.getView();
        LOGGER.info("Forward by path: " + path);

        try {
            getServletContext().getRequestDispatcher(path).forward(req, resp);
        } catch (ServletException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.info("Finish forwarding");
    }
}
