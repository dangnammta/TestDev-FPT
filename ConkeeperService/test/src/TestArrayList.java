
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
class Hocsinh {

    public String ten;
    public int tuoi;
}

public class TestArrayList {

    ArrayList<Hocsinh> hs = new ArrayList<>();
    public List add() {
        

        Hocsinh a = new Hocsinh();
        a.ten =" viet anh";
        a.tuoi = 12;
        hs.add(a);
        Hocsinh b = new Hocsinh();
        b.ten =" viet anh 2";
        b.tuoi = 14;
        hs.add(b);
        Hocsinh c = new Hocsinh();
        c.ten =" viet anh 3";
        c.tuoi = 14;
        hs.add(c);
//        for (int i = 0; i < hs.size(); i++) {
//            if (hs.get(i).tuoi == 12) {
//                System.out.println(hs.get(i).ten);
//            }
//        }
    return hs;
    }
    public void ktra(List<Hocsinh> list){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).tuoi == 12) {
                System.out.println(list.get(i).ten);
            }
        }
    }

    public static void main(String[] args) {
        TestArrayList t = new TestArrayList();
        List<Hocsinh> list = t.add();
        t.ktra(list);
        System.out.println(t.hs);

    }
}
