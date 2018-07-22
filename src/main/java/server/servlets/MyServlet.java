package server.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import server.worker.MeasurementConverter;
import server.worker.MeasurementValidator;
import server.worker.pojo.json.InputJson;
import server.worker.pojo.json.Measurement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        ObjectMapper om = new ObjectMapper();
        InputJson inputJson = om.readValue(req.getInputStream(), InputJson.class);

        List<Measurement> measurements = MeasurementValidator.validateAllFile(inputJson);
        resp.getWriter().println("СУСЕС");
        //TODO: обработка пустых файлов
        MeasurementConverter.queue.add(measurements);
    }
}
