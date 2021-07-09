package SmartHome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Driver implements IDriver{
    String boilerCommand="";
    String acCommand="";


    public int sendCommand(Subscriber sub, Boolean boiler, Boolean ac){


        boilerCommand=getBoilerCommand(sub,boiler);

        acCommand=getAcCommand(sub,ac);
        System.out.println("POST to: "+"http://193.6.19.58:8182/smarthome/"+ sub.homeId+ "\n" +"{" +"\"homeId\":\""+sub.homeId +"\","
                +"\"boilerCommand\":\""+ boilerCommand+"\"," + "\"airConditionerCommand\":\""+ acCommand+"\"" +
                "}");


        URL url = null;
        try {
            url = new URL("http://193.6.19.58:8182/smarthome/"+ sub.homeId);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestProperty("Content-Type", "text/plain; utf-8");
            con.setRequestProperty("Accept", "text/plain");
            con.setDoOutput(true);
            String jsonInputString = "{" +
                    "\"homeId\":\""+sub.homeId +"\"," +
                    "\"boilerCommand\":\""+ boilerCommand+"\"," +
                    "\"airConditionerCommand\":\""+ acCommand+"\"" +
                    "}";

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return 0;
    }

    public String getBoilerCommand(Subscriber sub,boolean Start) {
        String commandReturn="";
        if(Start){
        switch(sub.getBoilerType())
        {
            case "Boiler 1200W":
                commandReturn="bX3434";
                break;
            case "Boiler p5600":
                commandReturn="cX7898";
                break;
            case "tw560":
                commandReturn="dX3422";
                break;
            case "Boiler 1400L":
                commandReturn="kx8417";
                break;
            default:
                commandReturn="";
        }
        }else {
            switch(sub.getBoilerType())
            {
                case "Boiler 1200W":
                    commandReturn="bX1232";
                    break;
                case "Boiler p5600":
                    commandReturn="cX3452";
                    break;
                case "tw560":
                    commandReturn="dX111";
                    break;
                case "Boiler 1400L":
                    commandReturn="kx4823";
                    break;
                default:
                    commandReturn="";
            }

        }

        return commandReturn;
    }

    public String getAcCommand(Subscriber sub,boolean Start) {

        String commandReturn = "";
        if (Start) {
            switch (sub.airConditionerType) {
                case "Air p5600":
                    commandReturn = "bX5676";
                    break;
                case "Air c3200":
                    commandReturn = "cX3452";
                    break;
                case "Air rk110":
                    commandReturn = "eX1111";
                    break;
                default:
                    commandReturn = "";
            }
        } else {
            switch (sub.airConditionerType) {
                case "Air p5600":
                    commandReturn = "bX3421";
                    break;
                case "Air c3200":
                    commandReturn = "cX5423";
                    break;
                case "Air rk110":
                    commandReturn = "eX2222";
                    break;

                default:
                    commandReturn = "";
            }

        }

        return commandReturn;
    }
}
