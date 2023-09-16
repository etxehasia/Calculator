/*
 * Copyright 2015-2022 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package com.pantxi.calculator;


import java.util.HashSet;
import java.util.Set;

public class Calculator {

	public int add(int a, int b) {
		return a + b;
	}
	public int multiply(int a, int b) {
		return a * b;
	}

	public void longCalcul(int ms) {
		// attendre ms millisecondes
		try {
			Thread.sleep(ms);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}

	}

	public Set<Integer> ensembleChiffres(int pNombre) {
		Set<Integer> entiers = new HashSet<Integer>(); // ensemble d'entiers vide
		String chaineDuNombre = String.valueOf(pNombre); 	// équivalent du nbre en châine de caractères

		// Parcours de la chaîne et ajout de chaque élément dans l'ensemble
		for (int i=0; i<chaineDuNombre.length(); i++) {
			if (chaineDuNombre.charAt(i) != '-')
			{
				entiers.add(Integer.parseInt(chaineDuNombre, i, i+1, 10));
				/* retourne l'équivalent en entier de la portion de chaîne comprise dans l'intervalle [i, i+1[.
				   Lève une exception si la conversion en entier n'est pas possible.
				*/
			}
		}
		return entiers;
	}
}
