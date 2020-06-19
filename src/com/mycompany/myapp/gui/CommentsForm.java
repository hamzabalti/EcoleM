/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Comment;
import com.mycompany.myapp.services.CategorieService;
import com.mycompany.myapp.services.CommentService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
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
public class CommentsForm extends BaseForm{
    public static int id;
    
    public CommentsForm(){
         super("Liste des commentaires", BoxLayout.y());
          Container t=new Container(new FlowLayout(CENTER, CENTER));
         Label l3=new Label("Bienvenu: "+" "+MyApplication.u.getPrenom());
         l3.getAllStyles().setFgColor(0x0000FF);
         l3.setHeight(1000);
         t.add(l3);
         add(t);
        
        Toolbar tb = getToolbar();
        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> {
            AddComment.c = id;
            AddComment ac = new AddComment();
            ac.show();
        });
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_HOME, e -> {
            Home h = new Home() ;
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
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       
    WebService ws = new WebService();
    CommentService ds = new CommentService();
    Map x = ws.getResponse("Listcomment/"+id);
    ArrayList<Comment> listevents = ds.getListComments(x);
    for (Comment e : listevents) {
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            Label a = new Label(e.getCommentaire());
           

            
            photos.add(a);
            try {
                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                photos.add(sep);
            } catch (IOException ex) {
            }
            add(photos);
            a.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                   

                }
            });
        }
        show();
    }
    
}
