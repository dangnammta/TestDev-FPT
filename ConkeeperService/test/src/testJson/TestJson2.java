/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testJson;

import com.sun.org.apache.xml.internal.serializer.utils.MsgKey;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author vietanh
 * read json read
 */
public class TestJson2 {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
    
        try {
            Object obj = parser.parse(new FileReader("/home/vietanh/Documents/Project/repos/FProjects/TestDev/ConkeeperService/test/src/testJson/test.json"));
            JSONObject jSONObject = (JSONObject) obj;
            System.out.println(jSONObject);
            String name = (String)jSONObject.get("name");
            System.out.println(name);
            long age = (long)jSONObject.get("age");
            System.out.println(age);
            JSONArray jsonarray = (JSONArray)jSONObject.get("message");
//            System.out.println(jsonarray);
            Iterator<String> iterator = jsonarray.iterator();
            while (iterator.hasNext()) {                
                System.out.println(iterator.next());
            }
        } catch (Exception e) {
            System.out.println("sai");
        }
    
    }
}
