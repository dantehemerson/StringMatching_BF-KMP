package com.dantehemerson;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Dante on 25/07/2017.
 */
public class FuerzaBruta {

    private int tiempo;
    ArrayList<Integer> posiciones;

    public FuerzaBruta() {
        posiciones = new ArrayList<>();
    }

    public int obtenerTiempo() {
        return 0;
    }

    public ArrayList<Integer> getPosiciones() {
        return posiciones;
    }

    public String getPosicionesString() {
        String res = "";
        for(int i = 0; i < posiciones.size(); i++) {
            res += Integer.toString(posiciones.get(i)) + ", ";
        }

        return (res.isEmpty() ? "No se encontro ocurrencias" : res);
    }

    public int getTiempo() {
        return tiempo;
    }


    boolean buscar(String T, String P) {
        posiciones.clear();
        tiempo = 0;
        int k, i, j;
        for(i = 0; i <= (T.length() - P.length()) ; i++) {
            k = i;
            j = 0;
            while ((j < P.length() && k < T.length()) && T.charAt(k) == P.charAt(j) && j <= P.length()) {
                tiempo++;
                k++;
                j++;
            }
            if(j > P.length() - 1) {
                posiciones.add(i + 1);
                return true;

            }
        }
        return !posiciones.isEmpty();
    }


    boolean buscarTodos(String T, String P) {
        posiciones.clear();
        tiempo = 0;
        int k, i, j;
        for(i = 0; i <= (T.length() - P.length()) ; i++) {
            k = i;
            j = 0;
            while ((j < P.length() && k < T.length()) && T.charAt(k) == P.charAt(j) && j <= P.length()) {
                tiempo++;
                k++;
                j++;
            }
            if(j > P.length() - 1) {
                posiciones.add(i + 1);

            }
        }
        return !posiciones.isEmpty();
    }


}










