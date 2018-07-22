package server.worker.pojo.json;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InputJson {
    List<Measurement> measurements;

    public InputJson(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public InputJson() {
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    @Override
    public String toString() {
        return Arrays.toString(measurements.toArray()) + "\n";
    }
}
