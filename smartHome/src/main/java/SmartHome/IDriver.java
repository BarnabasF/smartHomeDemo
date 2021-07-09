package SmartHome;

public interface IDriver {

    public abstract int sendCommand(Subscriber subs, Boolean boiler, Boolean ac);
}
