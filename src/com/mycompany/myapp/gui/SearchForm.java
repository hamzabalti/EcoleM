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
import static com.mycompany.myapp.gui.ReservationForm.id;
import com.mycompany.myapp.services.ActiviteService;
import com.mycompany.myapp.services.EnfantService;
import com.mycompany.myapp.services.ReservationsService;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.MyApplication;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import com.mycompany.myapp.utils.WebService;

/**
 *
 * @author Jasser
 */
public class SearchForm extends BaseForm{
    public SearchForm(){
        
        setName("Recherche");
        setTitle("Recherche");
         Container tt=new Container(new FlowLayout(CENTER, CENTER));
         Label l3=new Label("Bienvenu Mr(s)"+" "+MyApplication.u.getPrenom());
         l3.getAllStyles().setFgColor(0x0000FF);
         l3.setHeight(1000);
         tt.add(l3);
         add(tt);
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
            
           
            TextField t = new TextField();
            t.setHint("Nom");
            Button b = new Button("Chercher");
            
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            
            WebService ws = new WebService();
           Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            photos.add(t);
            photos.add(b);
            add(photos);
            b.addActionListener(e->{
                ReservationsService ds = new ReservationsService();
    Map x = ws.getResponse("search_by_name/"+t.getText());
    ArrayList<Reservation> listevents = ds.getListLivres(x);
                SearchReservation.listevents = listevents ;
                SearchReservation sr = new SearchReservation();
                sr.show();
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
