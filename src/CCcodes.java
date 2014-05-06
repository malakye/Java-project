/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import flexjson.*;
import java.nio.file.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author jeffj
 */
public class CCcodes {
    
    private static String cwd;
    private static final String path = "config.js";
    private static String configFile; 
    private List stdMap;
    private class CountryCodes {
        String Country;
        String Units;
    }
    
    public CCcodes(){
        readFile(path);
    }
    
    private void readFile(String path) {
        
        byte[] rawFile;  // read the entire raw configuration file into this var
        
        try {
            cwd = new File( "." ).getCanonicalPath();  // grab the current working directory
            rawFile = Files.readAllBytes(Paths.get(path));
            configFile = new String(rawFile);
            JSONDeserializer<List<CountryCodes>> deserializer = new JSONDeserializer<List<CountryCodes>>();
            stdMap = deserializer.deserialize(configFile);
            System.out.println(stdMap);
        } catch(Exception e) {
            System.out.println(e.toString());
            System.out.println("Something went wrong! Missing file: "+cwd+"/"+path+"?");
        }
    }
    public String[] getUnits(){
        String temp[] = new String[stdMap.size()];
        ArrayList<String> units;
        //for each in stdMap
        //        units.add(each.Units);
        
       // temp = units.toArray(temp);
        return temp;
    }
}
