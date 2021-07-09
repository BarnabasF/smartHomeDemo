package SmartHome;

public class Session {
    public String sessionId;
    public double temperature;
    public boolean boilerState;
    public boolean airConditionerState;

    public Session(String sessionId, double temperature, boolean boilerState, boolean airConditionerState) {
        this.sessionId = sessionId;
        this.temperature = temperature;
        this.boilerState = boilerState;
        this.airConditionerState = airConditionerState;
    }

    public Session() {
    }



    public String getSessionId() {
        return sessionId;
    }

    public double getTemperature() {
        return temperature;
    }

    public boolean isBoilerState() {
        return boilerState;
    }

    public boolean isAirConditionerState() {
        return airConditionerState;
    }
}
