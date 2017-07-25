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
    int[] F; // Tabla de falllos
    String FString; // Tabla de fallos en String
    int tiempoProcesamiento;
    int tiempoBuscar;
    String procedimiento;



    public KMP() {
        posiciones = new ArrayList<>();
        tiempoBuscar = tiempoProcesamiento = 0;
    }


    public void generarBusquedas(String S, String W) {
        F = generarTablaKMP(W);
        generarCadena();
        posiciones = buscarTodos(S, W);
    }

    private void generarCadena() {
        FString = "";
        for(int i : F) {
            FString += Integer.toString(i);
        }
    }

    public String obtenerTablaString() {
        return FString;
    }

    public int[] obtenerTabla() {
        return F;
    }

    public ArrayList<Integer> obtenerPosiciones() {
        return posiciones;
    }

    public String obtenerProcedimiento() {
        return procedimiento;
    }
    public String obtenerPosicionesString() {
        String pos = "";
        for(int i = 0; i < posiciones.size(); i++) {
            pos += Integer.toString(posiciones.get(i)) + ", ";
        }
        return pos;
    }

    public int obtenerTiempoTotal() {
        return tiempoBuscar + tiempoProcesamiento;
    }

    /**
     * @param S Cadena en donde se desea buscar.
     * @param W Cadena que se quiere buscar.
     * @return Un arreglo con las posiciónes donde se encontró el patrón W.
     */
    public ArrayList<Integer> buscarTodos(String S, String W) {
        int m = 0;
        int i = 0;
        int[] T = generarTablaKMP(W); // Generando la tabla de fallos.
        ArrayList<Integer> posiciones = new ArrayList<>();
        tiempoBuscar = 0;
        procedimiento = "";
        procedimiento += S;
        procedimiento += "\n";

        if (S.length() >= W.length()) {

            while (m <= (S.length() - W.length())) {
                tiempoBuscar++; // Aumenta el tiempo buscar
                if (W.charAt(i) == S.charAt(m + i)) {
                    procedimiento += S.charAt(m + i);
                    if (i == (W.length() - 1)) { // Encontró una ocurrencia
                        posiciones.add(m); // Añadiendo la posicion encontrada al arreglo de posiciones encontradas
                        m = m + i - T[i];
                        if (i > 0) {
                            i = T[i];
                        }

                        procedimiento += "\n";
                        for(int z = 0; z < m; z++) {
                            procedimiento += " ";
                        }
                    } else {
                        i++;
                    }
                } else {
                    procedimiento += W.charAt(i);
                    procedimiento += "\n";
                    m = m + i - T[i];
                    //salto para comparar tomar este
                    for(int z = 0; z < m; z++) {
                        procedimiento += " ";
                    }

                    if (i > 0) {
                        i = T[i];
                    }
                }
            }
        }
        return posiciones;
    }

    public int[] generarTablaKMP(String W) {
        // Creando el arreglo de enteros de tamaño longitud de la cadena para almacenar la tabla de fallo
        int[] T = new int[W.length()];


        int pos = 2;
        int cnd = 0;

        T[0] = -1;
        T[1] = 0;
        tiempoProcesamiento = 0; // Reinicia el tiempo de procesamiento.
        while (pos <= (W.length() - 1)) {
            tiempoProcesamiento++; // Aumenta el tiempo (cantidad de veces que compara)
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