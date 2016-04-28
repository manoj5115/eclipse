package japp1;

import java.util.Arrays;
import java.util.List;

public class JApp1 {

    String s = "abc2";
    int i = 1;
    public void m1(){
         System.out.println("parent");
    }
    
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3");
        list.forEach(System.out::println);
        list.forEach(s -> System.out.println(s));
        call(a -> System.out.println("m2 called - " + a));
    }
    
    static void call(Test t){
        t.m2(21);
    }
    
    interface Test {
        public void m2(int a);
    }
}
