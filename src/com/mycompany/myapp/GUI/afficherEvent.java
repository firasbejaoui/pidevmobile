/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Entitie.event;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.service.eventService;
import com.mycompany.myapp.utils.Mail;

/**
 *
 * @author ASUS
 */
public class afficherEvent extends Form {
    Form current;
    
      public afficherEvent(Form previous){
        
        current = this;
       
              setTitle("List des réfugiés");
        setLayout(BoxLayout.y());
       
        for (event w : new eventService().getAllClub())
        {
           
            this.add(affichage(w));
        }
        this.show();
            this.getToolbar().addCommandToLeftBar("back", MyApplication.theme.getImage("back-command.png"), ev->{
      new ajouterEvent(this).show();
        });
              FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);

fab.bindFabToContainer(getContentPane());

fab.createSubFAB(FontImage.MATERIAL_IMPORT_CONTACTS, "envoie mail").addActionListener(e->{
Form hi=new Form();

 Mail m=new Mail();
   hi.add(m.createDemo());
   hi.show();
});  
          
    }
    
        
    public Container affichage(event c){
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Button btn=new Button("Nom : "+c.getNoun());
         Label lab=new Label("Description :"+c.getDescription());
        
        cn2.add(btn).add(lab);
        cn1.add(BorderLayout.WEST, cn2);
        //cn1.add(BorderLayout.CENTER,labnbr);
     //supprimer   
        btn.addActionListener(e->{
            Form f2=new Form("Details",BoxLayout.y());
          TextField  tfNom=new TextField(c.getNoun());
          TextField  tfPrenom=new TextField(c.getDescription());


          Button btn_modif=new Button("modifier");
           Button btn_sup=new Button("supprimer");
         f2.add("Event").add("Nom : ").add(tfNom).add("Description : ").add(tfPrenom).add(btn_sup).add(btn_modif);
     btn_sup.addActionListener(ww ->
     
     {
         new eventService().suppEvent(c.getId());
         new afficherEvent(this).showBack();
     }
     
     );
          btn_modif.addActionListener(aa ->
     
     {                    

    
         
         c.setNoun(tfNom.getText());
         c.setDescription(tfPrenom.getText());
      
         new eventService().modifierEvent(c);
         new afficherEvent(this).showBack();
     }
     
     );
            f2.getToolbar().addCommandToLeftBar("back", null, ev->{
            this.show();
        });
                   f2.show();
        });
        
        cn1.setLeadComponent(btn);
        return cn1;
                
    }
    
    

    
        
}
