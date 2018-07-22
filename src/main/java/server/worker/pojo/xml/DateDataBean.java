package server.worker.pojo.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Date;

/**
 * Created by RuLemur on 22.07.2018 in 22:47.
 * testSegmento
 */
@JacksonXmlRootElement(localName = "date")
public class DateDataBean {
    Date day;
    short temperature;
    short avgTemperature;

    public DateDataBean(Date day, short temperature, short avgTemperature) {
        this.day = day;
        this.temperature = temperature;
        this.avgTemperature = avgTemperature;
    }

    public DateDataBean() {
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public short getTemperature() {
        return temperature;
    }

    public void setTemperature(short temperature) {
        this.temperature = temperature;
    }

    public short getAvgTemperature() {
        return avgTemperature;
    }

    public void setAvgTemperature(short avgTemperature) {
        this.avgTemperature = avgTemperature;
    }
}
