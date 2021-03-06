package action;

import dao.factory.DAOFactory;
import dao.user.UserDAO;
import dao.user.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(RegistrationAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("Start RegistrationAction");
        DAOFactory daoFactory = new DAOFactory();
        UserDAO userDAO = daoFactory.getUserDAO();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (null == login || null == password) {
        } //todo error page or smth

        if (userDAO.isUserRegistered(login)) {
            //req.setAttribute("sameLogin", "login.error.message");//todo
            LOGGER.error("Login is not unique");
            return new ActionResult("register.jsp");
        } else {
            User user = new User();
            HttpSession session = req.getSession();

            user.setLogin(login);
            user.setName(req.getParameter("name"));
            user.setLastName(req.getParameter("lastName"));
            user.setEmail(req.getParameter("email"));
            user.setPassword(req.getParameter(password));
            user.setRoleID(2);//todo Создать таблицу ролей
            user.setActive(true);
            userDAO.create(user);

            //todo Позже добавить параметр  jsp и расскомментить
            /*session.setAttribute("successfulMessage", "successful.registration.message");
            session.setAttribute("user", user);*/
            LOGGER.info("New user - " + login);
        }

        LOGGER.info("Finish RegistrationAction");
        return new ActionResult("ok.jsp");//todo Изменить на нормальную страницу + параметр, либо в ok.jsp принимать параметр с текстом + сделать ссылку на главную
        //return new ActionResult("main.jsp");
    }
}
