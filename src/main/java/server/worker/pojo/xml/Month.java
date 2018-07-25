package server.worker.pojo.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RuLemur on 22.07.2018 in 22:46.
 * testZodiac
 */
public class Month {
    @JacksonXmlProperty(isAttribute = true)
    String value;

    @JacksonXmlProperty(isAttribute = true)
    short max = Short.MIN_VALUE;

    @JacksonXmlProperty(isAttribute = true)
    short min = Short.MAX_VALUE;

    @JacksonXmlProperty(isAttribute = true)
    short avg = 0;

    @JacksonXmlProperty(localName = "date")
    @JacksonXmlElementWrapper(localName = "date", useWrapping = false)
    List<Day> days = new ArrayList<>();

    int avgSum = 0;

    public Month(String value, short max, short min, short avg, List<Day> days) {
        this.value = value;
        this.max = max;
        this.min = min;
        this.avg = avg;
        this.days = days;
    }

    public Month() {
    }

    public Month(String month) {
        this.value = month;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public Day getDayDataBean(String day, String temp) {
        for (Day dayDataBean : days) {
            if (String.valueOf(day).equals(dayDataBean.getDay())) {
                dayDataBean.setTemperature(temp);
                return dayDataBean;
            }

        }
        return new Day(day, temp);
    }

    public void setDayDataBean(Day dayDataBean) {
        max = dayDataBean.takeMax() >= max ? dayDataBean.takeMax() : max;
        min = dayDataBean.takeMin() <= min ? dayDataBean.takeMin() : min;

        avgSum += (int) dayDataBean.getAvgTemperature();
        avg = (short) (avgSum / (days.size() + 1));

        for (int i = 0; i < days.size(); i++) {
            if (String.valueOf(dayDataBean.getDay()).equals(days.get(i).getDay())) {
                days.set(i, dayDataBean);
                return;
            }
        }
        days.add(dayDataBean);

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public short getMax() {
        return max;
    }

    public void setMax(short max) {
        this.max = max;
    }

    public short getMin() {
        return min;
    }

    public void setMin(short min) {
        this.min = min;
    }

    public short getAvg() {
        return avg;
    }

    public void setAvg(short avg) {
        this.avg = avg;
    }
}
