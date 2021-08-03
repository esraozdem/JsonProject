import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadDataFromJSONFile {


    public static void main(String[] args) throws IOException, ParseException {
     JSONParser jsonParser = new JSONParser();
     FileReader fileReader = new FileReader("./jsonfiles/employee.json");

   Object obj= jsonParser.parse(fileReader);


        JSONObject emptyJsonObj =(JSONObject)obj;

        String fname = (String)emptyJsonObj.get("firstname");

        String lname= (String)emptyJsonObj.get("lastname");

        JSONArray array = (JSONArray)emptyJsonObj.get("address");

        for (int i = 0; i < array.size(); i++) {


            JSONObject address = (JSONObject)array.get(i);

            String street = (String)address.get("street");
            String city = (String)address.get("city");

            String  state = (String)address.get("state");

            System.out.println("Address of " + i + "");
            System.out.println(street);
            System.out.println(city);
            System.out.println(state);



//study
            //conflict






        }
    }
}
