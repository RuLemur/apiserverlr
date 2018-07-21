package server.worker.pojo;

import java.util.Date;

public class Measurement {
    Date date;
    double temperature;
    String unit;

    public Measurement() {
    }

    public Measurement(Date date, double temperature, String unit) {
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

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
