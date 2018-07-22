package server.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import server.worker.MeasurementConverter;
import server.worker.pojo.InputJson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");
        resp.getWriter().println("asd");
        ObjectMapper om = new ObjectMapper();
//        MeasurementConverter.syncJsonQueue.add( req.getInputStream(
        InputJson inputJson = om.readValue(req.getInputStream(), InputJson.class);
        MeasurementConverter.syncJsonQueue.add(inputJson);
    }
}
