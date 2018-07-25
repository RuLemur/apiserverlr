package server.worker.pojo.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RuLemur on 22.07.2018 in 22:46.
 * testZodiac
 */
public class Year {
    @JacksonXmlProperty(isAttribute = true)
    String value;

    @JacksonXmlProperty(isAttribute = true)
    short max = Short.MIN_VALUE;

    @JacksonXmlProperty(isAttribute = true)
    short min = Short.MAX_VALUE;

    @JacksonXmlProperty(isAttribute = true)
    short avg = 0;

    @JacksonXmlElementWrapper(useWrapping = false)
    List<Month> months = new ArrayList<>();

    int avgSum = 0;

    public Year() {
    }

    public Year(String value, short max, short min, short avg, List<Month> months) {
        this.value = value;
        this.max = max;
        this.min = min;
        this.avg = avg;
        this.months = months;
    }

    public Year(String year) {
        this.value = year;
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

    public List<Month> getMonths() {
        return months;
    }

    public void setMonths(List<Month> months) {
        this.months = months;
    }

    public Month getMonthDataBean(String month) {
        for (Month monthDataBean : months) {
            if (String.valueOf(month).equals(monthDataBean.getValue())) {
                return monthDataBean;
            }

        }
        return new Month(month);
    }

    public void setMonthDataBean(Month monthDataBean) {
        max = monthDataBean.getMax() >= max ? monthDataBean.getMax() : max;
        min = monthDataBean.getMin() <= min ? monthDataBean.getMin() : min;

        avgSum += (int) monthDataBean.getAvg();
        avg = (short) (avgSum / (months.size() + 1));

        for (int i = 0; i < months.size(); i++) {
            if (String.valueOf(monthDataBean.getValue()).equals(months.get(i).getValue())) {
                months.set(i, monthDataBean);
                return;
            }
        }
        months.add(monthDataBean);


    }
}
