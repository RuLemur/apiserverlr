package server.servlets;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import server.helpers.PrettyPrint;
import server.worker.MeasurementConverter;
import server.worker.MeasurementValidator;
import server.worker.pojo.json.InputJson;
import server.worker.pojo.json.Measurement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;

public class MyServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(MyServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        ObjectMapper om = new ObjectMapper();
        InputJson inputJson = null;
        try {
            inputJson = om.readValue(req.getInputStream(), InputJson.class);
            if (inputJson.getMeasurements().size() == 0) {
                LOG.warn("Пришел пустой запрос");
                resp.setStatus(500);
                resp.getWriter().print("Ошибка: пустой запрос");
                return;
            }
        } catch (EOFException | NullPointerException e) {
            LOG.error("Пришел некорректный запрос", e);
            resp.setStatus(500);
            resp.getWriter().print("Ошибка: некорректный запрос");
            return;
        }


        List<Measurement> measurements = MeasurementValidator.validateAllFile(inputJson);

        //        MeasurementConverter.queue.add(measurements);
        MeasurementConverter mc = new MeasurementConverter();
        String convertedXml = mc.convert(measurements);

        resp.setStatus(200);
        resp.getWriter().println(PrettyPrint.format(convertedXml));
    }
}
