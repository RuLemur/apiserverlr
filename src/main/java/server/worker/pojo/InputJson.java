package server.worker.pojo;

import java.util.List;

public class InputJson {
    List<Measurement> measurements;

    public InputJson(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public InputJson(){

    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
}
