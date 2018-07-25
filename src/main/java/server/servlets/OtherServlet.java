package server.servlets;

import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by RuLemur on 25.07.2018 in 3:26.
 * testSegmento
 */
public class OtherServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(OtherServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(200);

        LOG.info("Пользователь обратился к другому аддресу");
        resp.getWriter().print("Сервер для преобразования JSONа в отчет XML");
    }
}
