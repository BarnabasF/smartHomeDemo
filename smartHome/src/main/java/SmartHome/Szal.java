package SmartHome;


import org.json.JSONException;

import java.io.IOException;

import static SmartHome.Controller.betolto_1;
import static SmartHome.Controller.program;

public class Szal implements Runnable {

    java.lang.Thread t;
    String threadName;
    public Boolean running=true;
    Loader betolto;

    public Szal(String threadName_)
    {
        this.threadName=threadName_;
    }

    public void run() {
        while (running){
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

            try {
                java.lang.Thread.sleep(300000);// 5percenként fut le az ellenőrzés
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start(){
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }

    void stop(){
        running=false;
    }
}
