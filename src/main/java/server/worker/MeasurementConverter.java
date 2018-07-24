package server.worker;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.log4j.Logger;
import server.main.ZodiacCore;
import server.worker.pojo.json.Measurement;
import server.worker.pojo.xml.AllMeasurementsDataBean;
import server.worker.pojo.xml.DayDataBean;
import server.worker.pojo.xml.MonthDataBean;
import server.worker.pojo.xml.YearDataBean;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by RuLemur on 22.07.2018 in 15:05.
 * testZodiac
 */
public class MeasurementConverter extends Thread {
    public final static Queue<List<Measurement>> queue = new ConcurrentLinkedQueue<List<Measurement>>();
    private static final Logger LOG = Logger.getLogger(MeasurementConverter.class);

    private int waitDelay = 1000;

    public MeasurementConverter() {
        LOG.debug("Читаем настройки для конвертера");
        waitDelay = Integer.valueOf(ZodiacCore.prop.getProperty("converter.wait.new.rq"));

    }

    private void convert(List<Measurement> measurements) {
        LOG.info("Начинаем процесс конвертации в XML");
//        System.out.println(typeConvert(measurements));
        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writeValue(new File("simple_bean.xml"), typeConvert(measurements));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private AllMeasurementsDataBean typeConvert(List<Measurement> measurements) {
//        Map<String, HashMap<String, HashMap<String, String>>> meshMap = new HashMap<>();
        AllMeasurementsDataBean jsonDataBean = new AllMeasurementsDataBean();
        DateFormat dayFormat = new SimpleDateFormat("dd");
        DateFormat monthFormat = new SimpleDateFormat("MM");
        DateFormat yearFormat = new SimpleDateFormat("yyyy");


        for (Measurement mesh : measurements) {
            String meshYear = yearFormat.format(mesh.getDate());
            String meshMonth = monthFormat.format(mesh.getDate());
            String meshDay = dayFormat.format(mesh.getDate());

            YearDataBean yearDataBean = jsonDataBean.getYearDataBean(meshYear);
            if (null != yearDataBean) {
                MonthDataBean monthDataBean = yearDataBean.getMonthDataBean(meshMonth);
                if (null != monthDataBean) {
                    DayDataBean dayDataBean = monthDataBean.getDayDataBean(meshDay);
                    if (null != dayDataBean) {
                        String newValue = dayDataBean.getTemperature() + "," + mesh.getTemperature();
                        dayDataBean.setTemperature(newValue);
                        monthDataBean.setDayDataBean(meshDay,dayDataBean);
                    } else {
                        dayDataBean = new DayDataBean();
                        dayDataBean.setTemperature(mesh.getTemperature());
                        monthDataBean.setDayDataBean(meshDay,dayDataBean);
                    }
                } else {
                    DayDataBean dayDataBean = new DayDataBean();
                    dayDataBean.setTemperature(mesh.getTemperature());
                    dayDataBean.setDay(meshDay);

                    monthDataBean.setDayDataBean(meshDay, dayDataBean);
                    yearDataBean.setMonthDataBean(meshMonth, monthDataBean);
                }
            } else {

                DayDataBean dayDataBean = new DayDataBean();
                dayDataBean.setTemperature(mesh.getTemperature());
                dayDataBean.setDay(meshDay);

                MonthDataBean monthDataBean = new MonthDataBean();
                monthDataBean.setDayDataBean(meshDay, dayDataBean);

                yearDataBean = new YearDataBean();
                yearDataBean.setMonthDataBean(meshMonth, monthDataBean);
            }
            jsonDataBean.setYearDataBean(meshYear, yearDataBean);
        }
        return jsonDataBean;
    }
//    private List<DayDataBean>

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
