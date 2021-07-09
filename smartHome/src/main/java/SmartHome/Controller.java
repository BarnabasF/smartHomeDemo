package SmartHome;


import org.json.JSONException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
public static String id=null;
public static Loader betolto_1;
public static Monitor monitor_1;
public static int hour=0;
public static List <Temperature> temperatures;
public static int index=0;
public static Driver driver_1;
public static boolean boilerBool,acBool;



    public static void main(String[] args) throws IOException, JSONException {

       //program("KD34AF24DS");
        betolto_1= new Loader();
        betolto_1.loadSubscribers();

        Timer t = new Timer( );
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                try {
                    for(Subscriber i: betolto_1.lista.subscribers)
                    {
                        program(i.homeId);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }, 1000,300000);
        //1000 means 1 second delay before get executed & 5000 means will be repeated every 5 seconds.
        //
        //To stop it , simply call t.cancel()


    System.out.println("End of program...");
    }

    public static void program(String id) throws JSONException, IOException {

        monitor_1 = new Monitor();
        monitor_1.loadSession(id);
        System.out.println("sessionId: "+monitor_1.session.getSessionId()+"\n"+"temperature: "+ monitor_1.session.temperature);
        setHour();// beállitjuk a mostani időt
        index=subscriberIndexFromID(id);

        if (index==-19){System.out.println("ERROR, WRONG ID");}
        else {
            temperatures = betolto_1.lista.subscribers.get(index).getTemperatures();
        }

        double targetTemp=targetTemperature();
        System.out.println("Cálhőmérséklet: "+targetTemp);

        driver_1=new Driver();

        boilerBool=getBoilerBool(targetTemp,monitor_1.session.temperature);
        acBool=getAcBool(targetTemp,monitor_1.session.temperature);

        System.out.println(driver_1.sendCommand(betolto_1.lista.subscribers.get(index),boilerBool,acBool));
    }

    public static double targetTemperature(){
        int from=0,to=0;

        for(Temperature i : temperatures){
            String[] array= i.period.split("-");

            from=Integer.parseInt(array[0]);
            to=Integer.parseInt(array[1]);

            if(hour >= from     &&      hour <= to)
            {
                return i.temperature;
            }
        }
        return -19;
    }

    public static int subscriberIndexFromID(String homeID)  {
        int index=0;
        for(Subscriber i : betolto_1.lista.subscribers) {
            if(i.homeId.equals(homeID)){
                return index;
            }
            index++;
        }
        return -19;

    }

    public static void list_SubscribersInSystem(){
        for(Subscriber i : Controller.betolto_1.getLista().subscribers)
        {
            System.out.println(i.getSubscriber());
            System.out.println(i.getHomeId());
            System.out.println(i.getAirConditionerType());
            System.out.println(i.getBoilerType()+"\n \n");
        }

    }

    public static void setHour(){
        Date date = new Date();
        System.out.println("pillanatnyi óra: "+date.getHours());
        hour=date.getHours();
    }

    public static boolean getBoilerBool(double target, double current) {
        if(target>current && (target-current)>=0.2){return true;}
        else {return false;}
    }

    public static boolean getAcBool(double target, double current) {
        if(target<current && (current-target)>=0.2){return true;}
        else {return false;}
    }

}
