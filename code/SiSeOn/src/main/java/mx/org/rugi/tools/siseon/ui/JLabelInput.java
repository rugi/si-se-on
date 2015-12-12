/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.rugi.tools.siseon.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Componente que agrupa un JLabel y un JTextField para facilitar su manejo.
 *
 * @author rugi
 */
public class JLabelInput extends JPanel {

    /**
     *
     */
    private JLabel label;
    /**
     *
     */
    private JTextField input;
    /**
     *
     */
    private BorderLayout layout;

    /**
     *
     */
    public JLabelInput() {
        this("Value:");
    }

    /**
     *
     * @param label
     */
    public JLabelInput(String label) {
        this(label, "");
    }

    /**
     *
     * @param label
     * @param text
     */
    public JLabelInput(String label, String text) {
        this( new JLabel(label), new JTextField(text));
    }

    /**
     *
     * @param layout
     * @param label
     * @param input
     */
    private JLabelInput( 
            JLabel label,
            JTextField input) {
        super();
        this.layout = new BorderLayout();
        this.label = label;
        this.input = input;
        initComponents();
    }

    /**
     *
     */
    private void initComponents() {
        this.setLayout(layout);
 
        this.add(label, BorderLayout.WEST);
        this.add(input, BorderLayout.CENTER);
        this.validate();
    }

    /**
     *
     * @return
     */
    public String getTextInput() {
        return this.input.getText();
    }

    /**
     *
     * @param al
     */
    public void addActionTextInput(ActionListener al) {
        this.input.addActionListener(al);
    }
}
