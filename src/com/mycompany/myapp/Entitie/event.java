/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Entitie;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class event {
       private int id;

    private String noun;
    private String Description;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoun() {
        return noun;
    }

    public void setNoun(String noun) {
        this.noun = noun;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public event(String noun, String Description) {
        this.noun = noun;
        this.Description = Description;
    }

    public event() {
    }
    


}
