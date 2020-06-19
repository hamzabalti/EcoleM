/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.Activite;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Enfant;
import com.mycompany.myapp.entities.Garderie;
import com.mycompany.myapp.entities.Reservation;
import static com.mycompany.myapp.gui.MatiereMonlivredetails.e;
import com.mycompany.myapp.services.ActiviteService;
import com.mycompany.myapp.services.EnfantService;
import com.mycompany.myapp.services.ReservationsService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
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
public class ReservationForm extends BaseForm{
    ComboBox<String> c;
    ComboBox<String> c1;
    ComboBox<String> c2;
    public static int id ;
    public ReservationForm(){
         Container t=new Container(new FlowLayout(CENTER, CENTER));
         Label l3=new Label("Bienvenu: "+" "+MyApplication.u.getPrenom());
         l3.getAllStyles().setFgColor(0x0000FF);
         l3.setHeight(1000);
         t.add(l3);
         add(t);
        id = 4 ;
        c = new ComboBox();
        c1 = new ComboBox();
        c2 = new ComboBox();
        setName("Ajouter réservation");
        setTitle("Ajouter réservation");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            ReservationListForm rf = new ReservationListForm();
            rf.show();
        });
         getToolbar().addCommandToRightSideMenu("Home", icons, (e) -> {
            Home cf = new Home();
               cf.show();
        });
            getToolbar().addCommandToRightSideMenu("Reservations", icons, (e) -> {
            ReservationListForm cf = new ReservationListForm();
               cf.show();
        });
            getToolbar().addCommandToRightSideMenu("Garderies", icons, (e) -> {
            ListGarderieForm cf = new ListGarderieForm();
               cf.show();
        });
             if(MyApplication.u.getRoles().equals("ROLE_PARENT")){
              getToolbar().addCommandToRightSideMenu("Ajouter Enfants", icons, (e) -> {
            AddEnfant cf = new AddEnfant();
               cf.show();
        }); 
            }
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
            
            Button b = new Button("Réserver");
            
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            ActiviteService as = new ActiviteService();
            WebService ws = new WebService();
            Map x = ws.getResponse("ListActivite");
           ArrayList<Activite> listevents = as.getListActivite(x);
           for (Activite e : listevents) {
               c.addItem(e.getType());
           }
            Map x2 = ws.getResponse("ListEnfant/"+MyApplication.u.getId());
            
            EnfantService es = new EnfantService();
           ArrayList<Enfant> listevent = es.getListEnfant(x2);
           for (Enfant e : listevent) {
               c1.addItem(e.getUsername());
           }
           Map x3 = ws.getResponse("li");
            
            ReservationsService ess = new ReservationsService();
           ArrayList<Garderie> listevente = ess.getListGarderie(x3);
           for (Garderie e : listevente) {
               c2.addItem(e.getNom());
           }
            
            
            photos.add(c);
            photos.add(c1);
            photos.add(c2);
            photos.add(datePicker);
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
       if(((int)(dnow.getTime()- dc.getTime()) < 0)){
                Date d = datePicker.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Reservation r = new Reservation();
                ws.addService(c1.getSelectedItem(), c.getSelectedItem(), sdf.format(d), MyApplication.u.getId(),c2.getSelectedItem());
                System.out.println(c.getSelectedItem() +"Enfant : " + c1.getSelectedItem() + "Date :" +sdf.format(d) );
       }
       else{
           Dialog.show("Erreur", "Vérifiez vos informations", "Ok", null);
       }});
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
