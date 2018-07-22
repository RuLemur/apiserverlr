package server.worker.pojo.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import java.util.List;

/**
 * Created by RuLemur on 22.07.2018 in 22:43.
 * testSegmento
 */
@XmlAccessorType(XmlAccessType.FIELD)
@JacksonXmlRootElement(localName = "measurement")
public class AllMeasurementsDataBean {
    @XmlValue
    List<YearDataBean> yearDataBeans;

    @JacksonXmlProperty(isAttribute=true)
    int total;
    @JacksonXmlProperty(isAttribute=true)
    int max;
    @JacksonXmlProperty(isAttribute=true)
    int min;
    @JacksonXmlProperty(isAttribute=true)
    int avg;

    public AllMeasurementsDataBean(List<YearDataBean> yearDataBeans, int total, int max, int min, int avg) {
        this.yearDataBeans = yearDataBeans;
        this.total = total;
        this.max = max;
        this.min = min;
        this.avg = avg;
    }

    public AllMeasurementsDataBean() {
    }

    public List<YearDataBean> getYearDataBeans() {
        return yearDataBeans;
    }

    public void setYearDataBeans(List<YearDataBean> yearDataBeans) {
        this.yearDataBeans = yearDataBeans;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getAvg() {
        return avg;
    }

    public void setAvg(int avg) {
        this.avg = avg;
    }
}
