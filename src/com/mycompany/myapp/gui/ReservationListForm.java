/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.Matieremonlivre;
import com.mycompany.myapp.entities.Reservation;
import static com.mycompany.myapp.gui.CommentsForm.id;
import static com.mycompany.myapp.gui.MatieremonlivreForm.c;
import com.mycompany.myapp.services.MatiereMonLivreService;
import com.mycompany.myapp.services.ReservationsService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
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
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import com.mycompany.myapp.utils.WebService;

/**
 *
 * @author Jasser
 */
public class ReservationListForm extends BaseForm{
    
    public ReservationListForm(){
        setName("Liste des réservations");
        setTitle("Liste des réservations");
              Container t=new Container(new FlowLayout(CENTER, CENTER));
         Label l3=new Label("Bienvenu Mr(s)"+" "+MyApplication.u.getPrenom());
         l3.getAllStyles().setFgColor(0x0000FF);
         l3.setHeight(1000);
         t.add(l3);
        add(t);
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
                  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            EspaceReservation rf = new EspaceReservation();
            rf.show();
        });
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_SEARCH, e -> {
            SearchForm h = new SearchForm();
            h.show();
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
            getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> {
            
            ReservationForm ac = new ReservationForm();
            ac.show();
        });
             WebService ws = new WebService();
    ReservationsService ds = new ReservationsService();
    Map x = ws.getResponse("Listreservations/"+MyApplication.u.getId());
    ArrayList<Reservation> listevents = ds.getListLivres(x);
             for (Reservation e : listevents) {
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String st = formatter.format(e.getDateGar());
            Label a = new Label("Reservation le : " + st);
            Label b = new Label("état : "+e.getEtat());
            Label c = new Label("Prix : " +e.getPrix());
           
           Button voir = new Button("Détails");
            
            photos.add(a);
            photos.add(b);
            photos.add(c);
            photos.add(voir);
            try {
                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                photos.add(sep);
            } catch (IOException ex) {
            }
        
            add(photos);
            
            a.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                }
            });
            
            voir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ReservationDetails.e = e ;
                    ReservationDetails r = new ReservationDetails();
                    r.show();

                }
            });
        }
        
        show();
    }
    
    }
    

