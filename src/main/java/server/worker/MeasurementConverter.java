package server.worker;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.log4j.Logger;
import server.main.ZodiacCore;
import server.worker.pojo.json.Measurement;
import server.worker.pojo.xml.AllMeasurementsDataBean;
import server.worker.pojo.xml.YearDataBean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by RuLemur on 22.07.2018 in 15:05.
 * testSegmento
 */
public class MeasurementConverter extends Thread {
    public final static Queue<List<Measurement>> queue = new ConcurrentLinkedQueue<List<Measurement>>();
    private static final Logger LOG = Logger.getLogger(MeasurementConverter.class);

    private int waitDelay = 1000;

    public MeasurementConverter() {
        LOG.debug("Читаем настройки для конвертера");
        waitDelay = Integer.valueOf(ZodiacCore.prop.getProperty("converter.wait.new.rq"));

    }

    private void convert(List<Measurement> measurements)  {
        LOG.info("Начинаем процесс конвертации в XML");
        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writeValue(new File("simple_bean.xml"), new AllMeasurementsDataBean(new ArrayList<YearDataBean>(), 1, 3, 4, 5));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private List<DateDataBean>

    /**
     * Запуск в отдельный поток конвертера
     */
    public void run() {
        System.out.println("thread was runned 2");
        while (true) {
            if (queue.isEmpty()) {
                try {
                    sleep(waitDelay);
                } catch (InterruptedException e) {
                    LOG.error("Ошибка ожидания");
                    e.printStackTrace();
                }
            } else {
                LOG.debug("В очереде появилось значение, запускаем конвертер");
                convert(queue.poll());

            }
        }
    }
}
