/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.ui.Form;

/**
 *
 * @author HP
 */
public class lancerMobile extends Form {
    Form current;
    public lancerMobile(){
  
            this.getToolbar().addCommandToLeftSideMenu("Ajouter Refugié", null, ev->{
         new  ajouterEvent(current).show();
    });
    }
}