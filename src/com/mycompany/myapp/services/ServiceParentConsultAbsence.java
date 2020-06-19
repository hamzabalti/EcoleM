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
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public class ServiceParentConsultAbsence {
      public ArrayList<Absence> absence;
    
    public static ServiceParentConsultAbsence instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceParentConsultAbsence() {
         req = new ConnectionRequest();
    }

    public static ServiceParentConsultAbsence getInstance() {
        if (instance == null) {
            instance = new ServiceParentConsultAbsence();
        }
        return instance;
    }
    
    
    
    
    public ArrayList<Absence> parseTasks(String jsonText){
        try {
            absence=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> absenceListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)absenceListJson.get("root");
            for(Map<String,Object> obj : list){
                Absence a = new Absence();
                float id = Float.parseFloat(obj.get("id").toString());
                a.setId((int)id);
                float nb_absence = Float.parseFloat(obj.get("nb_absence").toString());
                a.setNb_absence((int)nb_absence);
                a.setU(((int)Float.parseFloat(obj.get("apprenant").toString())));
                a.setC(((int)Float.parseFloat(obj.get("classe").toString())));
                absence.add(a);
            }
            
            
        } catch (IOException ex) {
            
        }
        return absence;
    }
    
    public ArrayList<Absence> getAllAbsenceParentApprenant(int idp){
        Absence a=new Absence();
        
        String url = Statics.BASE_URL+"getAllAbsenceParentApprenant/"+idp;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                absence = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return absence;
    }
}
