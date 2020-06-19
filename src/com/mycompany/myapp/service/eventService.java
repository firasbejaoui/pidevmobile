/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.Entitie.event;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class eventService {
     public ConnectionRequest req;
         public static eventService  instance=null;

          public ArrayList<event> listClub;
String URL="http://localhost/unt/web/app_dev.php/";
         
  public boolean resultOK;
         public ArrayList<event> tasks;
         public event e=new event();
     
    public eventService() {
        req = new ConnectionRequest();    
    }
           
     public static eventService getInstance() {
        if (instance == null) {
            instance = new eventService();
        }
        return instance;
    }  
     public ArrayList<event> parseEvent(String jsonText)throws Exception{
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.print("size :"+list.size());
            for(Map<String,Object> obj : list){
                event t = new event();
                float id = Float.parseFloat(obj.get("id").toString());
               
                t.setId((int)id);
                t.setNoun(obj.get("nameEvent").toString());
                t.setDescription(obj.get("description").toString());
             
                tasks.add(t);
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return tasks;
    }
     
     
  
         public boolean addEvent(event v) {
        String url =URL+"ajoutM?noun="+v.getNoun()+"&description="+v.getDescription(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
         
             
     public ArrayList<event> suppEvent(int id){
        String url =URL+"deleteM/"+id;
       System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                
                try {
                    tasks = parseEvent(new String(req.getResponseData()));
                } catch (Exception ex) {
                
                }
                    //System.out.println("chnia mochkol "+tasks);
                    req.removeResponseListener(this);
              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
         
         
     
       
  public boolean modifierEvent(event v) {
        String url =URL+"editM/"+v.getId()+"?noun="+v.getNoun()+"&description="+v.getDescription();
        req.setUrl(url);
           req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
  
  
  
  
public ArrayList<event> getAllClub(){
        String url =URL+"affM";
       //System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                   // System.out.println(url);
                    tasks = parseEvent(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
  
     
}
