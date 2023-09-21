package com.pantxi.calculator;

import org.junit.jupiter.api.*;
/*
L'instruction : import org.junit.jupiter.api.*     inclut (pour notre code) :
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 */
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;   // si on utilise les assertions de JUnit

class CalculatorTest {

    private Calculator calculatorEnTest;
    private static Instant startedAt;	// variable de classe

    @BeforeEach
    void setUp() {		// montage

        calculatorEnTest = new Calculator();
    }

    @AfterEach
    void tearDown() {	// démontage
        calculatorEnTest = null;
    }

    @BeforeAll
    static void suiteSetUp() {	// montage avant tous les tests
        System.out.println("Tic chrono avant tous les tests");
        startedAt = Instant.now();
    }

    @AfterAll
    static void suiteTearDown() {	// démontage après tous les tests
        System.out.println("Tic chrono apres tous les tests");
        Instant endedAt = Instant.now();
        long duree = Duration.between(startedAt, endedAt).toMillis();
        System.out.println("Duree des tests : " + duree + " millisecondes");
        System.out.println(MessageFormat.format("Duree des tests : {0} ms", duree));
    }

    @DisplayName("Tests de sommes de 2 int " )
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "0,    1,   1",
            "1,    2,   3",
            "-2,   2,   0",
            "0,    0,   0",
            "-1,   -2,  -3"
    })

    void add_parametre_devrait_calculer_la_somme_de_deux_int(int first, int second, int expectedResult) throws Throwable {
        // GIVEN

        // WHEN
        int somme = calculatorEnTest.add(first, second);

        // THEN  -- SI on ne s'occupe pas de l'exception levée

        //assertEquals(expectedResult, calculatorEnTest.add(first, second),
        //		() -> first + " + " + second + " should equal " + expectedResult);	// JUnit
        // assertThat(somme).isEqualTo(expectedResult);	                            // assertJ

        // THEN         -- si on s'occupe de l'exception levée
        Throwable erreur = catchThrowable(()->calculatorEnTest.add(first,second));
        assertThat(erreur).isNull();
    }
    @Test
    void add_devrait_calculer_la_somme_de_deux_int() {
        // GIVEN
        int opG = 1;
        int opD = 2;

        // THEN
        Throwable erreur = catchThrowable(()->calculatorEnTest.add(opG,opD));
        assertThat(erreur).isNull();
    }
    @Test
    void add_devrait_lever_une_exception_si_somme_hors_intervalle_des_int() {
        // GIVEN
        int opG = 1;
        int opD = Integer.MAX_VALUE;

        // THEN
        Throwable erreur = catchThrowable(()->calculatorEnTest.add(opG,opD));
        assertThat(erreur)
                .isNotNull()
                .isInstanceOf(RuntimeException.class)
                .hasMessage(erreur.getMessage());
    }

    @Timeout(1)	// en secondes
    @Test
    void longCalcul_devrait_durer_moins_d_1_seconde () {
        // GIVEN

        // WHEN
        calculatorEnTest.longCalcul(900); 	// en millisecondes

        // THEN
        // pas d'assertion
    }
    @Test
    void digitsSet_devrait_retourner_les_chiffres_d_un_entier_positif() {
        //GIVEN
        int entierPositif = 97689;

        //WHEN
        Set<Integer> ensembleChiffres = calculatorEnTest.ensembleChiffres(entierPositif);

        //THEN
        assertThat(ensembleChiffres).containsExactlyInAnyOrder(6, 7, 8, 9);
    }

    @Test
    void digitsSet_devrait_retourner_les_chiffres_d_un_entier_negatif() {
        //GIVEN
        int entierNegatif = -1;

        //WHEN
        Set<Integer> ensembleChiffres = calculatorEnTest.ensembleChiffres(entierNegatif);

        //THEN
        assertThat(ensembleChiffres).containsExactlyInAnyOrder(1);
    }
    @Test
    void digitsSet_devrait_retourner_le_chiffre_0_d_un_entier_nul() {
        //GIVEN
        int entierNul = 0000;

        //WHEN
        Set<Integer> ensembleChiffres = calculatorEnTest.ensembleChiffres(entierNul);

        //THEN
        assertThat(ensembleChiffres).containsExactlyInAnyOrder(0);
    }
}