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
import com.mycompany.myapp.entities.Favoris;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class ApprenantFavoris {
 
    public static ApprenantFavoris instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    ApprenantFavoris() {
         req = new ConnectionRequest();
    }
    public static ApprenantFavoris getInstance() {
        if (instance == null) {
            instance = new ApprenantFavoris();
        }
        return instance;
    }
  public boolean addFavoris(Favoris f){  
        String url = Statics.BASE_URL +"AjouterFavoris/"+f.getApprenant()+"/"+ f.getEmploit();
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
