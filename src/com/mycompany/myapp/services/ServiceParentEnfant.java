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
public class ServiceParentEnfant {
    
    
        public ArrayList<User> user;
    
    public static ServiceParentEnfant instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceParentEnfant() {
         req = new ConnectionRequest();
    }

    public static ServiceParentEnfant getInstance() {
        if (instance == null) {
            instance = new ServiceParentEnfant();
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
                u.setId((int)id);
                u.setPrenom(obj.get("prenom").toString());
                u.setUsername(obj.get("username").toString()); 
                user.add(u);
            }
        } catch (IOException ex) {
            
        }
        return user;
    }
       
       public ArrayList<User> getUser(int id){        
        String url = Statics.BASE_URL+"getAppParent/"+id;
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

    

