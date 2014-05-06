
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kye
 */
public class Parser {
    private ArrayList<String> countryList;
    private ArrayList<String> unitList;              
    
    public Parser() {
        unitList = new ArrayList<String>();
        countryList = new ArrayList<String>();
        String line;
        StringBuilder sb = new StringBuilder();
        try {
                InputStream is = this.getClass().getResourceAsStream("/config.json");
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                String jsonText = sb.toString();
 
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonText);
           
                JSONArray value= (JSONArray) jsonObject.get("values");          // get an array from the JSON object
                
                Iterator i = value.iterator();
 
                while (i.hasNext())                                    // take each value from the json array separately
                    {
                        JSONObject innerObj = (JSONObject) i.next();
                        String country = (String) innerObj.get("Country");
                        String unit = (String) innerObj.get("Units");
                        countryList.add(country);
                        unitList.add(unit);
                    }
            
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ParseException ex) {
                ex.printStackTrace();
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
    }
    
    public String[] getUnits(){
        return unitList.toArray(new String[unitList.size()]);
    }
    
    public String[] getCountries(){
        return countryList.toArray(new String[countryList.size()]);
    }
}
