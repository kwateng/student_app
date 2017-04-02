/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clientserver;

import java.io.Serializable;

/**
 *
 * @author Kavindya
 */
public class Student implements Serializable {
    private String name;
    private String index;

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
     * @return the index
     */
    public String getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(String index) {
        this.index = index;
    }

}
