/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.rugi.tools.siseon;

import javax.swing.JFrame;
import mx.org.rugi.tools.siseon.ui.SiSeOnFrame;

/**
 *
 * @author rugi
 */
public class Main {

    private static void createAndShowGUI() {
        //Create and set up the window.
        SiSeOnFrame frame = new SiSeOnFrame("Simple Search In PDF Docs. Powered by LUCENE.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
