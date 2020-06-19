/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import static com.codename1.push.PushContent.setTitle;
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
import com.mycompany.myapp.entities.Emploi;
import com.mycompany.myapp.entities.Matiere;
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceEmploitEnseignant;
import com.mycompany.myapp.services.ServiceRecupMatiere;
import com.mycompany.myapp.services.ServiceRecupNote;
import com.mycompany.myapp.services.ServiceUserApprenant;
import com.mycompany.myapp.services.ServiceUserEnseignant;
import java.io.IOException;

/**
 *
 * @author acer
 */
public class EmploitEnseignantForm extends Form{

    public EmploitEnseignantForm() {
        setTitle("Votre Emploit");
        
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
                Home cf = new Home();
                  
                cf.show();          
        });
                 Style s = UIManager.getInstance().getComponentStyle("TitleCommand");

        
              FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
          getToolbar().addCommandToRightSideMenu("Affecter Note", icons, (e) -> {
            EnseignantNoteForm c = new EnseignantNoteForm();
               c.show();
        });
             getToolbar().addCommandToRightSideMenu("Voir Les Avis", icons, (e) -> {
            AvisEnseignantForm c = new AvisEnseignantForm();
               c.show();
        });
             
                    FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, s);
              getToolbar().addCommandToRightSideMenu("Logout", icon1, (e) -> {
                  MyApplication.u = null ; 
            LoginForm cf = new LoginForm();
               cf.show();
        });
          Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    
 for(Emploi e1:ServiceEmploitEnseignant.getInstance().getEmploitEnseignant(MyApplication.u.getId())){
            //Container c2 = new Container(new GridLayout(1,6));
          //  System.out.println(e1.getUser());
            

            
           Label spanLabel1=new Label();
                                                                        spanLabel1.getAllStyles().setFgColor(0x000000);

           Label emploi=new Label(e1.getEmploit());
                                                                        emploi.getAllStyles().setFgColor(0x000000);

           Label n_emploit=new Label(e1.getN_emploit());
                                                                        n_emploit.getAllStyles().setFgColor(0x000000);


             


    
           
             String ch1="";
         for(User u1:ServiceUserEnseignant.getInstance().getallUserEn(e1.getUser())){
               ch1+=u1.getPrenom();           
                    System.out.println(u1.getPrenom());
  
         }
             
            spanLabel1.setText(ch1);

 Label a=new Label("                       ");
            Label b=new Label("                       ");
  
          Label Pre=new Label("Prenom:");
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
  ImageViewer ens = null;
        try {
            ens = new ImageViewer(Image.createImage("/emploiEns.png").scaled(1200,900));
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
                  add(c1);

    
    
}

  
}
        
        
        
        
        
        
        
        
        
        
        
        
        
    
    
    
    
    

