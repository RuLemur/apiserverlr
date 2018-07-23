package server.worker;

import org.apache.log4j.Logger;
import server.helpers.TempConverter;
import server.worker.pojo.json.InputJson;
import server.worker.pojo.json.Measurement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class MeasurementValidator {
    private static final Logger LOG = Logger.getLogger(MeasurementValidator.class);
    private static List<Measurement> goodMeasurementList;

    /**
     * Метод, который полностью проверяет файл, добавляет нужное и удаляет "сломанные" записи.
     * И конвертирует все велечины в целисий
     *
     * @param inputJson - исходный файл с велеичнами
     * @return - исправленный файл
     */
    public static List<Measurement> validateAllFile(InputJson inputJson) {
        goodMeasurementList = new ArrayList<>();
        int sumTemp = 0;

        for (Measurement entity : inputJson.getMeasurements()) {
            Measurement goodMeasurement = new Measurement();

            Date date = checkDate(entity);
            String temp = checkTemperature(entity, sumTemp);
            String unit = checkUnit(entity);
            if (date == null || temp == null) {
                continue;
            }
            short newTemp = 0;
            switch (unit) {
                case "K": {
                    newTemp = TempConverter.convert(TempConverter.Units.KELVIN, TempConverter.Units.CELSIUS, Short.valueOf(temp));
                    sumTemp += newTemp;
                    unit = "C";
                    break;
                }
                case "F": {
                    newTemp = TempConverter.convert(TempConverter.Units.FAHRENHEIT, TempConverter.Units.CELSIUS, Short.valueOf(temp));
                    sumTemp += newTemp;
                    unit = "C";
                    break;
                }
                case "C": {
                    newTemp = Short.valueOf(temp);
                    sumTemp += newTemp;
                    break;
                }
            }

            goodMeasurement.setDate(date);
            goodMeasurement.setTemperature(String.valueOf(newTemp));
            goodMeasurement.setUnit(unit);

            goodMeasurementList.add(goodMeasurement);
            LOG.debug("Успешно добавлена запись: " + goodMeasurement.toString());
        }

        return goodMeasurementList;
    }

    /**
     * Проверка поля даты
     *
     * @param entity - 1 запись измерений
     * @return - дату, либо null, если формат неверный
     */
    private static Date checkDate(Measurement entity) {
        if (entity.getDate() == null) {
            LOG.error("В записи отсутствует дата. Запись вычеслению не подлежит.");
            return null;
        }
        return entity.getDate();
    }

    /**
     * Проверка поля температуры
     *
     * @param entity - 1 запись измерений
     * @return - температуру, если нету, то среднюю по предыдущим замерам, если и их нет, то null
     */
    private static String checkTemperature(Measurement entity, int sumTemp) {
        if (entity.getTemperature() == null || entity.getTemperature().isEmpty()) {
            if (goodMeasurementList.size() == 0) {
                LOG.error("В записи отстутствует температура. Данная запись пропущена");
                return null;
            }
            LOG.error("В записи отстутствует температура. Для данной записи взята средняя температура из прошлых записей");
            return String.valueOf((sumTemp / goodMeasurementList.size()));
        }
        return entity.getTemperature();
    }

    /**
     * Проверка поля шаклы измерения
     *
     * @param entity - 1 запись измерений
     * @return - Обозначение шкалы, K - если формат неверный или не указан
     */
    private static String checkUnit(Measurement entity) {
        String[] units = {"k", "c", "f"};
        if (entity.getUnit() != null && !entity.getUnit().isEmpty()
                && Arrays.asList(units).contains(entity.getUnit().toLowerCase())) {
            return entity.getUnit().toUpperCase();
        } else {
            LOG.warn("Отстутствует или некорректно указана еденица измерения температуры. Указан Кельвин");
            return "K";
        }
    }

}