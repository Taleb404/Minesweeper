/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswipper;

import java.io.Serializable;

/**
 *
 * @author Taleb
 */
public class Shield   implements Serializable{
        private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
