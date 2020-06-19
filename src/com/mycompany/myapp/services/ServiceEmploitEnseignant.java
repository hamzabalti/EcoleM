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
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public class ServiceEmploitEnseignant {
    public ArrayList<Emploi> emploi;
    
    public static ServiceEmploitEnseignant instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEmploitEnseignant() {
         req = new ConnectionRequest();
    }

    public static ServiceEmploitEnseignant getInstance() {
        if (instance == null) {
            instance = new  ServiceEmploitEnseignant();
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
                
                e.setId((int)id);
                e.setN_emploit(obj.get("nEmploi").toString());  
                e.setUser(((int)Float.parseFloat(obj.get("user").toString())));
                e.setEmploit(obj.get("emploit").toString());
                
                
                emploi.add(e);
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return emploi;
    }
    
    public ArrayList<Emploi> getEmploitEnseignant(int id){
        Emploi e=new Emploi();
        
        String url = Statics.BASE_URL+"AfficheEmploiDeEnseignant/"+id;
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
