package SmartHome;

public interface IMonitor {
    Session session=null;

    public abstract Session getSession(String id);

}
