package com.pantxi.calculator;
import org.jetbrains.annotations.NotNull;

import static java.lang.Math.abs;

public class Rationnel {
    /* définition du type Rationnel et mise à disposition (publique)
        - de getters
        - d'un opérateur d'addition
        - d'une primitive d'E/S (afficher())
        En privé, utilise la méthode réduire permettant de retourner
        la fraction irréductible après un calcul .
     */
    private int num; // numérateur, porte le signe de la fraction
    private int den; // dénominateur, > 0

    // Constructeur
    public Rationnel(int num, int den) throws RuntimeException{
        if (den == 0) {
            throw new RuntimeException();
        } else if (den > 0) {
            this.num = num;
            this.den = den;
        } else {
            this.num = -num;
            this.den = -den;
        }
       reduire();
    }

    // Accesseurs
    public int getNum() {
        return num;
    }

    public int getDen() {
        return den;
    }

    // Constante zero
    public static Rationnel FRACTION_NULLE = new Rationnel(0, 1);

    // Opérateurs publics
    public Rationnel additionner(Rationnel rat) {
        // Retourne la fraction irréductible de this + rat
        Rationnel somme;            // somme de this et rat,

        // retourner la fraction irréductible associée

        // Calculer la somme
        somme = new Rationnel(getNum() * rat.getDen() + rat.getNum() * getDen(),
                          getDen() * rat.getDen());

        somme.reduire();
        return somme;
    }

    // Opérateurs d'entrée / sortie
    public void afficher() {
        System.out.println( " " + num +  "/" + den + " ") ;
    }
    // affiche à l'écran la fraction this

    //Utilitaires privés
    private void reduire() {
        // retourne la fraction irréductible associée à this
        Rationnel fractionReduite;  // dont on retourne la valeur

        if (num == 0)          // Cas particulier où le numérateur est NUL
        {
           den = 1;
        } else {
            // Calculer le PGCD du numérateur et dénominateur (pour éviter d'appeler 2 fois la fonction de calcul)
            int lePgcd = pgcd(num, den); // le plus gd commun diviseur du numérateur et du dénominateur

            // Calculer la fraction réduite
            num = num / lePgcd;
            den = den / lePgcd;
        }
    }

    private int pgcd(int entierA, int entierB)
    {
        // pré-condition : au moins un des deux entiers est diff�rent de 0
        // post-condition : le pgcd est > 0

        // Stratégie : application de l'algorithme d'Euclide
        // si entierA > entierB > 0, alors PGCD(entierA, entierB) = PGCD(entierB, reste)
        // on reste est le reste de la division euclidienne de entierA par entierB : entierA = quotient*entierB + reste
        // le PGCD est le dernier reste non nul

        int lePgcd;    // le plus grand entier > 0 divisant à la fois entierA et entierB
        // valeur à retourner
        int reste ;	// reste de la division entière de a par b

        // entierA, entierB >> transformer en nbre positif >> a >= 0, b >= 0
        int a = abs(entierA);
        int b = abs(entierB);


        // a >= 0, b >= 0 (mais pas les 2 nuls en même temps >> Calculer PGCD >> lePgcd
        if(a == 0)        // si un des entiers est nul, le PGCD est l'autre entier
        {
            // suppose que la pré-condition vérifiée !!
            lePgcd = b;
        }
        else if (b ==0)
        {
            lePgcd = a;
        }
        else    // les 2 entiers sont différents de 0
        {
            // a > 0, b > 0 >> Ordonner les 2 nombres >> a >= b > 0
            if(a < b)
            {
                // échange des rôles
                echanger(a,b);
            }

            // a >= b > 0 >> calcul du PGCD par algorithme d'Euclide >> lePGCD > 0
            while (true)
            {
                // calcul reste
                reste = a % b;

                if (reste == 0)
                {
                    lePgcd = b;
                    break;
                }

                // préparer le tour suivant
                a = b;
                b = reste;
            }
        }
        return lePgcd;
    }

    private void echanger (int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }
}