
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Clase;
import com.mycompany.myapp.entities.Emploi;
import com.mycompany.myapp.entities.Matiere;
import com.mycompany.myapp.entities.cour;
import com.mycompany.myapp.services.ServiceAllMatiere;
import com.mycompany.myapp.services.ServiceRecupClasse;
import com.mycompany.myapp.services.ServiceRecupCour;
import com.mycompany.myapp.services.ServiceRecupMatiere;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class CoursApprenantForm extends Form{
                 Container af=new Container(BoxLayout.y());
                              int i=0; 
             
             
             String ch1="";
    
    public CoursApprenantForm() {
            
                                 super("Votre Cour", BoxLayout.y());

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
                 
             getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_SEARCH, e -> {
            RechercheCourApprennantForm h = new RechercheCourApprennantForm();
            
            h.show();
        });
        
            
           
             FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, s);
              getToolbar().addCommandToRightSideMenu("Logout", icon1, (e) -> {
                  MyApplication.u = null ; 
            LoginForm cf = new LoginForm();
               cf.show();
        });
              
               
        

        
        ArrayList<Matiere> list=ServiceAllMatiere.getInstance().getaMatiere();
       Container c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

     
                       
      for(cour cour1:ServiceRecupCour.getInstance().getCour(MyApplication.u.getId())){
                           
                           i++;
       

           Label classe=new Label();
           classe.getAllStyles().setFgColor(0x000000);
           Label matiere=new Label();
           matiere.getAllStyles().setFgColor(0x000000);
           Label courpdf=new Label(cour1.getCour_pdf());
           courpdf.getAllStyles().setFgColor(0x000000);
           Label nom=new Label(cour1.getNom());
           nom.getAllStyles().setFgColor(0x000000);
           Label a=new Label("                       ");
           Label b=new Label("                       ");
            

         for(Clase c:ServiceRecupClasse.getInstance().getClasse(cour1.getClasse())){
               ch1+=c.getNiveau()+c.getType();           
           }
             
            classe.setText(ch1);
             String ch2="";

         for(Matiere m1:ServiceRecupMatiere.getInstance().getAllMatiere(cour1.getMatiere())){
               ch2+=m1.getNom_matiere();           
           }
            matiere.setText(ch2);
             
            Label ma=new Label("La Matiere:");
    ma.getAllStyles().setFgColor(0xFF0000);
    
   

    af.add(ma);
           af.add(matiere);
                  Label classee=new Label("La Classe:");
    classee.getAllStyles().setFgColor(0xFF0000);
       af.add(classee);
           af.add(classe);
            Label desc=new Label("La Description:");
    desc.getAllStyles().setFgColor(0xFF0000);
       af.add(desc);
       af.add(nom);
 Label courrpdf=new Label("Votre Cours:");
    courrpdf.getAllStyles().setFgColor(0xFF0000);
       af.add(courrpdf);
       af.add(courpdf);
       af.add(a);
       af.add(b);


                                 
       }
        Label nbr = new Label("Les Nombre Des Cours="+i);
    nbr.getAllStyles().setFgColor(0x0000FF);

     
       Container t=new Container(new FlowLayout(CENTER, CENTER));
         Label l3=new Label("Bienvenu: "+" "+MyApplication.u.getPrenom());
         l3.getAllStyles().setFgColor(0x0000FF);
         l3.setHeight(1000);
         t.add(l3);
                Container c2=new Container(new FlowLayout(CENTER, CENTER));

               ImageViewer ens = null;
        try {
            ens = new ImageViewer(Image.createImage("/Cour.jpg").scaled(1200,900));
        } catch (IOException ex) {  
            System.out.println(ex.toString());
        } 
          c2.add(ens);
           

 
  
 
    add(t);
    add(c2);
    add(nbr);
    add(af);
   
} 
}

