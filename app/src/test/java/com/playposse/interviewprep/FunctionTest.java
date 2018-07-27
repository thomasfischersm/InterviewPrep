package com.playposse.interviewprep;

/**
 * Created by thoma on 7/24/2018.
 */
public class FunctionTest {

    private static void greet(MyFunc func) {
        System.out.println(func.doThis("Thomas"));
    }

    public static void main(String[] args) {
//        greet((name) -> "Bonjour " + name);
//        greet((name) -> "Good morning " + name);
    }

    private interface MyFunc {

        String doThis(String name);
    }
}
