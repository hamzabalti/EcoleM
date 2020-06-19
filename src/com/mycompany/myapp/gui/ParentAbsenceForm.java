/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Absence;
import com.mycompany.myapp.entities.Clase;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceParentConsultAbsence;
import com.mycompany.myapp.services.ServiceRecupAbsence;
import com.mycompany.myapp.services.ServiceRecupClasse;
import com.mycompany.myapp.services.ServiceUserApprenant;
import java.io.IOException;

/**
 *
 * @author acer
 */
public class ParentAbsenceForm extends Form{
    
 public ParentAbsenceForm(){
        
             super("List Absneces", BoxLayout.y());

         
           Container t=new Container(new FlowLayout(CENTER, CENTER));
         Label l3=new Label("Bienvenu Mr(s) "+" "+MyApplication.u.getPrenom());
         l3.getAllStyles().setFgColor(0x0000FF);
         l3.setHeight(1000);
         t.add(l3);
         add(t);
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
       
        Toolbar tb = getToolbar();
        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            Home h = new Home();
            h.show();
        });
       
               getToolbar().addCommandToRightSideMenu("Garderies", icons, (e) -> {
            ListGarderieForm cf = new ListGarderieForm();
               cf.show();
        });
           
            getToolbar().addCommandToRightSideMenu("Reservations", icons, (e) -> {
            ReservationListForm cf = new ReservationListForm();
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
       
             
             FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, s);
              getToolbar().addCommandToRightSideMenu("Logout", icon1, (e) -> {
                  MyApplication.u = null ; 
            LoginForm cf = new LoginForm();
               cf.show();
        });
        
          ImageViewer ens = null;
        try {
            ens = new ImageViewer(Image.createImage("/AbsenApp.png").scaled(1200,900));
        } catch (IOException ex) {  
            System.out.println(ex.toString());
        }         
       

  
      
        
         

 User u=new User();
             Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    
 for(Absence a1:ServiceParentConsultAbsence.getInstance().getAllAbsenceParentApprenant(MyApplication.u.getId())){
          //  Container c2 = new Container(new GridLayout(1,5));
            

            
        
           Label spanLabel2=new Label();
                                                                        spanLabel2.getAllStyles().setFgColor(0x000000);

           Label nbabsence=new Label(Integer.toString(a1.getNb_absence()));
                                                                        nbabsence.getAllStyles().setFgColor(0x000000);


                        Label spanLabel1=new Label();
                                                                          spanLabel1.getAllStyles().setFgColor(0x000000);

                        



           String ch="";
            for(Clase c:ServiceRecupClasse.getInstance().getClasse(a1.getC())){
               ch+=c.getNiveau()+c.getType();           
           }
           spanLabel2.setText(ch);
           
             
           String ch1="";
         for(User u1:ServiceUserApprenant.getInstance().getUser(a1.getU())){
               ch1+=u1.getPrenom();           
           }
             
            spanLabel1.setText(ch1);

       Label pre=new Label("Le Prenom:");
       pre.getAllStyles().setFgColor(0xFF0000);
       Label classe=new Label("La Classe:");
       classe.getAllStyles().setFgColor(0xFF0000);
       Label absence=new Label("Nombre d'Absence:");
       absence.getAllStyles().setFgColor(0xFF0000);
        Label a=new Label("                       ");
            Label b=new Label("                       ");
//       add(l);
//           c1.add(ens);
            
           c1.add(pre);
           c1.add(spanLabel1);
           c1.add(classe);
          // c2.add(spanLabel);
           c1.add(spanLabel2);
           c1.add(absence);
           c1.add(nbabsence);
           c1.add(a);
           c1.add(b);

          // c1.add(c2);
       }
 
  add(ens);
  add(c1);

    
}}

