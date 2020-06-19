/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.User;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.MyApplication;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.mycompany.myapp.utils.WebService;

/**
 *
 * @author Jasser
 */
public class AddParent extends BaseForm{
    
    ComboBox<String> c;
    public AddParent(){
        
         c = new ComboBox();
       
        
        setName("Inscription");
        setTitle("Inscription");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            LoginForm rf = new LoginForm();
            rf.show();
        });
       
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Picker datePicker = new Picker();
            datePicker.setType(Display.PICKER_TYPE_DATE);
            TextField email = new TextField();
            email.setHint("Email");
            TextField cin = new TextField();
            cin.setHint("CIN");
            TextField username = new TextField();
            username.setHint("username");
            TextField prenom = new TextField();
            prenom.setHint("Prénom");
            TextField tel = new TextField();
            tel.setHint("Tel");
            TextField password = new TextField();
            password.setHint("Password");
            TextField Confirm = new TextField();
            Label sexe = new Label("Sexe :");
            Label Niveau = new Label("Niveau : ");
            Label Date = new Label("Date de naissance");
            Confirm.setHint("confirm passowod");
            c.addItem("Homme");
            c.addItem("Femme");
            
           
            Button b = new Button("Ajouter");
            Button f = new Button("Login");
            
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            
            WebService ws = new WebService();
           photos.add(username);
           photos.add(email);
           photos.add(prenom);
           photos.add(cin);
            photos.add(datePicker);
            photos.add(tel);
            photos.add(c);
            photos.add(password);
            photos.add(Confirm);
            photos.add(b);
            photos.add(f);
            add(photos);
            f.addActionListener(e->{
                LoginForm lf = new LoginForm();
                lf.show();
            });
            b.addActionListener(e->{
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date dateobj = new Date();
                Date da = datePicker.getDate();
                String st = df.format(dateobj);
                String st1 = df.format(da);
               Date dnow = new Date(); 
               Date dc = new Date();              
       try {
           dnow = df.parse(st);
           dc = df.parse(st1);          
       } catch (ParseException ex) {
           
       }                          
                if(password.getText().equals("") || Confirm.getText().equals("") || email.getText().equals("") || username.getText().equals("")|| prenom.getText().equals("")|| tel.getText().equals("") || ((int)(dnow.getTime()- dc.getTime()) < 0)){
                    Dialog.show("Erreur", "Vérifiez vos informations", "Ok", null);
                    
                }else{
                if(password.getText().equals(Confirm.getText())){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    User u = new User();
                    Date d = datePicker.getDate();
                    u.setDate(sdf.format(d));
                    u.setEmail(email.getText());
                    
                    u.setSexe(c.getSelectedItem());
                    u.setUsername(username.getText());
                    u.setPrenom(prenom.getText());
                    u.setTel(tel.getText());
                    u.setPassword(password.getText());
                    u.setCin(cin.getText());
                    
                    ws.addparent(u);
                    System.out.println("hellooooooo");
                }}
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
