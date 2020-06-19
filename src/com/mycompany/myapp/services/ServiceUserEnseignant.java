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
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public class ServiceUserEnseignant {
    public ArrayList<User> user;
    
    public static ServiceUserEnseignant instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceUserEnseignant() {
         req = new ConnectionRequest();
    }

    public static ServiceUserEnseignant getInstance() {
        if (instance == null) {
            instance = new ServiceUserEnseignant();
        }
        return instance;
    }

       public ArrayList<User> parseTasks(String jsonText){
        try {
            user=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> userListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)userListJson.get("root");
            for(Map<String,Object> obj : list){
                User u = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                float cin= Float.parseFloat(obj.get("cin").toString());
                float tel = Float.parseFloat(obj.get("tel").toString());
                float matiere = Float.parseFloat(obj.get("matiere").toString());
              
                
                u.setId((int)id);
               
                u.setCin(obj.get("cin").toString()); 
                u.setSexe(obj.get("sexe").toString());                 
                u.setTel(obj.get("tel").toString());
               u.setPrenom(obj.get("prenom").toString()); 
                u.setM((int)matiere);


                


                user.add(u);
            }
            
            
        } catch (IOException ex) {
            
        }
        return user;
    }
       
       public ArrayList<User> getallUserEn(int id){
        User u=new User();
        
        String url = Statics.BASE_URL+"getallUserEn/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                user = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;
    }
 
    public ArrayList<User> getallEnseignant(){
        User u=new User();
        
        String url = Statics.BASE_URL+"getallEnseignant";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                user = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;
    }
    public ArrayList<User> getallEnseignantByName(String Name){
        User u=new User();
        
        String url = Statics.BASE_URL+"getEnseignantByName/"+Name;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                user = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;
    }
    
}
