/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Clase;
import com.mycompany.myapp.entities.Matiere;
import com.mycompany.myapp.entities.cour;
import com.mycompany.myapp.services.ServiceAllMatiere;
import com.mycompany.myapp.services.ServiceRecupClasse;
import com.mycompany.myapp.services.ServiceRecupCour;
import com.mycompany.myapp.services.ServiceRecupMatiere;
import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class RechercheCourApprennantForm extends Form{

    public RechercheCourApprennantForm() {
      super("Votre Cour", BoxLayout.y());
       Container t=new Container(new FlowLayout(CENTER, CENTER));
         Label l3=new Label("Bienvenu: "+" "+MyApplication.u.getPrenom());
         l3.getAllStyles().setFgColor(0x0000FF);
         l3.setHeight(1000);
         t.add(l3);
         add(t);
         
               getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            CoursApprenantForm h = new CoursApprenantForm();
            h.show();
        });

                ArrayList<Matiere> list=ServiceAllMatiere.getInstance().getaMatiere();
       Container c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        ComboBox Combomatiere=new ComboBox("Choisir Votre Cour");
        Button btnValider = new Button("Recherche"); 

        
        c3.add(Combomatiere);
        c3.add(btnValider);
       
        
            for(Matiere mm :list){
            Combomatiere.addItem(mm.getNom_matiere());
        }
            
            btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 ServiceAllMatiere serviceAllMatiere=new ServiceAllMatiere();
                 ArrayList<Matiere> list= serviceAllMatiere.getMatiereByName(Combomatiere.getSelectedItem().toString());
                 for(Matiere l1 :list){  
                     System.out.println("IdMatiere"+l1.getId());
                     int a=l1.getId();
                    // Label y=new Label (Integer.toString(a));
                ApprenantCoursTrierForm apprenantCoursTrier=new ApprenantCoursTrierForm();
                                    Container c5 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                    Label l=new Label("Bienvenu"+"  "+MyApplication.u.getPrenom());
                      l.getAllStyles().setFgColor(0x0000FF);


                  ArrayList<cour> list2=ServiceRecupCour.getInstance().AfficheCourApprenantTrier(MyApplication.u.getId(),l1.getId());
                  for(cour cc :list2){
        
           Label classe=new Label();
                      classe.getAllStyles().setFgColor(0x000000);

           Label matiere=new Label();
                      matiere.getAllStyles().setFgColor(0x000000);


           Label courpdf=new Label(cc.getCour_pdf());
                       courpdf.getAllStyles().setFgColor(0x000000);

           Label nom=new Label(cc.getNom());
                  nom.getAllStyles().setFgColor(0x000000);
                  
                      
         String ch1="";
         for(Clase c:ServiceRecupClasse.getInstance().getClasse(cc.getClasse())){
               ch1+=c.getNiveau()+c.getType();  
               
               System.out.println("classe nchalah ye5dem");
           }        
            classe.setText(ch1);
             String ch2="";
         for(Matiere m1:ServiceRecupMatiere.getInstance().getAllMatiere(cc.getMatiere())){
               ch2+=m1.getNom_matiere(); 
                              System.out.println("Matiere nchalah ye5dem");

           }
          Label mati=new Label("La Matiere:");
    mati.getAllStyles().setFgColor(0xFF0000);
            matiere.setText(ch2);
            c5.add(l);
            c5.add(mati);
          c5.add(matiere);
           Label clasee=new Label("La Classe:");
    clasee.getAllStyles().setFgColor(0xFF0000);
    c5.add(clasee);
                  c5.add(classe);
                  
                        Label nomme=new Label("La Description du Cour:");
    nomme.getAllStyles().setFgColor(0xFF0000);
    c5.add(nomme);
                          c5.add(nom);
      Label pdf=new Label("Le PDF:");
    pdf.getAllStyles().setFgColor(0xFF0000);
c5.add(pdf);
        c5.add(courpdf); 
              Label aa=new Label("                        ");
              Label bb=new Label("                        ");
c5.add(aa);
c5.add(bb);

                  
                  
                  }
                apprenantCoursTrier.add(c5);
                apprenantCoursTrier.show();
            }
        }});
        
        
        
          add(c3); 
 
    }
    
}
