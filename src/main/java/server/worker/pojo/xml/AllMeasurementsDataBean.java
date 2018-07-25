package server.worker.pojo.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RuLemur on 22.07.2018 in 22:43.
 * testZodiac
 */
//@XmlAccessorType(XmlAccessType.FIELD)
@JacksonXmlRootElement(localName = "measurement")
public class AllMeasurementsDataBean {

    @JacksonXmlElementWrapper(useWrapping = false)
    List<Year> years = new ArrayList<>();

    @JacksonXmlProperty(isAttribute = true)
    int total;
    @JacksonXmlProperty(isAttribute = true)
    int max = Short.MIN_VALUE;
    @JacksonXmlProperty(isAttribute = true)
    int min = Short.MAX_VALUE;
    @JacksonXmlProperty(isAttribute = true)
    int avg;

    int avgSum = 0;

    public AllMeasurementsDataBean(List<Year> years, int total, int max, int min, int avg) {
        this.years = years;
        this.total = total;
        this.max = max;
        this.min = min;
        this.avg = avg;
    }

    public AllMeasurementsDataBean() {
    }


    public List<Year> getYears() {
        return years;
    }

    public void setYears(List<Year> years) {
        this.years = years;
    }

    public Year getYearDataBean(String year) {
        for (Year yearDataBean : years) {
            if (String.valueOf(year).equals(yearDataBean.getValue())) {
                return yearDataBean;
            }

        }
        return new Year(year);
    }

    public void setYearDataBean(Year yearDataBean) {
        total++;

        max = (int) yearDataBean.getMax() > max ? (int) yearDataBean.getMax() : max;
        min = (int) yearDataBean.getMax() < min ? (int) yearDataBean.getMax() : min;

        avgSum += (int) yearDataBean.getAvg();
        avg = avgSum / (years.size() + 1);
        for (int i = 0; i < years.size(); i++) {
            if (String.valueOf(yearDataBean.getValue()).equals(years.get(i).getValue())) {
                years.set(i, yearDataBean);
                return;
            }
        }
        years.add(yearDataBean);

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
