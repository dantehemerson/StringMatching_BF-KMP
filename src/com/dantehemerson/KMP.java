package com.dantehemerson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dante on 23/07/2017.
 */

public class KMP {
    /**
     * @param S Cadena en donde se desea buscar.
     * @param W Cadena que se va a buscar.
     * @return Si la cadena se encontró retorna la primera posición empezando de la izuierda, de otro modo
     *         retorna -1.
     */
    private ArrayList<Integer> posiciones; // Arreglo con las posiciónes donde se encontro el patron
    String T; // Cadena
    String P; // Patron
    Integer[] F; // Tabla de falllos
    String FString; // Tabla de fallos en String


    public KMP() {
        posiciones = new ArrayList<>();

    }




    public static int buscar(String S, String W) {
        int m = 0;
        int i = 0;
        int[] T = generarTablaKMP(W); // Generando la tabla de fallos.

        if (S.length() >= W.length()) {
            while (m <= (S.length() - W.length())) {
                if (W.charAt(i) == S.charAt(m + i)) {
                    if (i == (W.length() - 1)) {
                        return m;
                    } else {
                        i++;
                    }
                } else {
                    m = m + i - T[i];
                    if (i > 0) {
                        i = T[i];
                    }
                }
            }
        }
        return -1;
    }

    /**
     * @param S Cadena en donde se desea buscar.
     * @param W Cadena que se quiere buscar.
     * @return Un arreglo con las posiciónes donde se encontró el patrón W.
     */
    public static ArrayList<Integer> buscarTodos(String S, String W) {
        int m = 0;
        int i = 0;
        int[] T = generarTablaKMP(W); // Generando la tabla de fallos.
        ArrayList<Integer> posiciones = new ArrayList<>();

        if (S.length() >= W.length()) {
            while (m <= (S.length() - W.length())) {
                if (W.charAt(i) == S.charAt(m + i)) {
                    if (i == (W.length() - 1)) {
                        posiciones.add(m);
                        m = m + i - T[i];
                        if (i > 0) {
                            i = T[i];
                        }
                    } else {
                        i++;
                    }
                } else {
                    m = m + i - T[i];
                    if (i > 0) {
                        i = T[i];
                    }
                }
            }
        }
        return posiciones;
    }

    public static int[] generarTablaKMP(String W) {
        // Creando el arreglo de enteros de tamaño longitud de la cadena para almacenar la tabla de fallo
        int[] T = new int[W.length()];

        int pos = 2;
        int cnd = 0;

        T[0] = -1;
        T[1] = 0;

        while (pos <= (W.length() - 1)) {
            if (W.charAt(pos - 1) == W.charAt(cnd)) {
                cnd++; // No se si se analiza antes??
                T[pos] = cnd;
                pos++;

            } else {
                if (cnd > 0) {
                    cnd = T[cnd];
                }
                T[pos] = 0;
                pos++;
            }
        }
        return T;
    }
}