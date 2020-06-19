/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Absence;
import com.mycompany.myapp.entities.Clase;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceRecupClasse {
  public ArrayList<Clase> classe;
    public static ServiceRecupClasse instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ServiceRecupClasse() {
         req = new ConnectionRequest();
    }
    public static ServiceRecupClasse getInstance() {
        if (instance == null) {
            instance = new ServiceRecupClasse();
        }
        return instance;
    }  
    public ArrayList<Clase> parseTasks(String jsonText){
        try {
            classe=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> classeListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));           
            List<Map<String,Object>> list = (List<Map<String,Object>>)classeListJson.get("root");
            for(Map<String,Object> obj : list){
                Clase c = new Clase();
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int)id);
                float niveau = Float.parseFloat(obj.get("niveau").toString());
                c.setNiveau((int)niveau);
                c.setType(obj.get("type").toString());               
                classe.add(c);
            } 
        } catch (IOException ex) {   
        }
        return classe;
    }
       public ArrayList<Clase> getClasses(){
        Clase c=new Clase(); 
        String url = Statics.BASE_URL+"AfficheClassAffecter";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                classe = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return classe;
    }   
    public ArrayList<Clase> getClasse(int id){
        Clase c=new Clase(); 
        String url = Statics.BASE_URL+"getClass/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                classe = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return classe;
    }  
}

