/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.rugi.tools.siseon.ui;

import java.awt.Dimension;
import javax.swing.JLabel;

/**
 *
 * @author rugi
 */
public class StatusBar extends JLabel {
    
    /** Creates a new instance of StatusBar */
    public StatusBar() {
        super();
        super.setPreferredSize(new Dimension(100, 16));
        setMessage("Ready");
    }
    
    /**
     * 
     * @param message 
     */
    public void setMessage(String message) {
        setText(" "+message);        
    }        
}
