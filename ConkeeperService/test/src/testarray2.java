
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vietanh
 */
public class testarray2 {
    ArrayList<Integer> a = new ArrayList<>();
    public void addall(){
        for (int i = 0; i < 10; i++) {
            a.add(i);
          
//            System.out.println(a);
        }
    }
    public void ktra(){
      for (int j = 0; j < a.size(); j++) {
                if (a.get(j).intValue() == 2) {
                    System.out.println(a);
                }
            }
    
    }
    public static void main(String[] args) {
        testarray2 b = new testarray2();
        b.addall();
        b.ktra();
    }
}
