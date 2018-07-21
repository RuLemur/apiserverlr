import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import server.servlets.MyServlet;
import server.worker.pojo.InputJson;

import java.io.FileInputStream;

public class Main {

//    public static void main(String[] args) {
//        try {
//            MyServlet myServlet = new MyServlet();
//
//            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//            context.addServlet(new ServletHolder(myServlet), "/bla");
//
//            Server server = new Server(8080);
//            server.setHandler(context);
//
//            server.start();
//            server.join();
//        }catch (Exception ex){
//            System.out.println("error");
//        }

    public static void main(String[] args) {
        ObjectMapper om = new ObjectMapper();
        String filepath = "/Users/rulemur/IdeaProjects/testSegmento/src/main/resources/template.json";
        try {
            InputJson inputJson = (InputJson) om.readValue(new FileInputStream(filepath), InputJson.class);
            System.out.println(inputJson);
        } catch (Exception er){
            System.out.println("i'm in trouble");
            er.printStackTrace();
        }
    }
}
