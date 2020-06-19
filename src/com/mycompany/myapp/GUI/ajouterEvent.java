/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.Entitie.event;
import com.mycompany.myapp.service.eventService;

/**
 *
 * @author ASUS
 */
public class ajouterEvent extends Form{
    
    Form current;
    public ajouterEvent (){
        
    }
    
    
    public ajouterEvent(Form previous) {
        current=this;
        setLayout(BoxLayout.y());
      getToolbar().addMaterialCommandToLeftSideMenu("back", 
                FontImage.MATERIAL_BACKUP, ev->{new afficherEvent(current).show();});
          getToolbar().addCommandToLeftSideMenu("Exit",
            null, ev->{Display.getInstance().exitApplication();});
          
            this.getToolbar().addCommandToLeftSideMenu("Ajouter Event", null, ev->{
         new  ajouterEvent (current).show();
    });
        Style catRecStyle= getAllStyles();
        //catRecStyle.setBgColor(0xAACDFC);
        
        catRecStyle.setBgColor(0xefc2c2);
              setTitle("Ajout des events ");
        TextField tfnom= new TextField("","Saisir le nom d'evenement");
        TextField tfprenom=new TextField("","Saisir la description");
       
        
       // System.out.println("why"+listCamp.get(0).getNomCamp());


        Button ajouter=new Button("ajouter");
        //style button
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        Style butStyle5=ajouter.getAllStyles();
        butStyle5.setBorder(RoundRectBorder.create().
        strokeColor(0).
        strokeOpacity(120).
        stroke(borderStroke));
        butStyle5.setBgColor(0xff6666);
        butStyle5.setBgTransparency(255);
        butStyle5.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle5.setMargin(Component.BOTTOM, 0);       
        butStyle5.setMargin(Component.TOP,0);           
        butStyle5.setMargin(Component.LEFT,0);  
        butStyle5.setMargin(Component.RIGHT,0); 
        
                    this.getToolbar().addCommandToRightBar("List", null, ev->{
         new  afficherEvent(current).show();
        });
        
        ajouter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length()==0)||(tfnom.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
      
                else
                {
                    
    }
                        event t = new event(tfnom.getText(),tfprenom.getText());
                        if( eventService.getInstance().addEvent(t))
                        { Dialog.show("Success","Connection accepted",new Command("OK"));
             
                            
                        }
                           
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                    
                }  
            
            
        });
       
        
        
        current.addAll(tfnom,tfprenom,ajouter);
     //   current.addScrollListener((ScrollListener) tfcamp);
                
        current.show();

        
    }
    
}
