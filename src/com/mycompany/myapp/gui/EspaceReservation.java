/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.mycompany.myapp.services.UserService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.utils.WebService;
import java.io.IOException;

/**
 *
 * @author Jasser
 */
public class EspaceReservation extends BaseForm{
    
    public EspaceReservation(){
        super("Espace Réservations", BoxLayout.y());
         Container t=new Container(new FlowLayout(CENTER, CENTER));
         Label l3=new Label("Bienvenu Mr(s) "+" "+MyApplication.u.getPrenom());
         l3.getAllStyles().setFgColor(0x0000FF);
         l3.setHeight(1000);
         t.add(l3);
         add(t);
        
        Container pro = new Container(new FlowLayout(RIGHT));
           ImageViewer pr = null;
        try {
            pr = new ImageViewer(Image.createImage("/gard.jpg").scaled(1200,900));
        } catch (IOException ex) {  
            System.out.println(ex.toString());
        }    
        Label l9=new Label("                                            ");
        Label l10=new Label("                                            ");
        
        SpanLabel S1 =new SpanLabel("Les élèves seront gardés dans leur salle de cours ou bien ils(elles) iront rejoindre leur club d'activité prévu.\n" +
"L’école propose des clubs très variés.\n" +
"L’objectif étant d’offrir à vos enfants des activités de qualité");
        
       
             pro.add(pr);
             add(pro);
             add(l9);
             add(S1);
             add(l10);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            Home rf = new Home();
            rf.show();
        });
    
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            
            Button b = new Button("Réservations");
            Button c = new Button("Liste Garderies");
            
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            UserService as = new UserService();
            WebService ws = new WebService();
            
           
           
          
            photos.add(b);
            photos.add(c);
           
            add(photos);
            
            
            c.addActionListener(e->{
              ListGarderieForm em = new ListGarderieForm();
               em.show();
               
            });
            b.addActionListener(e->{
                ReservationListForm ee = new ReservationListForm();
                ee.show();
               
            });
            
           
            /**c.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    WebService ws = new WebService();
                    String status = ws.getStatus("check/"+6+"/"+e.getId());
                    if(status.equals("subscribed")){
                        MatiereVideos.ml = e ;
                        System.out.println(e.getId());
                        MatiereVideos m = new MatiereVideos();
                        m.f.show();
                    }else{
                        
                    }

                }
            });**/
           
      
        show();
        
       
        
        
    }
    
}
