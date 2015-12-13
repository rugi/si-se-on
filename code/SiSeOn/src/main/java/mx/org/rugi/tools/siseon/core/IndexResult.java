/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.rugi.tools.siseon.core;

/**
 *
 * @author rugi
 */
public class IndexResult {

    private StringBuilder message;

    /**
     *
     */
    public IndexResult() {
        super();
        this.message = new StringBuilder();
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message.toString();
    }

    /**
     * @param message the message to set
     */
    public void appendMessage(String message) {
        this.message.append(message);
        this.message.append("\n");
    }
}
