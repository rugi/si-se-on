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
public class IndexItem implements IArray {

    /**
     *
     */
    private String name;
    /**
     *
     */
    private String path;
    /**
     *
     */
    private String lenght;
    /**
     *
     */
    private String modified;

    public IndexItem() {
        super();
    }

    /**
     *
     * @return
     */
    @Override
    public Object[] toArray() {
        Object[] r = new Object[4];
        r[0] = this.name;
        r[1] = this.path;
        r[2] = this.lenght;
        r[3] = this.modified;
        return r;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the lenght
     */
    public String getLenght() {
        return lenght;
    }

    /**
     * @param lenght the lenght to set
     */
    public void setLenght(String lenght) {
        this.lenght = lenght;
    }

    /**
     * @return the modified
     */
    public String getModified() {
        return modified;
    }

    /**
     * @param modified the modified to set
     */
    public void setModified(String modified) {
        this.modified = modified;
    }

}
