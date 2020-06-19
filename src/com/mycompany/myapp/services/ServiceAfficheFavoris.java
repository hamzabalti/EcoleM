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
import com.mycompany.myapp.entities.Favoris;
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
//public class ServiceAfficheFavoris {
//    
//    public ArrayList<Favoris> favoris;  
//    public static ServiceAfficheFavoris instance=null;
//    public boolean resultOK;
//    private ConnectionRequest req;
//    private ServiceAfficheFavoris() {
//         req = new ConnectionRequest();
//    }
//    public static ServiceAfficheFavoris getInstance() {
//        if (instance == null) {
//            instance = new ServiceAfficheFavoris();
//        }
//        return instance;
//    }
//
//    public ArrayList<Favoris> parseTasks(String jsonText){
//        try {
//            favoris=new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String,Object> noteListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));           
//            List<Map<String,Object>> list = (List<Map<String,Object>>)noteListJson.get("root");
//            for(Map<String,Object> obj : list){
//                Favoris t = new Favoris();
//                //BigDecimal b1 = new BigDecimal(t.getMoyenne());  
//                float id = Float.parseFloat(obj.get("id").toString());
//                float matiere = Float.parseFloat(obj.get("matiere").toString());
//                float apprenant = Float.parseFloat(obj.get("apprenant").toString());
//                float note_orale =Float.parseFloat(obj.get("note_orale").toString());
//                float moyenne = Float.parseFloat(((obj.get("moyenne").toString())));
//                float note_ecrit = Float.parseFloat(obj.get("note_ecrit").toString());
//                t.setId((int)id);
//                t.setM((int)matiere);
//                t.setU((int)apprenant);
//                t.setNote_orale((double)note_orale);
//                t.setMoyenne((double)moyenne);
//                t.setNote_ecrit((double)note_ecrit);
//                favoris.add(t);
//            }   
//        } catch (IOException ex) {
//            
//        }
//        return note;
//    }   
//    public ArrayList<Note> getAllNote(int id){
//        String url = Statics.BASE_URL+"getAll/Note/"+id;
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                note = parseTasks(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return note;
//    } 
//
//}
