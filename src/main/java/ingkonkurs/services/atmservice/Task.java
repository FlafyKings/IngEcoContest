package ingkonkurs.services.atmservice;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;

public class Task implements Comparable<Task> {

    private final int region;
    private final int requestType;
    private final int atmId;

    public Task(@JsonProperty("region") int region,
                @JsonProperty("requestType") RequestType requestType,
                @JsonProperty("atmId") int atmId) {
        this.region = region;
        this.requestType = requestType.toValue();
        this.atmId = atmId;
    }

    @Override
    public String toString() {
        return "Task: region: " + region + ", requestType: " + requestType + ", atmId: " + atmId;
    }

    @Override
    public int compareTo(Task o) {
        return Comparator.comparing(Task::getRegion)
                .thenComparing(Task::getRequestType)
                .compare(this, o);
    }

    public int getRegion() {
        return this.region;
    }

    public int getRequestType() {
        return this.requestType;
    }

    public int getAtm() {
        return this.atmId;
    }
}
