/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.Activite;
import com.mycompany.myapp.entities.Enfant;
import com.mycompany.myapp.entities.Garderie;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ActiviteService;
import com.mycompany.myapp.services.EnfantService;
import com.mycompany.myapp.services.ReservationsService;
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
import com.mycompany.myapp.services.ServiceSms;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.mycompany.myapp.utils.WebService;

/**
 *
 * @author Jasser
 */
public class AddEnfant extends BaseForm{
    ComboBox<String> c1;
    ComboBox<String> c;
   public AddEnfant(){ComboBox<String> c;
    
    
        c = new ComboBox();
        c1 = new ComboBox();
        
        setName("Ajouter Enfant");
        setTitle("Ajouter Enfant");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            Home rf = new Home();
            rf.show();
        });
    
            getToolbar().addCommandToRightSideMenu("Reservations", icons, (e) -> {
            ReservationListForm cf = new ReservationListForm();
               cf.show();
        });
            getToolbar().addCommandToRightSideMenu("Garderies", icons, (e) -> {
            ListGarderieForm cf = new ListGarderieForm();
               cf.show();
        });
                  getToolbar().addCommandToRightSideMenu("Note des Enfants", icons, (e) -> {
            ParentNoteForm cf = new ParentNoteForm();
               cf.show();
        });
                        getToolbar().addCommandToRightSideMenu("Absence des Enfants", icons, (e) -> {
            ParentAbsenceForm cf = new ParentAbsenceForm();
               cf.show();
        });
        
            
             FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, s);
              getToolbar().addCommandToRightSideMenu("Logout", icon1, (e) -> {
                  MyApplication.u = null ; 
            LoginForm cf = new LoginForm();
               cf.show();
        });
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Picker datePicker = new Picker();
            datePicker.setType(Display.PICKER_TYPE_DATE);
            TextField email = new TextField();
            email.setHint("Email");
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
            c1.addItem("1");
            c1.addItem("2");
            c1.addItem("3");
            c1.addItem("4");
            c1.addItem("5");
            c1.addItem("6");
            Button b = new Button("Ajouter");
            
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            
            WebService ws = new WebService();
           photos.add(username);
           photos.add(email);
           photos.add(prenom);
            photos.add(datePicker);
            photos.add(tel);
            
            photos.add(c);
            photos.add(c1);
            photos.add(password);
            photos.add(Confirm);
            
            
            photos.add(b);
            add(photos);
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
                    u.setNiveau(c1.getSelectedItem());
                    u.setSexe(c.getSelectedItem());
                    u.setUsername(username.getText());
                    u.setPrenom(prenom.getText());
                    u.setTel(tel.getText());
                    u.setPassword(password.getText());
                    u.setIdparent(MyApplication.u.getId());
                    ws.addUser(u);
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
