package com.dantehemerson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
    private JRadioButton primeraOcurrenciaRB;
    private JRadioButton todasLasOcurrenciasRB;
    private JTextField textInput;
    private JTextArea patronOutput;
    private JLabel timeOutput;
    private JScrollPane tablaDeFallosPanel;
    private JLabel labelTablaFallos;

    private String T;
    private String P;
    private int[] F;
    private KMP kmp;
    private FuerzaBruta FB;


    public App() {
        kmp = new KMP();
        FB = new FuerzaBruta();

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscar();
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


        /* Cuando se presione enter en este componente el foco se colocara en patronInput*/
        textInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    patronInput.requestFocus();
                }
            }
        });
        patronInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buscar();
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
        // Si la cadena tiene una longitud menor a 2, esto ocurre solo si esta en el modo KMP
        else if(searchMetod.getSelectedIndex() == 0 && patronInput.getText().length() < 2) {
            JOptionPane.showMessageDialog(null, "La longitud del patron debe ser mayor o igual a 2");
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

    private void escribirDatosKmp() {
        patronOutput.setText("");
        patronOutput.append("  " + P + "\n");
        patronOutput.append(" " + kmp.obtenerTablaString());

        positionsOutput.setText(kmp.obtenerPosicionesString());
        timeOutput.setText("Tiempo : " + Integer.toString(kmp.obtenerTiempoTotal()));
        processOutput.setText(kmp.obtenerProcedimiento());

    }


    private void buscar() {
        if(obtenerDatosDeEntrada()) { // Ahora ya se ingreso el texto(T) y el patron(P) correctamente.
            if(searchMetod.getSelectedIndex() == 0) { // Metodo KMP
                kmp.generarBusquedas(T, P);
                escribirDatosKmp();
            } else {
                tablaDeFallosPanel.hide();
                FB.buscar(T, P);

                if(FB.getPosiciones().isEmpty()) {
                    positionsOutput.setText("No se encontraron ocurrencias");
                } else {

                    if(primeraOcurrenciaRB.isSelected()) {
                        positionsOutput.setText(FB.getPosiciones().get(0).toString());
                    }
                    else{
                        positionsOutput.setText(FB.getPosicionesString());
                    }
                }

                timeOutput.setText("Tiempo : " + FB.getTiempo());

                processOutput.setText("Nada que mostrar");
                patronOutput.setText("");
            }

        }
    }
}
