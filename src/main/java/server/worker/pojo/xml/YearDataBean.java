package server.worker.pojo.xml;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RuLemur on 22.07.2018 in 22:46.
 * testSegmento
 */
@JsonTypeName("year")
public class YearDataBean {
    @JacksonXmlProperty(isAttribute = true)
    String value;

    @JacksonXmlProperty(isAttribute = true)
    String max;

    @JacksonXmlProperty(isAttribute = true)
    String min;

    @JacksonXmlProperty(isAttribute = true)
    String avg;

    List<MonthDataBean> monthDataBeans = new ArrayList<>();

    public YearDataBean() {
    }

    public YearDataBean(String value, String max, String min, String avg, List<MonthDataBean> monthDataBeans) {
        this.value = value;
        this.max = max;
        this.min = min;
        this.avg = avg;
        this.monthDataBeans = monthDataBeans;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public List<MonthDataBean> getMonthDataBeans() {
        return monthDataBeans;
    }

    public void setMonthDataBeans(List<MonthDataBean> monthDataBeans) {
        this.monthDataBeans = monthDataBeans;
    }

    public MonthDataBean getMonthDataBean(String month) {
        for (MonthDataBean monthDataBean : monthDataBeans) {
            if (monthDataBean.getValue() == String.valueOf(month)) {
                return monthDataBean;
            }

        }
        return null;
    }

    public void setMonthDataBean(String month, MonthDataBean monthDataBean) {
        for (int i = 0; i < monthDataBeans.size(); i++) {
            if (monthDataBeans.get(i).getValue() == String.valueOf(month)) {
                monthDataBeans.set(i, monthDataBean);
            }
            return;
        }
        monthDataBeans.add(monthDataBean);

    }
}
