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
import com.mycompany.myapp.entities.Emploi;
import com.mycompany.myapp.entities.cour;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hamza
 */
public class ServiceEmploitApprenant {
   public ArrayList<Emploi> emploi;
    
    public static ServiceEmploitApprenant instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceEmploitApprenant() {
         req = new ConnectionRequest();
    }

    public static ServiceEmploitApprenant getInstance() {
        if (instance == null) {
            instance = new ServiceEmploitApprenant();
        }
        return instance;
    }
    
    
    
    
    public ArrayList<Emploi> parseTasks(String jsonText){
        try {
            emploi=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> emploiListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)emploiListJson.get("root");
            for(Map<String,Object> obj : list){
                Emploi e = new Emploi();
                float id = Float.parseFloat(obj.get("id").toString());
                float Classe = Float.parseFloat(obj.get("classe").toString());
  
                e.setId((int)id);
                e.setN_emploit(obj.get("nEmploi").toString()); 
                e.setClasse((int)Classe);
                e.setEmploit(obj.get("emploit").toString());
                
                
                
                emploi.add(e);
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return emploi;
    }
    
    public ArrayList<Emploi> getEmploitApprenant(int id){
        Emploi e=new Emploi();
        
        String url = Statics.BASE_URL+"AfficheEmploiDeClass/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                emploi = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return emploi;
    } 
      
    public ArrayList<Emploi> RechercheEmploiByName(String Name){
        Emploi e=new Emploi();
        
        String url = Statics.BASE_URL+"RechercheEmploiByName/"+Name;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                emploi = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return emploi;
    }
    
        public ArrayList<Emploi> AfficherFavoris(int id){
        Emploi e=new Emploi();
        
        String url = Statics.BASE_URL+"AfficherFavoris/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                emploi = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return emploi;
    } 
    
}
