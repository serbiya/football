package action;

import dao.UserDAO;
import entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(RegistrationAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("RegistrationAction started");
        UserDAO userDAO = new UserDAO();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User userInBase = null;
        try {
            userInBase = userDAO.findUserByLogin(login);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        User user = new User();

        HttpSession session = req.getSession();
        if (userInBase != null) {
            req.setAttribute("sameLogin", "login.error.message");
            LOGGER.debug("loginErrorMessage");
            return new ActionResult("register.jsp");
        } else {
            try {
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(password);

                userDAO.create(user);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            session.setAttribute("successfulMessage", "successful.registration.message");
            session.setAttribute("user", user);
            LOGGER.debug("New user - " + user);
        }
        LOGGER.debug("Registration has finished");
        return new ActionResult("ok.jsp");
    }
}
