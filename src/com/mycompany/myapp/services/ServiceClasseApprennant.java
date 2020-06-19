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
import com.mycompany.myapp.entities.Avis;
import com.mycompany.myapp.entities.Clase;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public class ServiceClasseApprennant {
    
    
    
     public ArrayList<Clase> classe;  
    public static ServiceClasseApprennant instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ServiceClasseApprennant() {
         req = new ConnectionRequest();
    }
    public static ServiceClasseApprennant getInstance() {
        if (instance == null) {
            instance = new ServiceClasseApprennant();
        }
        return instance;
    }
    public ArrayList<Clase> parseTasks(String jsonText){
        try {
            classe=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> noteListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));           
            List<Map<String,Object>> list = (List<Map<String,Object>>)noteListJson.get("root");
            for(Map<String,Object> obj : list){
                Clase a = new Clase();
                float id = Float.parseFloat(obj.get("id").toString());
                a.setId((int)id);   
                 float niveau = Float.parseFloat(obj.get("niveau").toString());
                a.setNiveau((int)niveau);
                a.setType(obj.get("type").toString());
                classe.add(a);
            }   
        } catch (IOException ex) {
            
        }
        return classe;
    }   
    public ArrayList<Clase> getClassee(int idAppre){
        String url ="http://127.0.0.1:8000/getClAf/"+idAppre;
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
