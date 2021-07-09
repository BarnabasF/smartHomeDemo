package SmartHome;

import java.util.List;

public class Subscriber{
    public String getSubscriber() {
        return subscriber;
    }

    public String getHomeId() {
        return homeId;
    }

    public String getBoilerType() {
        return boilerType;
    }

    public String getAirConditionerType() {
        return airConditionerType;
    }

    public List<Temperature> getTemperatures() {
        return temperatures;
    }

    public String subscriber;
    public String homeId;
    public String boilerType;
    public String airConditionerType;
    public List<Temperature> temperatures;
}

