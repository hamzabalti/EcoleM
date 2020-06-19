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
import com.mycompany.myapp.entities.Matiere;
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hamza
 */
public class ServiceRecupMatiere {
   
    
    
        public ArrayList<Matiere> matiere;
    
    public static ServiceRecupMatiere instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceRecupMatiere() {
         req = new ConnectionRequest();
    }

    public static ServiceRecupMatiere getInstance() {
        if (instance == null) {
            instance = new ServiceRecupMatiere();
        }
        return instance;
    }



    public ArrayList<Matiere> parseTasks(String jsonText){
        try {
            matiere=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> matiereListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)matiereListJson.get("root");
            for(Map<String,Object> obj : list){
                Matiere m = new Matiere();
                float id = Float.parseFloat(obj.get("id").toString());
                float coefficient = Float.parseFloat(obj.get("coefficient").toString());
      
                
                
                m.setId((int)id);
                m.setNom_matiere(obj.get("nomMatiere").toString());               
                m.setCoefficient((int)coefficient);
            


                


                matiere.add(m);
            }
            
            
        } catch (IOException ex) {
            
        }
        return matiere;
    }
    
    
    public ArrayList<Matiere> getAllMatiere(int id){
        String url = Statics.BASE_URL+"getallMatiere/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                matiere = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return matiere;
    }

}
