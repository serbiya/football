package action;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(LoginAction.class);
    /*private User user = null;
    private Role userRole = null;*/


    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        /*LOGGER.debug("LoginAction started");
        HttpSession session = req.getSession();
        String view = null;

        String login = Validator.checkString("login", req);
        String password = Validator.checkRegisteredPassword("password", req);

        if (login == null || password == null) {
            return new ActionResult("main.jsp");
        } else {

            try {
                UserDAO userDAO = new UserDAO();
                user = userDAO.findUserByLogin(login);
            } catch (DAOException e) {
                LOGGER.error("DAOException in execute()" + e.getMessage());
            }
            LOGGER.debug("If login and password is not empty User is -" + user);
            String hash = DigestUtils.md5Hex(password);
            if (user == null || !hash.equals(user.getPassword())) {
                req.setAttribute("passwordErrorMessage", "password.error.message");
                LOGGER.debug("Cannot find user with such login and password");
                return new ActionResult("main.jsp");
            } else {

                try {
                    RoleDAO roleDAO = new RoleDAO();
                    userRole = roleDAO.findById(user.getRole().getId());
                } catch (DAOException e) {
                    LOGGER.error("DAOException in execute()" + e.getMessage());
                }
                LOGGER.debug("UserRole is: " + userRole);
                if (userRole.getRuName().equals("Администратор")) {
                    view = "adminPeriodicals";
                    LOGGER.debug("UserName is Admin");
                } else if (userRole.getRuName().equals("Пользователь")) {
                    view = "userPeriodicals";
                    LOGGER.debug("UserName is simple user");
                }
                session.setAttribute("user", user);
                LOGGER.debug("Session attribute of user - " + user);
                session.setAttribute("userRole", userRole);
                LOGGER.debug("Session attribute of userRole - " + userRole);
            }
        }
        LOGGER.debug("view is: " + view);
        LOGGER.debug("LoginAction finished");*/
        String view = "delete me!!!";//todo delete this string
        return new ActionResult(view, true);
    }

}

