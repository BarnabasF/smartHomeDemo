package SmartHome;

import SmartHome.Subscriber;
import com.google.gson.Gson;

import java.util.List;

public interface ILoader {
    public List<Subscriber> subscribers = null;

    public abstract void loadSubscribers();


}
