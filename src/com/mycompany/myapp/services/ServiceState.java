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
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.entities.StateData;
import static com.mycompany.myapp.services.ServiceRecupNote.instance;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public class ServiceState {
  public ArrayList<StateData> mydatas;  
    public static ServiceState instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceState() {
            req = new ConnectionRequest();

    }
    
        public static ServiceState getInstance() {
        if (instance == null) {

            instance = new ServiceState();
        }
        return instance;
    }
 
    public ArrayList<StateData> parseTasks(String jsonText){
        try {
            mydatas=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> noteListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));           
            List<Map<String,Object>> list = (List<Map<String,Object>>)noteListJson.get("root");
            System.out.println("Liste"+list);
            for(Map<String,Object> obj : list){
                StateData t = new StateData();
                t.setNom_matiere(obj.get("nom_matiere").toString());
                float moy=Float.parseFloat(obj.get("moyenne").toString());
                t.setMoyenne((int)moy);
                
                
                
                mydatas.add(t);
                
            }   
        } catch (IOException ex) {
            
        }
        return mydatas;
    }   
    
    public ArrayList<StateData> getState(){
         String url = Statics.BASE_URL+"getStat";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                mydatas = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return mydatas;
    }
    
}
