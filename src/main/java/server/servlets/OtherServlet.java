package server.servlets;

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
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(200);

        resp.getWriter().println("Сервер для преобразования JSONа в отчет XML");
    }
}
