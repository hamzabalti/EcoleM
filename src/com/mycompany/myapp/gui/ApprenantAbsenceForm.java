/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Absence;
import com.mycompany.myapp.entities.Clase;
import com.mycompany.myapp.entities.Matiere;
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceRecupAbsence;
import com.mycompany.myapp.services.ServiceRecupClasse;
import com.mycompany.myapp.services.ServiceRecupMatiere;
import com.mycompany.myapp.services.ServiceRecupNote;
import com.mycompany.myapp.services.ServiceUserApprenant;
import java.io.IOException;

/**
 *
 * @author acer
 */
public class ApprenantAbsenceForm extends Form{
            String ch="";

    public ApprenantAbsenceForm(){
                      super("List Absence", BoxLayout.y());

        
  
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
              
    


 User u=new User();
             Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    
 for(Absence a1:ServiceRecupAbsence.getInstance().getAbsence(MyApplication.u.getId())){
            //Container c2 = new Container(new GridLayout(1,5));
            

             Label a=new Label("                       ");
            Label b=new Label("                       ");
        
           Label spanLabel2=new Label();
                                                  spanLabel2.getAllStyles().setFgColor(0x000000);

           Label nbabsence=new Label(Integer.toString(a1.getNb_absence()));
                                                  nbabsence.getAllStyles().setFgColor(0x000000);

                        Label spanLabel1=new Label();
                                spanLabel1.getAllStyles().setFgColor(0x000000);




            for(Clase c:ServiceRecupClasse.getInstance().getClasse(a1.getC())){
               ch+=c.getNiveau()+c.getType();           
           }
           spanLabel2.setText(ch);
           
             
           String ch1="";
//           User u1=new User();
//           ServiceUser.getInstance().getUser(n1.getU());
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
           c1.add(pre);
           c1.add(spanLabel1);
            c1.add(classe);

          // c2.add(spanLabel);
           c1.add(spanLabel2);
                      c1.add(absence);

           c1.add(nbabsence);
           c1.add(a);
           c1.add(b);
          

          
       }
   
   ImageViewer ens = null;
        try {
            ens = new ImageViewer(Image.createImage("/AbsenApp.png").scaled(1200,900));
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
                 add(c1);

}
    
    
}
