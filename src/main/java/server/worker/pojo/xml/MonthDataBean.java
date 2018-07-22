package server.worker.pojo.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/**
 * Created by RuLemur on 22.07.2018 in 22:46.
 * testSegmento
 */
@JacksonXmlRootElement(localName = "month")
public class MonthDataBean {
    @JacksonXmlProperty(isAttribute = true)
    short value;

    @JacksonXmlProperty(isAttribute = true)
    short max;

    @JacksonXmlProperty(isAttribute = true)
    short min;

    @JacksonXmlProperty(isAttribute = true)
    int avg;

    List<DateDataBean> monthData;

    public MonthDataBean(short value, short max, short min, int avg, List<DateDataBean> monthData) {
        this.value = value;
        this.max = max;
        this.min = min;
        this.avg = avg;
        this.monthData = monthData;
    }

    public MonthDataBean() {
    }

    public List<DateDataBean> getMonthData() {
        return monthData;
    }

    public void setMonthData(List<DateDataBean> monthData) {
        this.monthData = monthData;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
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

    public int getAvg() {
        return avg;
    }

    public void setAvg(int avg) {
        this.avg = avg;
    }
}
