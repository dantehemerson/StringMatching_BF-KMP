package com.dantehemerson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dante on 22/07/2017.
 */
public class App {
    private JPanel mainPanel;
    private JButton searchBtn;
    private JTextField textToSearch;
    private JComboBox searchMetod;
    private JTextArea processOutput;
    private JTextField positionsOutput;
    private JRadioButton primeraOcurrenciaRadioButton;
    private JRadioButton todasLasOcurrenciasRadioButton;
    private JTextField text;
    private JTextArea failureFunctionOutput;
    private JLabel timeOutput;

    public App() {



        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processOutput.setText("Hola fui presionado");
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
