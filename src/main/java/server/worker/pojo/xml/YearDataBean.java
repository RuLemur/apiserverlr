package server.worker.pojo.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/**
 * Created by RuLemur on 22.07.2018 in 22:46.
 * testSegmento
 */
@JacksonXmlRootElement(localName = "year")
public class YearDataBean {
    @JacksonXmlProperty(isAttribute = true)
    short value;

    @JacksonXmlProperty(isAttribute = true)
    short max;

    @JacksonXmlProperty(isAttribute = true)
    short min;

    @JacksonXmlProperty(isAttribute = true)
    int avg;

    List<MonthDataBean> monthDataBeans;

    public YearDataBean() {
    }

    public YearDataBean(short value, short max, short min, int avg, List<MonthDataBean> monthDataBeans) {
        this.value = value;
        this.max = max;
        this.min = min;
        this.avg = avg;
        this.monthDataBeans = monthDataBeans;
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

    public List<MonthDataBean> getMonthDataBeans() {
        return monthDataBeans;
    }

    public void setMonthDataBeans(List<MonthDataBean> monthDataBeans) {
        this.monthDataBeans = monthDataBeans;
    }
}
