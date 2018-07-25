package server.worker.pojo.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by RuLemur on 22.07.2018 in 22:47.
 * testZodiac
 */
public class Day {
    String day;
    String temperature = "";
    short avgTemperature = 0;

    private int count = 1;
    private int sumTemp = 0;

    private short maximum = 0;

    private short minimum = 0;

    public Day(String day, String temperature, short avgTemperature) {
        this.day = day;
        this.temperature = temperature;
        this.avgTemperature = avgTemperature;
    }

    public Day() {
    }

    public Day(String day, String temp) {
        this.day = day;
        this.temperature += temp;
        avgTemperature = Short.valueOf(temp);
        sumTemp = avgTemperature;
        maximum = avgTemperature;
        minimum = avgTemperature;
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
        short temp = Short.valueOf(temperature);
        this.temperature += "," + temperature;
        sumTemp += (int) temp;
        int avgTemp = sumTemp / count;
        avgTemperature = (short) avgTemp;
        count++;

        maximum = temp >= maximum ? temp : maximum;
        minimum = temp <= minimum ? temp : minimum;
    }

    public short getAvgTemperature() {
        return avgTemperature;
    }

    public void setAvgTemperature(short avgTemperature) {
        this.avgTemperature = avgTemperature;
    }

    public short takeMax() {
        return maximum;
    }

    public short takeMin() {
        return minimum;
    }

}
