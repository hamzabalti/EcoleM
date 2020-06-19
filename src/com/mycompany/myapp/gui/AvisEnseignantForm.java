/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
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
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Avis;
import com.mycompany.myapp.entities.Matiere;
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceAvisApprenant;
import com.mycompany.myapp.services.ServiceAvisEnseignant;
import com.mycompany.myapp.services.ServiceNombreAvisEnseignant;
import com.mycompany.myapp.services.ServiceRecupMatiere;
import com.mycompany.myapp.services.ServiceRecupNote;
import com.mycompany.myapp.services.ServiceUserApprenant;
import com.mycompany.myapp.services.ServiceUserEnseignant;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class AvisEnseignantForm extends Form {
   public AvisEnseignantForm(){
       
             getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
                Home cf = new Home();
                  
                cf.show();          
        });
             
         Style s = UIManager.getInstance().getComponentStyle("TitleCommand");

             FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
          getToolbar().addCommandToRightSideMenu("Votre Emploit", icons, (e) -> {
            EmploitEnseignantForm c = new EmploitEnseignantForm();
               c.show();
        });
          
          getToolbar().addCommandToRightSideMenu("Affecter Votre Note", icons, (e) -> {
            EnseignantNoteForm c = new EnseignantNoteForm();
               c.show();
        });
          
                 FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, s);
              getToolbar().addCommandToRightSideMenu("Logout", icon1, (e) -> {
                  MyApplication.u = null ; 
            LoginForm cf = new LoginForm();
               cf.show();
        });
              
         setTitle("List Avis");
 int i=0;
             Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
         for(Avis a1:ServiceAvisEnseignant.getInstance().getAvis(MyApplication.u.getId())){
             
            i++;
             System.out.println(i);
            //Container c2 = new Container(new GridLayout(1,5));
            //System.out.println(n1.getId()+" "+n1.getMoyenne());
          // SpanLabel spanLabel2=new SpanLabel();
           Label avis=new Label(a1.getAvis());
                    avis.getAllStyles().setFgColor(0x000000);

         //  SpanLabel prenom=new SpanLabel();
                     Label prenom=new Label();
         prenom.getAllStyles().setFgColor(0x000000);

           
             String ch1="";
         for(User u1:ServiceUserApprenant.getInstance().getUser(a1.getApprenant())){
               ch1+=u1.getPrenom();           
           }
         prenom.setText(ch1);
         
        // Label nb=new Label();
         //int a=ServiceNombreAvisEnseignant.getInstance().CountAvis(10);
       
          Label a=new Label("                       ");
            Label b=new Label("                       ");
         Label p = new Label("L'apprenant:");
         p.getAllStyles().setFgColor(0xFF0000);
         Label p1 = new Label("Son Avis:");
         p1.getAllStyles().setFgColor(0xFF0000);
           
           c1.add(p);
           c1.add(prenom);
           c1.add(p1);
           c1.add(avis);
           c1.add(a);
           c1.add(b);
        
           //c1.add(c2);
       }
            Label nb=new Label("Nombre des Votre Avis ="+i);
            nb.getAllStyles().setFgColor(0x0000FF);
                                 Label espace=new Label("                          ");


          ImageViewer ens = null;
        try {
            ens = new ImageViewer(Image.createImage("/AvisEns.jpg").scaled(1200,900));
        } catch (IOException ex) {  
            System.out.println(ex.toString());
        }         
        Container c2=new Container(new FlowLayout(CENTER, CENTER));
        String ch2="";
  for(Matiere m1:ServiceRecupMatiere.getInstance().getAllMatiere(MyApplication.u.getM())){
               ch2+=m1.getNom_matiere(); 
                              System.out.println("Matiere nchalah ye5dem");

           }
           Label l=new Label("Bienvenu Mr(s)"+" "+MyApplication.u.getPrenom()+" "+"Votre Spécialité"+" "+ch2);
          l.getAllStyles().setFgColor(0x0000FF);
         l.setHeight(1000);
       
         
         c2.add(ens);
         add(l);
                  add(c2);
                  add(nb);
                  add(espace);
  add(c1);   
}

}