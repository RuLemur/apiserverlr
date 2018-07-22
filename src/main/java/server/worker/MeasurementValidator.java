package server.worker;

import org.apache.log4j.Logger;
import server.worker.pojo.InputJson;
import server.worker.pojo.Measurement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MeasurementValidator {
    private static final Logger LOG = Logger.getLogger(MeasurementValidator.class);

    public static boolean validateAllFile(InputJson inputJson) {
        List<Measurement> goodMeasurementList = new ArrayList<Measurement>();
        boolean emptyAmgTemp = true;
        short avgTemp = 0;

        for (Measurement entity : inputJson.getMeasurements()) {
            Measurement goodMeasurement = new Measurement();
            if (entity.getDate() == null) {
                LOG.error("В записи отсутствует дата. Запись вычеслению не подлежит.");
                break;
            }
            goodMeasurement.setDate(entity.getDate());

            if (entity.getTemperature() == null) {
                if (goodMeasurementList.size() == 0) {
                    LOG.error("В записи отстутствует температура. Данная запись пропущена");
                    break;
                }
                LOG.error("В записи отстутствует температура. Для данной записи взята средняя температура из прошлых записей");
                goodMeasurement.setTemperature(String.valueOf(avgTemp));
            }
            goodMeasurement.setTemperature(entity.getTemperature());

            if (entity.getUnit() == null){
                LOG.warn("Отстутствует еденица измерения температуры. Указан Кельвин");
            } else {
                String[] units = {"k", "c", "f"};
                if (Arrays.asList(units).contains(entity.getUnit().toLowerCase())){
                    goodMeasurement.setUnit(entity.getUnit().toUpperCase());
                    break;
                } else {
                    LOG.warn("Некорректная еденица измерения температуры. Указан Кельвин");
                }
            }
            goodMeasurement.setUnit("K");
        }

        return false;
    }
}
