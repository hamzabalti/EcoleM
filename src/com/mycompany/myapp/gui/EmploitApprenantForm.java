/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Avis;
import com.mycompany.myapp.entities.Clase;
import com.mycompany.myapp.entities.Emploi;
import com.mycompany.myapp.entities.Favoris;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ApprenantFavoris;
import com.mycompany.myapp.services.ServiceRecupClasse;
import com.mycompany.myapp.services.ServiceEmploitApprenant;
import com.mycompany.myapp.services.ServiceEmploitEnseignant;
import com.mycompany.myapp.services.ServiceUserEnseignant;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author acer
 */
public class EmploitApprenantForm extends Form{
     String ch1="";
    public EmploitApprenantForm() {
      
                      super("Votre Emploit", BoxLayout.y());

        
        
        Container inf = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Button empl = new Button("Consulter Emploi");
            Button cour = new Button("Consulter Cour");
            Button noteapp = new Button("Consulter Note");
            Button absenceapp = new Button("Consulter Les Absence");
            Button favoris = new Button("Consulter Favoris");
            Button avis = new Button("Ajouter Avis");
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
               avis.addActionListener(e->{
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
                            inf.add(avis);
 
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
              
          Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

 for(Emploi e1:ServiceEmploitApprenant.getInstance().getEmploitApprenant(MyApplication.u.getId())){
    
            


            
           Label spanLabel1=new Label();
           spanLabel1.getAllStyles().setFgColor(0x000000);
           Label emploi=new Label(e1.getEmploit());
          

           emploi.addPointerPressedListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
                ServiceEmploitApprenant emploitApprenant=new ServiceEmploitApprenant();
             ArrayList<Emploi> em =emploitApprenant.RechercheEmploiByName(emploi.getText());
             String ch="";

  for(Emploi e1:em){
      ch+=e1.getId();
}
                int a=Integer.valueOf(ch);
                Favoris f=new Favoris(MyApplication.u.getId(), a);
                ApprenantFavoris.getInstance().addFavoris(f);
                System.out.println("hhhhhhhh"+a); 
                Dialog.show("Succees", "Emploi est Ajouter Dans Favoris  ", new Command("OK"));
                
               }
               
           });
           
           emploi.getAllStyles().setFgColor(0x000000);
           Label n_emploit=new Label(e1.getN_emploit());
           n_emploit.getAllStyles().setFgColor(0x000000);

            Label a=new Label("                       ");
            Label b=new Label("                       ");
            
         for(Clase c:ServiceRecupClasse.getInstance().getClasse(e1.getClasse())){
               ch1+=c.getNiveau()+c.getType();           
           }
             
           spanLabel1.setText(ch1);            
           Label Pre=new Label("Classe:");
            Pre.getAllStyles().setFgColor(0xFF0000);
            
            c1.add(Pre);
           c1.add(spanLabel1);
           
           Label Empl=new Label("Description:");
            Empl.getAllStyles().setFgColor(0xFF0000);
           c1.add(Empl);
           c1.add(n_emploit);
           Label Des=new Label("Epmloit:");
            Des.getAllStyles().setFgColor(0xFF0000);
           c1.add(Des);
           c1.add(emploi);
           
           c1.add(a);
           c1.add(b);
         
       }
  Label clicker=new Label("Cliquer Sur Nom de l'emploi Pour le Favoris:");
                      clicker.getAllStyles().setFgColor(0x0000FF);
    ImageViewer ens = null;
        try {
            ens = new ImageViewer(Image.createImage("/Emplt.jpg").scaled(1200,900));
        } catch (IOException ex) {  
            System.out.println(ex.toString());
        }         
       
  Container c2=new Container(new FlowLayout(CENTER, CENTER));

         Container t=new Container(new FlowLayout(CENTER, CENTER));
         Label l3=new Label("Bienvenu: "+" "+MyApplication.u.getPrenom());
         l3.getAllStyles().setFgColor(0x0000FF);
         l3.setHeight(1000);
         t.add(l3);
         c2.add(ens);
         add(t);
                  add(c2);
                  add(clicker);
     
           add(c1);

    
    
} 
    
    
}
