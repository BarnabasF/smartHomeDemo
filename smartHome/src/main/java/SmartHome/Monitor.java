package SmartHome;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class Monitor implements IMonitor{
    Session session;

    @Override
    public Session getSession(String id) {
        return this.session;
    }

    public void loadSession(String  homeID) throws IOException, JSONException {

        String url = "http://193.6.19.58:8182/smarthome/"+homeID;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(response);

        JSONObject myobj= new JSONObject(response.toString());
        session= new Session
                (myobj.getString("sessionId"),
                (myobj.getDouble("temperature")),
                (myobj.getBoolean("boilerState")),
                (myobj.getBoolean("airConditionerState")));
    }


}
