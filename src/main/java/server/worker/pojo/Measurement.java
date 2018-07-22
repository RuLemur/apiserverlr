package server.worker.pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Measurement {
    String temperature;
    Date date;
    String unit;

    public Measurement() {
    }

    public Measurement(Date date, String temperature, String unit) {
        this.date = date;
        this.temperature = temperature;
        this.unit = unit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Date: " + date.toString() + ", Temperature: " + temperature + " Unit: " + unit + "\n";
    }
}
