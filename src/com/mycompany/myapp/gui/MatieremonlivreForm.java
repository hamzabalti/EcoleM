/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Matieremonlivre;
import com.mycompany.myapp.services.CategorieService;
import com.mycompany.myapp.services.MatiereMonLivreService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
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
public class MatieremonlivreForm extends BaseForm{
    public static Categorie c ;
    public MatieremonlivreForm() {
         Container t=new Container(new FlowLayout(CENTER, CENTER));
         Label l3=new Label("Bienvenu: "+" "+MyApplication.u.getPrenom());
         l3.getAllStyles().setFgColor(0x0000FF);
         l3.setHeight(1000);
         t.add(l3);
         add(t);
        
        setName("Liste Des matieres");
        setTitle("Liste des matières");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            CategorieForm h = new CategorieForm();
            h.show();
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
             WebService ws = new WebService();
    MatiereMonLivreService ds = new MatiereMonLivreService();
    Map x = ws.getResponse("matieremonlivre/"+c.getId());
    ArrayList<Matieremonlivre> listevents = ds.getListmatiere(x,c);
             for (Matieremonlivre e : listevents) {
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            Label a = new Label(e.getMatiere());
            

            try {
                encoded = EncodedImage.create("/like.png");
            } catch (IOException ex) {
            }
            img = URLImage.createToStorage(encoded, e.getNomfile(), "http://127.0.0.1:8000/Upload" + e.getNomfile());
            imv = new ImageViewer(img);
            photos.add(imv);
            photos.add(a);
            try {
                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                photos.add(sep);
            } catch (IOException ex) {
            }
            add(photos);
            
            a.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    WebService ws = new WebService();
                    String status = ws.getStatus("check/"+MyApplication.u.getId()+"/"+e.getId());
                    if(status.equals("subscribed")){
                        MatiereVideos.ml = e ;
                        System.out.println(e.getId());
                        MatiereVideos m = new MatiereVideos();
                        m.f.show();
                    }else{
                       MatiereMonlivredetails.e = e ;
                       MatiereMonlivredetails me = new MatiereMonlivredetails();
                       me.show();
                    }

                }
            });
            a.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    

                }
            });
        }
        show();
    }
    
}
