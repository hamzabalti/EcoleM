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
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Clase;
import com.mycompany.myapp.entities.Matiere;
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceClasseApprennant;
import com.mycompany.myapp.services.ServiceParentConsulteNote;
import com.mycompany.myapp.services.ServiceParentEnfant;
import com.mycompany.myapp.services.ServiceRecupMatiere;
import com.mycompany.myapp.services.ServiceRecupNote;
import com.mycompany.myapp.services.ServiceUserApprenant;
import java.io.IOException;

/**
 *
 * @author acer
 */
//public class ParentNoteForm extends Form{
//    public ParentNoteForm(){
//                 super("List Note Mes Enfants", BoxLayout.y());
//
//         
//           Container t=new Container(new FlowLayout(CENTER, CENTER));
//         Label l3=new Label("Bienvenu Mr(s) "+" "+MyApplication.u.getPrenom());
//         l3.getAllStyles().setFgColor(0x0000FF);
//         l3.setHeight(1000);
//         t.add(l3);
//         add(t);
//         
//          Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
//        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
//         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
//       
//        Toolbar tb = getToolbar();
//        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
//            Home h = new Home();
//            h.show();
//        });
//       
//               getToolbar().addCommandToRightSideMenu("Garderies", icons, (e) -> {
//            ListGarderieForm cf = new ListGarderieForm();
//               cf.show();
//        });
//           
//            getToolbar().addCommandToRightSideMenu("Reservations", icons, (e) -> {
//            ReservationListForm cf = new ReservationListForm();
//               cf.show();
//        });
//       
//             if(MyApplication.u.getRoles().equals("ROLE_PARENT")){
//              getToolbar().addCommandToRightSideMenu("Ajouter Enfants", icons, (e) -> {
//            AddEnfant cf = new AddEnfant();
//               cf.show();
//        }); 
//              
//            }
//   
//                        getToolbar().addCommandToRightSideMenu("Absence des Enfants", icons, (e) -> {
//            ParentAbsenceForm cf = new ParentAbsenceForm();
//               cf.show();
//        });
//             
//             FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, s);
//              getToolbar().addCommandToRightSideMenu("Logout", icon1, (e) -> {
//                  MyApplication.u = null ; 
//            LoginForm cf = new LoginForm();
//               cf.show();
//        });
//         
//            // Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//             Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//             
//
//      for(Note n1:ServiceParentConsulteNote.getInstance().getAllNote(MyApplication.u.getId())){
//           Label spanLabel2=new Label();
//                                                                        spanLabel2.getAllStyles().setFgColor(0x000000);
//
//                      Label spanLabel3=new Label();
//                                                                         spanLabel3.getAllStyles().setFgColor(0x000000);
//
//
//           Label orale=new Label(Double.toString(n1.getNote_orale()));
//                                                                        orale.getAllStyles().setFgColor(0x000000);
//
//           Label ecrit=new Label(Double.toString(n1.getNote_ecrit()));
//                                                                        ecrit.getAllStyles().setFgColor(0x000000);
//
//           Label moy=new Label(Double.toString(n1.getMoyenne()));
//                                                                        moy.getAllStyles().setFgColor(0x000000);
//
//           String ch="";
//          for(Matiere m1:ServiceRecupMatiere.getInstance().getAllMatiere(n1.getM())){
//               ch+=m1.getNom_matiere();           
//           }
//           spanLabel3.setText(ch);
//           
//             String ch1="";
//         for(User u1:ServiceUserApprenant.getInstance().getUser(n1.getU())){
//               ch1+=u1.getPrenom();           
//           }
//           spanLabel2.setText(ch1);
//            Label a=new Label("                       ");
//            Label b=new Label("                       ");
//            
//            
//           
//            Label pre=new Label("Le Prenom:");
//             pre.getAllStyles().setFgColor(0xFF0000);
//            Label mat=new Label("La Matiere:");
//             mat.getAllStyles().setFgColor(0xFF0000);
//            Label NoteO=new Label("La Note d'Orale:");
//             NoteO.getAllStyles().setFgColor(0xFF0000);
//            Label NoteE=new Label("La Note d'Ecrit:");
//             NoteE.getAllStyles().setFgColor(0xFF0000);
//            Label Moy=new Label("La Moyenne:");
//             Moy.getAllStyles().setFgColor(0xFF0000);
//           c2.add(pre);
//           c2.add(spanLabel2);
//                      c2.add(mat);
//
//           c2.add(spanLabel3);
//                      c2.add(NoteO);
//
//           c2.add(orale);
//                      c2.add(NoteE);
//
//           c2.add(ecrit);
//                      c2.add(Moy);
//
//           c2.add(moy);
//            c2.add(a);
//             c2.add(b);
//              
//             
//       
//             
//       }
//       ImageViewer ens = null;
//        try {
//            ens = new ImageViewer(Image.createImage("/noteParent.jpg").scaled(1200,900));
//        } catch (IOException ex) {  
//            System.out.println(ex.toString());
//        }         
//        Container c1=new Container(new FlowLayout(CENTER, CENTER));
//
//      
//         c1.add(ens);
//   
//                  add(c1);
//  add(c2);   
//}
//}



public class ParentNoteForm extends Form{
    public ParentNoteForm(){
                 super("List Note Mes Enfants", BoxLayout.y());

         
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
         
             Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

         for(User u1:ServiceParentEnfant.getInstance().getUser(MyApplication.u.getId())){
             
             for(Clase u5:ServiceClasseApprennant.getInstance().getClassee(u1.getId())){
             
         

             Label enf=new Label("Prenom:"+" "+u1.getPrenom());
            enf.getAllStyles().setFgColor(0xFF0000);

             Label enf0=new Label("Nom:"+" "+u1.getUsername()); 
              enf0.getAllStyles().setFgColor(0xFF0000);
             Label enf1=new Label("Classe:"+" "+u5.getNiveau()+""+u5.getType()); 
              enf1.getAllStyles().setFgColor(0xFF0000);
             Button b=new Button("Detaile");
             Label enf00=new Label("                        "); 
                                   b.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent evt) {
System.out.println("gggggg"+enf);      
            for(User u1:ServiceUserApprenant.getInstance().getApprenantByName(u1.getPrenom())){
                 
             Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
 for(Note n1:ServiceRecupNote.getInstance().getAllNote(u1.getId())){
          
           Label spanLabel1=new Label(Double.toString(n1.getMoyenne()));
                                                                        spanLabel1.getAllStyles().setFgColor(0x000000);

           Label spanLabel2=new Label();
                                                                        spanLabel2.getAllStyles().setFgColor(0x000000);

           Label orale=new Label(Double.toString(n1.getNote_orale()));
                                         orale.getAllStyles().setFgColor(0x000000);

           Label ecrit=new Label(Double.toString(n1.getNote_ecrit()));
                                         ecrit.getAllStyles().setFgColor(0x000000);

           Label moy=new Label(Double.toString(n1.getMoyenne()));
                                         moy.getAllStyles().setFgColor(0x000000);

           String ch="";
          for(Matiere m1:ServiceRecupMatiere.getInstance().getAllMatiere(n1.getM())){
               ch+=m1.getNom_matiere();           
           }
           spanLabel2.setText(ch);
                                         spanLabel2.getAllStyles().setFgColor(0x000000);

             String ch1="";
         for(User u2:ServiceUserApprenant.getInstance().getUser(n1.getU())){
               ch1+=u2.getPrenom();           
           }
            spanLabel1.setText(ch1);
                              spanLabel1.getAllStyles().setFgColor(0x000000);

            
            
            Label pre=new Label("Le Prenom:");
                              pre.getAllStyles().setFgColor(0xFF0000);

            Label mat=new Label("La Matiere:");
                              mat.getAllStyles().setFgColor(0xFF0000);

            Label NoteO=new Label("La Note d'Orale:");
                              NoteO.getAllStyles().setFgColor(0xFF0000);

            Label NoteE=new Label("La Note d'Ecrit:");
                              NoteE.getAllStyles().setFgColor(0xFF0000);

            Label Moy=new Label("La Moyenne:");
                              Moy.getAllStyles().setFgColor(0xFF0000);
                              
            Label a=new Label("                       ");
            Label b=new Label("                       ");

           c1.add(pre);
           c1.add(spanLabel1);
             c1.add(mat);

           c1.add(spanLabel2);
           c1.add(NoteO);
           c1.add(orale);
           c1.add(NoteE);

           c1.add(ecrit);
           c1.add(Moy);

           c1.add(moy);
           c1.add(a);
           c1.add(b);
        
       }
 
  ImageViewer ens = null;
        try {
            ens = new ImageViewer(Image.createImage("/NoteeAp.jpg").scaled(1200,900));
        } catch (IOException ex) {  
            System.out.println(ex.toString());
        }         
        Container c2=new Container(new FlowLayout(CENTER, CENTER));
       
         c2.add(ens);
        ParentScreenShot parentScreenShot=new ParentScreenShot();
            parentScreenShot.add(c2);
                        parentScreenShot.add(c1);

        
        parentScreenShot.show();
           
             
             
                
                
                
                
            }}
             });

             c2.add(enf);
             c2.add(enf0);
             c2.add(enf1);
             c2.add(b);
             c2.add(enf00);
           
         }
         }
    
       ImageViewer ens = null;
        try {
            ens = new ImageViewer(Image.createImage("/noteParent.jpg").scaled(1200,900));
        } catch (IOException ex) {  
            System.out.println(ex.toString());
        }         
        Container c1=new Container(new FlowLayout(CENTER, CENTER));

      
         c1.add(ens);
   
                  add(c1);
  add(c2);   

    }
}
