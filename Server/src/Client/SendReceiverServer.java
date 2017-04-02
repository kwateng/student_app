/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Client;

import java.io.Serializable;

/**
 *
 * @author Kavindya
 */
public class SendReceiverServer implements Serializable{
private String title;
private Object object;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the object
     */
    public Object getObject() {
        return object;
    }

    /**
     * @param object the object to set
     */
    public void setObject(Object object) {
        this.object = object;
    }
}
