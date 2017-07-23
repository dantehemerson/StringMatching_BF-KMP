package com.dantehemerson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dante on 22/07/2017.
 */
public class App {
    private JPanel mainPanel;
    private JButton searchBtn;
    private JTextField patronInput;
    private JComboBox searchMetod;
    private JTextArea processOutput;
    private JTextField positionsOutput;
    private JRadioButton primeraOcurrenciaRadioButton;
    private JRadioButton todasLasOcurrenciasRadioButton;
    private JTextField textInput;
    private JTextArea patronOutput;
    private JLabel timeOutput;
    private JScrollPane tablaDeFallosPanel;
    private JLabel labelTablaFallos;

    private String T;
    private String P;
    private int[] F;

    public App() {

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(obtenerDatosDeEntrada()) { // Ahora ya se ingreso el texto(T) y el patron(P) correctamente.

                    if(searchMetod.getSelectedIndex() == 1) {
                        tablaDeFallosPanel.hide();
                    }
                    escribirTablaDeFallos();

                }

            }
        });

        searchMetod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(searchMetod.getSelectedIndex() == 1) {
                    tablaDeFallosPanel.hide();
                    labelTablaFallos.setText("_______________________________________________________________________________");
                }
                else {
                    tablaDeFallosPanel.show();
                    labelTablaFallos.setText("TABLA DE FALLOS : _____________________________________________________________");


                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }


    private boolean obtenerDatosDeEntrada() {
        if(textInput.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Tienes que ingresar una cadena");
            textInput.requestFocus();
            return false;
        }
        else if(patronInput.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Tienes que ingresar una Patron");
            patronInput.requestFocus();
            return false;
        }
        else {
            // Ahora ya se ingreso el texto(T) y el patron(P) correctamente.
            T = textInput.getText();
            P = patronInput.getText();
        }
        return true;
    }

    private void escribirTablaDeFallos() {
        F = KMP.generarTablaKMP(P);
        String salida = " ";
        for(int i : F) {
            salida += Integer.toString(i);
        }
        patronOutput.setText("");
        patronOutput.append("  " + P + "\n");
        patronOutput.append(salida);

    }


}
