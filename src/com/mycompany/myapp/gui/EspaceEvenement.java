/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.mycompany.myapp.services.UserService;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.utils.WebService;
import java.io.IOException;

/**
 *
 * @author Jasser
 */
public class EspaceEvenement extends BaseForm{
    
    public EspaceEvenement(){
        super("Espace Evenements", BoxLayout.y());
               Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
                       Container t=new Container(new FlowLayout(CENTER, CENTER));

         Label l3=new Label("Bienvenu: "+" "+MyApplication.u.getPrenom());
          l3.getAllStyles().setFgColor(0x0000FF);
         l3.setHeight(1000);
         t.add(l3);
         add(t);
        ImageViewer ens = null;
        try {
            ens = new ImageViewer(Image.createImage("/even.jpg").scaled(1200,900));
        } catch (IOException ex) {  
            System.out.println(ex.toString());
        }         
        Container c2=new Container(new FlowLayout(CENTER, CENTER));
             c2.add(ens);
             add(c2);
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
        Toolbar tb = getToolbar();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            Home rf = new Home();
            rf.show();
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
        
    
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            
            Button b = new Button("Activités");
            Button c = new Button("Evenements");
            
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            UserService as = new UserService();
            WebService ws = new WebService();
            
           
           
          
            photos.add(b);
            photos.add(c);
           
            add(photos);
            
            
            c.addActionListener(e->{
              ListeEvents em = new ListeEvents();
               em.show();
               
            });
            b.addActionListener(e->{
                ListActiviteForm ee = new ListActiviteForm();
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
