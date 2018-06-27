
import com.sun.org.apache.xerces.internal.xs.PSVIProvider;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vietanh
 */
public class testdate {
//    public Date d2 = "01/14/2017 09:29:58";

    public static String a = "ssss";
//    public Date d2 = new Date(2017, 12, 12, 12, 12, 12);
    public static long d3 = new Date(2017, 01, 19).getTime();

    public static void main(String[] args) {
        TestDate1 date1 = new TestDate1();
        System.out.println(d3);
        Date d =  new Date();
        
        System.out.println(d);
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat();
        String str = dateFormat.format(d);
        System.out.println(str);
        
//        System.out.println(a);
    }
//    public static void main(String[] args) {
//        Date d = new Date();
//        Date d3 = new Date(12,12,12);
//        System.out.println(d);
//        Date d1 = new Date(2017, 12, 12,12,12,12);
//        long diff = d.getTime()- d3.getTime();
//        
//        System.out.println(diff);
//    }

}
