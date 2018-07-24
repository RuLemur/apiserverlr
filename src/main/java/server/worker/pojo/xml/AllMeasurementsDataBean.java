package server.worker.pojo.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by RuLemur on 22.07.2018 in 22:43.
 * testSegmento
 */
@XmlAccessorType(XmlAccessType.FIELD)
@JacksonXmlRootElement(localName = "measurement")
public class AllMeasurementsDataBean {
    @XmlValue
    @JacksonXmlElementWrapper(useWrapping = false)
    List<YearDataBean> yearDataBeans = new ArrayList<>();

    @JacksonXmlProperty(isAttribute = true)
    int total;
    @JacksonXmlProperty(isAttribute = true)
    int max;
    @JacksonXmlProperty(isAttribute = true)
    int min;
    @JacksonXmlProperty(isAttribute = true)
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

    public void setYearDataBeans( List<YearDataBean> yearDataBeans) {
        this.yearDataBeans = yearDataBeans;
    }

    public YearDataBean getYearDataBean(String year) {
        for (YearDataBean yearDataBean : yearDataBeans) {
            if (yearDataBean.getValue() == String.valueOf(year)) {
                return yearDataBean;
            }

        }
        return null;
    }

    public void setYearDataBean(String year, YearDataBean yearDataBean) {
        for (int i = 0; i < yearDataBeans.size(); i++) {
            if (yearDataBeans.get(i).getValue() == String.valueOf(year)) {
                yearDataBeans.set(i, yearDataBean);
            }
            return;
        }
        yearDataBeans.add(yearDataBean);

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
