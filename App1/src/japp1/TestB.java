package japp1;

import java.util.Arrays;
import java.util.List;

public class TestB extends JApp1{
    
    int i = 2;
   String s = "pqr";
//    public void m1(){
//         System.out.println("child");
//    }
     public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3");
        list.forEach(System.out::println);
        list.forEach(s -> System.out.println(s));
        
        JApp1 o = new TestB();
         System.out.println(o.i);
         o.m1();
         o.s = "xyz";
         System.out.println(o.s);
    }
}
