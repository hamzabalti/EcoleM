/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Avis;
import com.mycompany.myapp.entities.Clase;
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceAvisApprenant;
import com.mycompany.myapp.services.ServiceRecupClasse;
import com.mycompany.myapp.services.ServiceRecupNote;
import com.mycompany.myapp.services.ServiceSms;
import com.mycompany.myapp.services.ServiceUserApprenant;
import com.mycompany.myapp.services.ServiceUserEnseignant;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class AvisApprenantForm extends Form {

    public AvisApprenantForm() {
        setTitle("Votre Avis");
        setLayout(BoxLayout.y());
         
          Container inf = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Button empl = new Button("Consulter Emploi");
            Button cour = new Button("Consulter Cour");
            Button noteapp = new Button("Consulter Note");
            Button absenceapp = new Button("Consulter Les Absence");
            Button favoris = new Button("Consulter Favoris");
            Button aviss = new Button("Ajouter Avis");

           absenceapp.addActionListener(e->{
                ApprenantAbsenceForm aaf = new ApprenantAbsenceForm();
              aaf.show();
               
            });
         
               favoris.addActionListener(e->{
              ApprenantFovorisForm fm = new ApprenantFovorisForm();
               fm.show();
               
            });
     
             cour.addActionListener(e->{
               CoursApprenantForm crp = new CoursApprenantForm();
               crp.show();
               
            });
             empl.addActionListener(e->{
               EmploitApprenantForm emp = new EmploitApprenantForm();
               emp.show();
               
            });
               aviss.addActionListener(e->{
               AvisApprenantForm v = new AvisApprenantForm();
               v.show();
               
            });
               
         Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
                InfoForm cf = new InfoForm();
                           inf.add(cour);
                           inf.add(absenceapp);
                           inf.add(noteapp);
                           inf.add(empl);
                            inf.add(favoris);
                            inf.add(aviss);
 
                cf.add(inf);
                cf.show();
               
    
               cf.show(); 
               
            
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
            
           
             FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, s);
              getToolbar().addCommandToRightSideMenu("Logout", icon1, (e) -> {
                  MyApplication.u = null ; 
            LoginForm cf = new LoginForm();
               cf.show();
        });
              
              
        Container c1=new Container(BoxLayout.x());
        Container c2=new Container(BoxLayout.y());
        Container c3=new Container(BoxLayout.y());
        ComboBox cEnseignant=new ComboBox();
        ArrayList<User> list=ServiceUserEnseignant.getInstance().getallEnseignant();
   
        
        TextField avis = new TextField("","avis");
    
            
        
             
             
        for(User l :list){
            cEnseignant.addItem(l.getPrenom());
        }
        
        ImageViewer ens = null;
        try {
            ens = new ImageViewer(Image.createImage("/AvisApp.jpg").scaled(1200,900));
        } catch (IOException ex) {  
            System.out.println(ex.toString());
        }         

         Container t=new Container(new FlowLayout(CENTER, CENTER));
         Label l3=new Label("Bienvenu: "+" "+MyApplication.u.getPrenom());
         l3.getAllStyles().setFgColor(0x0000FF);
         l3.setHeight(1000);
         t.add(l3);

Label p = new Label("Choisir Votre Enseignant:");
 p.getAllStyles().setFgColor(0xFF0000);
Label p1 = new Label("Votre Avis:");
 p1.getAllStyles().setFgColor(0xFF0000);

        Button btnValider = new Button("Ajouter Avis");      
        c2.add(p);
        c2.add(cEnseignant);
        c2.add(p1);
        c2.add(avis);
        c2.add(btnValider);       
        c3.add(c1);
        c3.add(c2);
        add(t);
        add(ens);
        add(c3);
        btnValider.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((avis.getText().length()==0)||cEnseignant.getSelectedItem()==null){
                    Dialog.show("Alert", "Vérfier les champs Remplit", new Command("OK"));}
            
                
           
                else
                {
                    
                   ArrayList<User> liste=ServiceUserEnseignant.getInstance().getallEnseignantByName(cEnseignant.getSelectedItem().toString());
                      for(User l1 :liste){  
                     System.out.println("jgh"+l1.getPrenom()+l1.getId());
        
                   Avis a = new Avis(MyApplication.u.getId(),
                           avis.getText(), 
                           l1.getId());
                                
                      
                        if( ServiceAvisApprenant.getInstance().addAvis(a)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
//                          ServiceSms sms =new ServiceSms();
//                          sms.getInstance().SmS(Integer.valueOf(MyApplication.u.getTel()));
                        }
                        else{
                            Dialog.show("ERROR", "Server error", new Command("OK"));}
                }}
            }
        });     
         
    }
}

    
    
    

