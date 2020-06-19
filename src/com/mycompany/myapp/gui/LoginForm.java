/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.Comment;
import com.mycompany.myapp.entities.User;
import static com.mycompany.myapp.gui.AddComment.c;
import static com.mycompany.myapp.gui.CommentsForm.id;
import com.mycompany.myapp.services.ActiviteService;
import com.mycompany.myapp.services.CommentService;
import com.mycompany.myapp.services.UserService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
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
public class LoginForm extends BaseForm{
    
    public LoginForm(){
    super("Login", BoxLayout.y());
                                      Style ss = UIManager.getInstance().getComponentStyle("TitleCommand");     

    FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_HELP, ss);
              getToolbar().addCommandToRightBar("Plus", icon1, (e) -> {
                  MyApplication.u = null ; 
            FormHelp cf = new FormHelp();
               cf.show();
        });   
        
     ImageViewer ens = null;
        try {
            ens = new ImageViewer(Image.createImage("/loginphoto.jpg").scaled(1200,900));
        } catch (IOException ex) {  
            System.out.println(ex.toString());
        }         
        Container c2=new Container(new FlowLayout(CENTER, CENTER));
             c2.add(ens);
add(c2);
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            TextField login = new TextField();
            
            login.setHint("Email");
            TextField password = new TextField(TextField.PASSWORD);
            password.setHint("Password");
            
            Button b = new Button("Se connecter");
            Button f = new Button("Inscription");
            
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            UserService as = new UserService();
            WebService ws = new WebService();
            
           
           
            
            
            photos.add(login);
            photos.add(password);
            photos.add(b);
            photos.add(f);
            add(photos);
            f.addActionListener(e->{
                AddParent p = new AddParent();
                p.show();
            });
            b.addActionListener(e->{
               String st = ws.Login(login.getText(), password.getText());
               if (st.equals("success")) {
                           
                           ArrayList<User> listevents = as.getListCategorie(ws.getUser("http://localhost/Service/GetUser.php?email="+login.getText()));
                           for (User es : listevents) {
                               MyApplication.u = es ;
                               Home h = new Home();
                               h.show();
                               System.out.println(es.getRoles());
                               
                           }
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
            });
            
           
      
        show();
        
       
        
        
    }
    
}
