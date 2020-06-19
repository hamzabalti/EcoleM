/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Avis;
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class ServiceAvisApprenant {
      public ArrayList<Avis> note;  
    public static ServiceAvisApprenant instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    ServiceAvisApprenant() {
         req = new ConnectionRequest();
    }
    public static ServiceAvisApprenant getInstance() {
        if (instance == null) {
            instance = new ServiceAvisApprenant();
        }
        return instance;
    }
  public boolean addAvis(Avis a) {  
        String url = Statics.BASE_URL +"AjouterAvisAction/"+a.getApprenant()+"/" + a.getEnseignant() +"?avis="+a.getAvis();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
