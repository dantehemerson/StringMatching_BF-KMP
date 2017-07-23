package com.dantehemerson;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Dante on 22/07/2017.
 */
public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("String Matching(BF - KMP)");
        frame.setContentPane(new App().getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));

        frame.pack();
        frame.setVisible(true);
    }
}
