
import java.util.ArrayList;
import java.util.List;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vietanh
 */
class Xocsinh {

    public String ten[]=new String[2];
//    public String ten[];
    public int tuoi;
}

public class testarray1 {

    ArrayList<Xocsinh> xs = new ArrayList<>();

   
    public List add() {
        Xocsinh a = new Xocsinh();
        a.ten[1] ="vietanh6";
        a.ten[0]="vietanh5";
        a.tuoi = 12;
        xs.add(a);
//        Xocsinh b = new Xocsinh();
//        b.ten[1] = " viet anh 2";
//        b.tuoi = 14;
//        xs.add(b);
//        Xocsinh c = new Xocsinh();
//        c.ten[2] = " viet anh 3";
//        c.tuoi = 14;
//        xs.add(c);
        for (int i = 0; i < xs.size(); i++) {
            if (xs.get(i).tuoi == 12) {
                for(int j = 0 ; j < a.ten.length ; j++){
                    System.out.println(xs.get(i).ten[j]);
                }
            }
        }
        return xs;
          
    }
    public void ktra() {
//ArrayList<Xocsinh> list = new ArrayList<>();
       

    }
     public static void main(String[] args) {
        testarray1 t = new testarray1();
//        System.out.println(hs.set(1, hs));
        List list =t.add();
        
     }
}
