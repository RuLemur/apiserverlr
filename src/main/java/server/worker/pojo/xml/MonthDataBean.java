package server.worker.pojo.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RuLemur on 22.07.2018 in 22:46.
 * testZodiac
 */
@JacksonXmlRootElement(localName = "month")
public class MonthDataBean {
    @JacksonXmlProperty(isAttribute = true)
    String value;

    @JacksonXmlProperty(isAttribute = true)
    String max;

    @JacksonXmlProperty(isAttribute = true)
    String min;

    @JacksonXmlProperty(isAttribute = true)
    String avg;

    List<DayDataBean> dayDataBeans = new ArrayList<>();

    public MonthDataBean(String value, String  max, String  min, String  avg, List<DayDataBean> dayDataBeans) {
        this.value = value;
        this.max = max;
        this.min = min;
        this.avg = avg;
        this.dayDataBeans = dayDataBeans;
    }

    public MonthDataBean() {
    }

    public List<DayDataBean> getDayDataBeans() {
        return dayDataBeans;
    }

    public void setDayDataBeans(List<DayDataBean> dayDataBeans) {
        this.dayDataBeans = dayDataBeans;
    }

    public DayDataBean getDayDataBean(String day) {
        for (DayDataBean dayDataBean : dayDataBeans) {
            if (dayDataBean.getDay() == String.valueOf(day)) {
                return dayDataBean;
            }

        }
        return null;
    }

    public void setDayDataBean(String day, DayDataBean dayDataBean) {
        for (int i = 0; i < dayDataBeans.size(); i++) {
            if (dayDataBeans.get(i).getDay() == String.valueOf(day)) {
                dayDataBeans.set(i, dayDataBean);
            }
            return;
        }
        dayDataBeans.add(dayDataBean);

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
}
