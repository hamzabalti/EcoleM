  
//
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.UserService;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Matiere;
import com.mycompany.myapp.services.ServiceRecupMatiere;
import java.util.ArrayList;
import com.mycompany.myapp.utils.WebService;
import java.io.IOException;

/**
 *
 * @author Jasser
 */
public class Home extends BaseForm{

    
    
    
    
    
    
    
    
    
    public Home(){
    
    super("Home", BoxLayout.y());
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_NOTIFICATIONS, e -> {
            NotificationForm r = new NotificationForm();
            r.show();
        });
    
            Container c2=new Container(new FlowLayout(CENTER, CENTER));
            Container titre=new Container(new FlowLayout(CENTER, CENTER));
                        Container titre1=new Container(new FlowLayout(CENTER, CENTER));



            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            Container photo = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            Container ens = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            Container inf = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Container prof=new Container(new FlowLayout(CENTER, CENTER));
            Container elv=new Container(new FlowLayout(CENTER, CENTER));
            
     



            Button b = new Button("Espace Evenements");
            Button c = new Button("Espace Mon Livre");
            Button r = new Button("Espace Garderie");
            Button L = new Button("Logout");
            Button en = new Button("Ajouter Enfants");
            Button note = new Button("Consulter Note");
            Button absence = new Button("Consulter Absence");
            Button empl = new Button("Consulter Emploi");
            Button cour = new Button("Consulter Cour");
            Button noteapp = new Button("Consulter Note");
            Button absenceapp = new Button("Consulter Les Absence");
            Button favoris = new Button("Consulter Favoris");
            Button avis = new Button("Ajouter Avis");

            Button ajoutnote = new Button("Affecter La Note");
            Button affichem = new Button("Afficher Emploi");
            Button affichav = new Button("Afficher Les Avis");
            Button info = new Button("Consulter Info");
  
            
   


           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            UserService as = new UserService();
            WebService ws = new WebService();
            
           
           int count = ws.getcount("count/"+MyApplication.u.getId());
            
            
           if (count > 0){
               Dialog.show("Notification", "Vous avez "+count+" Notifications", "Ok", null);
           }
          
           
            Label l2=new Label("Bienvenu Mr(s): "+" "+MyApplication.u.getPrenom());
          l2.getAllStyles().setFgColor(0x0000FF);
         l2.setHeight(1000);
             Label l3=new Label("Bienvenu: "+" "+MyApplication.u.getPrenom());
          l3.getAllStyles().setFgColor(0x0000FF);
         l3.setHeight(1000);

         
          
            if(MyApplication.u.getRoles().equals("ROLE_PARENT")){
                               Style ss = UIManager.getInstance().getComponentStyle("TitleCommand");     
FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_HELP, ss);
              getToolbar().addCommandToRightBar("Plus", icon1, (e) -> {
                  MyApplication.u = null ; 
            FormHelp cf = new FormHelp();
               cf.show();
        });   
                
                             getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_EXIT_TO_APP, e -> {
                LoginForm cf = new LoginForm();
             cf.showBack();
               
            
        });
              
                 ImageViewer para = null;
        try {
            para = new ImageViewer(Image.createImage("/pdgParent.jpg").scaled(1200,900));
        } catch (IOException ex) {  
            System.out.println(ex.toString());
        }     
             c2.add(para);
              photos.add(en);  
              photos.add(r);
              photos.add(note);
              photos.add(absence);

            }
            Label m = new Label("Espace Parent");
      m.getAllStyles().setFgColor(0xFF0000);
          Label m11 = new Label("Espace Apprennant");
      m11.getAllStyles().setFgColor(0xFF0000);
          Label m2 = new Label("Espace Enseignant");
      m2.getAllStyles().setFgColor(0xFF0000);
                        if(MyApplication.u.getRoles().equals("ROLE_PARENT")){
 titre.add(m);
 
 add(titre);
                        add(l2);
                       
                        }
                        else if(MyApplication.u.getRoles().equals("ROLE_ENSEIGNANT"))
 {
                   String ch3="";
  for(Matiere m1:ServiceRecupMatiere.getInstance().getAllMatiere(MyApplication.u.getM())){
               ch3+=m1.getNom_matiere(); 
                              System.out.println("Matiere nchalah ye5dem");

           }
           Label l4=new Label("Bienvenu Mr(s)"+" "+MyApplication.u.getPrenom()+" "+"Votre Spécialité"+" "+ch3);
          l4.getAllStyles().setFgColor(0x0000FF);
         l4.setHeight(1000);
       titre.add(m2);
       add(titre);
                                        add(l4);
                                        

                        }
                        else{
                              titre.add(m11);
 add(titre);
                        add(l3);
                       
                        }
            add(c2);

            add(photos);
            
            
                info.addActionListener(e->{
                InfoForm f = new InfoForm();
                           inf.add(cour);
                           inf.add(absenceapp);
                           inf.add(noteapp);
                           inf.add(empl);
                            inf.add(favoris);
                            inf.add(avis);
 
                f.add(inf);
                f.show();
               
            });
            
            
              absence.addActionListener(e->{
                ParentAbsenceForm af = new ParentAbsenceForm();
               af.show();
               
            });
            
             en.addActionListener(e->{
                AddEnfant cf = new AddEnfant();
               cf.show();
               
            });
              note.addActionListener(e->{
                ParentNoteForm nf = new ParentNoteForm();
               nf.show();
               
            });
                 noteapp.addActionListener(e->{
                ListNoteForm n1 = new ListNoteForm();
               n1.show();
               
            });
              absenceapp.addActionListener(e->{
              ApprenantAbsenceForm aaf = new ApprenantAbsenceForm();
              aaf.show();
               
            });
            c.addActionListener(e->{
              CategorieForm em = new CategorieForm();
               em.show();
            });
               favoris.addActionListener(e->{
              ApprenantFovorisForm fm = new ApprenantFovorisForm();
               fm.show();
               
            });
            b.addActionListener(e->{
                EspaceEvenement ee = new EspaceEvenement();
                ee.show();
               
            });
            r.addActionListener(e->{
               EspaceReservation er = new EspaceReservation();
               er.show();
               
            });
            L.addActionListener(e->{
                MyApplication.u = null ; 
                LoginForm lf = new LoginForm();
                lf.show();
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
                ajoutnote.addActionListener(e->{
               EnseignantNoteForm ennote = new EnseignantNoteForm();
               ennote.show();
               
            });
                affichem.addActionListener(e->{
               EmploitEnseignantForm azer = new EmploitEnseignantForm();
               azer.show();
               
            });
                affichav.addActionListener(e->{
               AvisEnseignantForm azert = new AvisEnseignantForm();
               azert.show();
               
            });
           
         
        if(MyApplication.u.getRoles().equals("ROLE_APPRENANT")){
          
                      Style ss = UIManager.getInstance().getComponentStyle("TitleCommand");     
FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_HELP, ss);
              getToolbar().addCommandToRightBar("Plus", icon1, (e) -> {
                  MyApplication.u = null ; 
            FormHelp cf = new FormHelp();
               cf.show();
        });   
            
                                 getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_EXIT_TO_APP, e -> {
                LoginForm cf = new LoginForm();
             cf.showBack();
               
            
        });
            
             ImageViewer elve = null;
        try {
            elve = new ImageViewer(Image.createImage("/elevclasse.jpg").scaled(1200,900));
        } catch (IOException ex) {  
            System.out.println(ex.toString());
        }      
                

             elv.add(elve);
            photo.add(b);
            photo.add(c);
           
            photo.add(info);


            }
                   add(elv);

           add(photo);
      
        if(MyApplication.u.getRoles().equals("ROLE_ENSEIGNANT")){
            
                                  Style ss = UIManager.getInstance().getComponentStyle("TitleCommand");     
FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_HELP, ss);
              getToolbar().addCommandToRightBar("Plus", icon1, (e) -> {
                  MyApplication.u = null ; 
            FormHelp cf = new FormHelp();
               cf.show();
        });   
                              getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_EXIT_TO_APP, e -> {
                LoginForm cf = new LoginForm();
             cf.showBack();
               
            
        });
       ImageViewer pr = null;
        try {
            pr = new ImageViewer(Image.createImage("/ensss.jpg").scaled(1200,900));
        } catch (IOException ex) {  
            System.out.println(ex.toString());
        }      
            
        
             prof.add(pr);

              ens.add(ajoutnote);
              ens.add(affichem);
              ens.add(affichav);
              
            }
             add(prof);

            add(ens);
           
           
        show();
        
       
        
        
    }
}    

