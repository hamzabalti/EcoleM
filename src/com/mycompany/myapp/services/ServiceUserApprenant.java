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

public class ServiceUserApprenant {
        public ArrayList<User> user;
    
    public static ServiceUserApprenant instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceUserApprenant() {
         req = new ConnectionRequest();
    }

    public static ServiceUserApprenant getInstance() {
        if (instance == null) {
            instance = new ServiceUserApprenant();
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
                float affecter = Float.parseFloat(obj.get("affecter").toString());
                float niveau = Float.parseFloat(obj.get("niveau").toString());
                float parent = Float.parseFloat(obj.get("parent").toString());
                u.setId((int)id);
                u.setCin(obj.get("cin").toString()); 
                u.setSexe(obj.get("sexe").toString());                 
                u.setTel(obj.get("tel").toString());
                u.setAffecter((int)affecter);
                u.setNiveau(obj.get("niveau").toString());
                u.setLoisir(obj.get("loisir").toString()); 
                u.setIdparent((int)parent);
                u.setPrenom(obj.get("prenom").toString()); 
                user.add(u);
            }
        } catch (IOException ex) {
            
        }
        return user;
    }
       public ArrayList<User> parsePrenoms(String jsonText){
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
                user.add(u);
            }
        } catch (IOException ex) {    
        }
        return user;
    }
       
       public ArrayList<User> getUser(int id){
        User u=new User();
        
        String url = Statics.BASE_URL+"getallUser/"+id;
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
       
        public ArrayList<User> getUserAffecter(int niveau,char type){
        User u=new User();
        
        String url = Statics.BASE_URL+"AfficheClassAffecterUser/"+niveau+"/"+type;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                user = parsePrenoms(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;
    }
 
      public ArrayList<User> getApprenantByName(String Name){
        User u=new User();
        
        String url = Statics.BASE_URL+"getApprenantByName/"+Name;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                user = parsePrenoms(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;
    }
    
}
