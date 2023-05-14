package ingkonkurs.services.atmservice;

public enum RequestType {
    FAILURE_RESTART,
    PRIORITY,
    SIGNAL_LOW,
    STANDARD;

    public int toValue() {
        return ordinal();
    }
}
