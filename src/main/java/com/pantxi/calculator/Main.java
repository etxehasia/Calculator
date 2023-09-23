package com.pantxi.calculator;

public class Main {
    public static void main(String[] args) throws Throwable {

        System.out.println("Hello World");
        Calculator c = new Calculator();
        try {
            int somme = c.add(1, 2147483647);
            System.out.println("1 + 2147483647 = " + somme);
        }
        catch   (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        Rationnel r1 = new Rationnel(2, 4);
        Rationnel r2 = new Rationnel(2, 8);
        r1.afficher();
        r2.afficher();
        Rationnel sommeRat = c.add(r1, r2);
        sommeRat.afficher();

    }
}


