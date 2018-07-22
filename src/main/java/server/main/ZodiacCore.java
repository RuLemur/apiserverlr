package server.main;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import server.servlets.MyServlet;
import server.worker.MeasurementConverter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ZodiacCore {
    private static final Logger LOG = Logger.getLogger(ZodiacCore.class);
    public static final Properties prop = new Properties();
    private static int port = 8080;

    public static void setUp() {
        try {
            prop.load(new FileInputStream("./src/main/resources/server.properties"));
            port = Integer.valueOf(prop.getProperty("server.port"));
        } catch (IOException e) {
            LOG.error("Ошибка загрузки файла настроек. Файл настроек: /resources/server.properties");
        }
    }

    public static void main(String[] args) {
        try {
            setUp();
            MyServlet myServlet = new MyServlet();

            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.addServlet(new ServletHolder(myServlet), "/converter");

            Server server = new Server(port);
            server.setHandler(context);

            server.start();
            LOG.info(String.format("Сервер запущен на порту %d", port));

        } catch (Exception ex) {
            LOG.error("Server error");
        }

        MeasurementConverter mc = new MeasurementConverter();
        mc.start();
        LOG.info("Запустили поток для обработки входяших JSON");
    }
}
