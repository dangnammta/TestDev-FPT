/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testJson;

import java.io.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author vietanh
 * wrire json to file
 */
public class TestJson {
    public static void main(String[] args) {
        
        JSONObject obj = new JSONObject();
        obj.put("name", "vietanh");
        obj.put("age", new Integer(100));
        JSONArray list = new JSONArray();
        list.add("msg1");
        list.add("msg2");
        list.add("msg3");
        obj.put("message", list);
        try (FileWriter file = new FileWriter("/home/vietanh/Documents/Project/repos/FProjects/TestDev/ConkeeperService/test/src/testJson/test.json")) {
            file.write(obj.toJSONString());
            file.flush();
        } catch (Exception e) {
            System.out.println("sai roi");
        }
        System.out.println("xxx");
    }
}
