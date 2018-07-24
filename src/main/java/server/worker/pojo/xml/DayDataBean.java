package server.worker.pojo.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Date;

/**
 * Created by RuLemur on 22.07.2018 in 22:47.
 * testSegmento
 */
@JacksonXmlRootElement(localName = "date")
public class DayDataBean {
    String day;
    String temperature;
    String avgTemperature;

    public DayDataBean(String day, String temperature, String avgTemperature) {
        this.day = day;
        this.temperature = temperature;
        this.avgTemperature = avgTemperature;
    }

    public DayDataBean() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getAvgTemperature() {
        return avgTemperature;
    }

    public void setAvgTemperature(String avgTemperature) {
        this.avgTemperature = avgTemperature;
    }
}
