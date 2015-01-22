/*
package servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorHandler extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ErrorHandler.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("doGet() started");
        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
        LOGGER.debug("throwable is " + throwable);
        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        LOGGER.debug("statusCode is " + statusCode);
        String servletName = (String) req.getAttribute("javax.servlet.error.servlet_name");
        LOGGER.debug("servletName is " + servletName);
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
        LOGGER.debug("requestUri is " + requestUri);
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        if (throwable != null){
            req.getSession().setAttribute("exceptionType", throwable.getClass().getName());
            req.getSession().setAttribute("exceptionMessage", throwable.getMessage());
            LOGGER.debug("exceptionMessage" + throwable.getMessage());
        }

        req.getSession().setAttribute("statusCode", statusCode);
        req.getSession().setAttribute("requestUri", requestUri);
        req.getSession().setAttribute("servletName", servletName);
        req.getRequestDispatcher("/error.jsp").forward(req, resp);
        LOGGER.debug("forward to error.jsp");
        LOGGER.debug("doGet() finished");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
*/
