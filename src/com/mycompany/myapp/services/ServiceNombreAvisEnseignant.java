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
import com.mycompany.myapp.entities.Avis;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public class ServiceNombreAvisEnseignant {
      public ArrayList<Avis> avis;
    
    public static ServiceNombreAvisEnseignant instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceNombreAvisEnseignant() {
         req = new ConnectionRequest();
    }

    public static ServiceNombreAvisEnseignant getInstance() {
        if (instance == null) {
            instance = new ServiceNombreAvisEnseignant();
        }
        return instance;
    }
    
    
    
    
    public ArrayList<Avis> parseTasks(String jsonText){
        try {
            avis=new ArrayList<>();
         JSONParser j = new JSONParser();
            Map<String,Object> noteListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));           
            List<Map<String,Object>> list = (List<Map<String,Object>>)noteListJson.get("root");
            for(Map<String,Object> obj : list){
                Avis a = new Avis();
                float id = Float.parseFloat(obj.get("id").toString());
                float apprenant= Float.parseFloat(obj.get("apprenant").toString());
                float enseignant = Float.parseFloat(obj.get("enseignant").toString());
                a.setId((int)id);             
                a.setApprenant((int)apprenant);
                a.setEnseignant((int)enseignant);
                a.setAvis(obj.get("avis").toString());
            }
            
            
        } catch (IOException ex) {
            
        }
        return avis;
    }
    
    public ArrayList<Avis> CountAvis(int idE){
        
        String url = Statics.BASE_URL+"CountAvis/"+idE;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                avis = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return avis;
    }
    
    
    
}
