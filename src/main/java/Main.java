import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import server.servlets.MyServlet;
import server.worker.MeasurementConverter;
import server.worker.pojo.InputJson;

import java.io.FileInputStream;

public class Main {

    public static void main(String[] args) {
        try {
            MyServlet myServlet = new MyServlet();

            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.addServlet(new ServletHolder(myServlet), "/bla");

            Server server = new Server(8080);
            server.setHandler(context);

            server.start();
//            server.join();
        } catch (Exception ex) {
            System.out.println("error");
        }

        MeasurementConverter mc = new MeasurementConverter();
        System.out.println("thread was runned 1 ");
        mc.start();
    }
}
