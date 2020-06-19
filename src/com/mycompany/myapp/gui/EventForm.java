/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.Event;
import static com.mycompany.myapp.gui.CommentsForm.id;
import com.mycompany.myapp.services.EventService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import com.mycompany.myapp.utils.WebService;


/**
 *
 * @author Jasser
 */
public class EventForm extends BaseForm{
    public static Event e ; 
    
    public EventForm() {
         Container t=new Container(new FlowLayout(CENTER, CENTER));
         Label l3=new Label("Bienvenu: "+" "+MyApplication.u.getPrenom());
         l3.getAllStyles().setFgColor(0x0000FF);
         l3.setHeight(1000);
         t.add(l3);
         add(t);
        WebService ws = new WebService();
        String status = ws.getStatus("checkifpart/"+MyApplication.u.getId()+"/"+e.getId()).toString();
        setName("Détails");
        setTitle("Détails");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
         Toolbar tb = getToolbar();
         System.out.println(status);
         
         if(status.equals("subscribed") ){
        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_DELETE, ef -> {
            ws.addunParticiper(MyApplication.u.getId(), e.getId());
            
        }); 
         }else{
            tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_SUBSCRIPTIONS, ef -> {
            ws.addParticiper(MyApplication.u.getId(), e.getId());
            
        }); 
         }
        
         
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_HOME, e -> {
            Home h = new Home() ;
            h.show();
        });
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_PLUS_ONE, et -> {
            ws.addLike(e.getId());
            
        });
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_LIST, es -> {
            CommentsForm.id = e.getId();
                    CommentsForm cf = new CommentsForm();
                    cf.show();
        });
            getToolbar().addCommandToRightSideMenu("Evenements", icon, (e) -> {
            ListeEvents cf = new ListeEvents();
               cf.show();
        });
            getToolbar().addCommandToRightSideMenu("Activités", icons, (e) -> {
             ListActiviteForm cf = new ListActiviteForm();
               cf.show();
        });
            getToolbar().addCommandToRightSideMenu("Catégorie", icons, (e) -> {
            CategorieForm cf = new CategorieForm();
               cf.show();
        });
                 getToolbar().addCommandToRightSideMenu("Matiere", icons, (e) -> {
            MatieremonlivreForm cf = new MatieremonlivreForm();
               cf.show();
        });
                          getToolbar().addCommandToRightSideMenu("Info", icons, (e) -> {
            InfoForm cf = new InfoForm();
               cf.show();
        });
                 
             FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, s);
              getToolbar().addCommandToRightSideMenu("Logout", icon1, (e) -> {
                  MyApplication.u = null ; 
            LoginForm cf = new LoginForm();
               cf.show();
        });
             
    EventService ds = new EventService();
    Map x = ws.getResponse("listEvent");
    //ArrayList<Event> listevents = ds.getListsEvents(x);
             //for (Event e : listevents) {
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            int i = ws.getCountComment("countcomment/"+e.getId());
            Label a = new Label("Titre : "+e.getTitre());
            Label b = new Label("Du "+e.getDated()+ " Jusqu'a le : " +e.getDateF());
            Label c = new Label("Adresse : "+e.getLieu());
            Label E = new Label("Description : "+e.getDiscription());
            Label F = new Label(e.getNbrplace() + " Places disponibles");
            Label G = new Label(e.getNbrparticipant() +" Paticipants");
            Label d = new Label(i+" Commentaires");
            Label  l= new Label(e.getVote()+" J'aimes");

            try {
                encoded = EncodedImage.create("/like.png");
            } catch (IOException ex) {
            }
            img = URLImage.createToStorage(encoded, e.getImage(), "http://127.0.0.1:8000/Upload/" + e.getImage());
            imv = new ImageViewer(img);
            
            photos.add(imv);
             try {
                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                photos.add(sep);
            } catch (IOException ex) {
            }
            photos.add(a);
            photos.add(b);
            photos.add(c);
            photos.add(E);
            photos.add(F);
            photos.add(G);
            photos.add(d);
             photos.add(l);
           
           
            add(photos);
            
            a.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    WebService ws = new WebService();
                    String status = ws.getStatus("check/"+6+"/"+e.getId());
                    if(status.equals("subscribed")){
                        
                        System.out.println(e.getId());
                        MatiereVideos m = new MatiereVideos();
                        m.f.show();
                    }else{
                       
                       MatiereMonlivredetails me = new MatiereMonlivredetails();
                       me.show();
                    }

                }
            });
            d.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    CommentsForm.id = e.getId();
                    CommentsForm cf = new CommentsForm();
                    cf.show();

                }
            });
        
        show();
    }
    
}
