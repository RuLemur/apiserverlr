package server.worker;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.log4j.Logger;
import server.main.ZodiacCore;
import server.worker.pojo.json.Measurement;
import server.worker.pojo.xml.AllMeasurementsDataBean;
import server.worker.pojo.xml.Day;
import server.worker.pojo.xml.Month;
import server.worker.pojo.xml.Year;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by RuLemur on 22.07.2018 in 15:05.
 * testZodiac
 */
public class MeasurementConverter { //extends Thread
    //    public final static Queue<List<Measurement>> queue = new ConcurrentLinkedQueue<List<Measurement>>();
    private static final Logger LOG = Logger.getLogger(MeasurementConverter.class);

    private int waitDelay = 1000;

    public MeasurementConverter() {
        LOG.debug("Читаем настройки для конвертера");
        waitDelay = Integer.valueOf(ZodiacCore.prop.getProperty("converter.wait.new.rq"));

    }

    public String convert(List<Measurement> measurements) {
        LOG.info("Начинаем процесс конвертации в XML");
        XmlMapper xmlMapper = new XmlMapper();
        try {
            Random rnd = new Random();
            String filePath = ZodiacCore.prop.getProperty("reports.path") + "result_" + rnd.nextInt(10000) + ".xml";
            File report = new File(filePath);
            AllMeasurementsDataBean value = typeConvert(measurements);
            xmlMapper.writeValue(report, value);
            return xmlMapper.writeValueAsString(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private AllMeasurementsDataBean typeConvert(List<Measurement> measurements) {
        AllMeasurementsDataBean jsonDataBean = new AllMeasurementsDataBean();
        jsonDataBean.setTotal(measurements.size());

        DateFormat dayFormat = new SimpleDateFormat("dd");
        DateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.getDefault());
        DateFormat yearFormat = new SimpleDateFormat("yyyy");

        for (Measurement mesh : measurements) {
            String meshYear = yearFormat.format(mesh.getDate());
            String meshMonth = monthFormat.format(mesh.getDate());
            String meshDay = dayFormat.format(mesh.getDate());

            Year year = jsonDataBean.getYearDataBean(meshYear);
            Month month = year.getMonthDataBean(meshMonth);
            Day day = month.getDayDataBean(meshDay, mesh.getTemperature());

            month.setDayDataBean(day);
            year.setMonthDataBean(month);
            jsonDataBean.setYearDataBean(year);
        }

        return jsonDataBean;
    }

//    /**
//     * Запуск в отдельный поток конвертера
//     */
//    public void run() {
//        LOG.info("Запушен конвертер в отедльном потоке");
//        while (true) {
//            if (queue.isEmpty()) {
//                try {
//                    sleep(waitDelay);
//                } catch (InterruptedException e) {
//                    LOG.error("Ошибка ожидания");
//                    e.printStackTrace();
//                }
//            } else {
//                LOG.debug("В очереде появилось значение, запускаем конвертер");
//                convert(queue.poll());
//
//            }
//        }
//    }
}
