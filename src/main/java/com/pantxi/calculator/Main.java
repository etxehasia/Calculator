package com.pantxi.calculator;

public class Main {
    public static void main(String[] args) throws Throwable {

        System.out.println("Hello World");
        Calculator c = new Calculator();
//        try {
            int somme = c.add(1,2147483647);
            System.out.println("1 + 2147483647 = " + somme);
            //        }
            /*
                    catch   (RuntimeException e) {
                        System.out.println("");
                    }
            */

    }
}


