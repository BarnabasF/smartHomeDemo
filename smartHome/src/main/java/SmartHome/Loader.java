package SmartHome;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Loader implements ILoader{

    public SubscribersList getLista() {
        return lista;
    }

    SubscribersList lista;

    //@Override
    public void loadSubscribers() {
        System.out.println("Load subscribers start");

        JsonReader reader=null;
        try {
            reader = new JsonReader(new FileReader("subscribers.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        lista =new Gson().fromJson(reader,SubscribersList.class);



        System.out.println("finished loading subscribers");
    }

}
