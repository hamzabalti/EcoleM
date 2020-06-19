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
import com.mycompany.myapp.entities.Clase;
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
public class ServiceRecupCour {
   public ArrayList<cour> cours;
    
    public static ServiceRecupCour instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceRecupCour() {
         req = new ConnectionRequest();
    }

    public static ServiceRecupCour getInstance() {
        if (instance == null) {
            instance = new  ServiceRecupCour();
        }
        return instance;
    }
    
    
    
    
    public ArrayList<cour> parseTasks(String jsonText){
        try {
            cours=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> emploiListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)emploiListJson.get("root");
            for(Map<String,Object> obj : list){
                cour e = new cour();
                float id = Float.parseFloat(obj.get("id").toString());
                float cl = Float.parseFloat(obj.get("classe").toString());
                float matiere = Float.parseFloat(obj.get("matiere").toString());

                e.setId((int)id);
                e.setClasse((int)cl);
                e.setMatiere((int)matiere);
                e.setNom(obj.get("nom").toString());
                e.setCour_pdf(obj.get("courpdf").toString());

                
                
                cours.add(e);
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return cours;
    }
    
    public ArrayList<cour> getCour(int id){
        cour c=new cour();
        String url = Statics.BASE_URL+"AfficheCourApprenant/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cours = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cours;
    } 
       public ArrayList<cour> AfficheCourApprenantTrier(int idAp,int idM){
        cour c=new cour();
        String url = Statics.BASE_URL+"AfficheCourApprenantTrier/"+idAp+"/"+idM;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cours = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cours;
    } 
      
}
