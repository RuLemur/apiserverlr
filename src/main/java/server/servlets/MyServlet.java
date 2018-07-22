package server.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import server.worker.MeasurementConverter;
import server.worker.MeasurementValidator;
import server.worker.pojo.InputJson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        ObjectMapper om = new ObjectMapper();
        InputJson inputJson = om.readValue(req.getInputStream(), InputJson.class);

        MeasurementValidator.validateAllFile(inputJson);
        resp.getWriter().println("СУСЕС");
        System.out.println(inputJson);
        MeasurementConverter.queue.add(inputJson);
    }
}
