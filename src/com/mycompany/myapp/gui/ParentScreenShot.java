/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
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
import com.codename1.ui.util.ImageIO;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Clase;
import com.mycompany.myapp.entities.Matiere;
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceRecupClasse;
import com.mycompany.myapp.services.ServiceRecupMatiere;
import com.mycompany.myapp.services.ServiceRecupNote;
import com.mycompany.myapp.services.ServiceUserApprenant;
import java.io.IOException;
import java.io.OutputStream;

public class ParentScreenShot extends Form{
    String ch1="";
    public ParentScreenShot(){
         
             super("List Note", BoxLayout.y());
             
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
             ParentNoteForm parentNoteForm=new ParentNoteForm();
             
                parentNoteForm.show();          
        });

                     
              Container t=new Container(new FlowLayout(CENTER, CENTER));
         Label l3=new Label("Bienvenu Mr(s)"+" "+MyApplication.u.getPrenom());
         l3.getAllStyles().setFgColor(0x0000FF);
         l3.setHeight(1000);
         t.add(l3);
         add(t);
         
           
         Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
         
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_CAMERA, e->{
               Image screenshot = Image.createImage(getWidth(), getHeight());
        revalidate();
        setVisible(true);
        paintComponent(screenshot.getGraphics(), true);

        String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
               System.out.println(imageFile);
        try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
            ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
        } catch(IOException err) {
            Log.e(err);
        }
           });
         
  
//              
//             Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
// for(Note n1:ServiceRecupNote.getInstance().getAllNote(MyApplication.u.getId())){
//           
//           Label spanLabel1=new Label(Double.toString(n1.getMoyenne()));
//                                                                        spanLabel1.getAllStyles().setFgColor(0x000000);
//
//           Label spanLabel2=new Label();
//                                                                        spanLabel2.getAllStyles().setFgColor(0x000000);
//
//           Label orale=new Label(Double.toString(n1.getNote_orale()));
//                                         orale.getAllStyles().setFgColor(0x000000);
//
//           Label ecrit=new Label(Double.toString(n1.getNote_ecrit()));
//                                         ecrit.getAllStyles().setFgColor(0x000000);
//
//           Label moy=new Label(Double.toString(n1.getMoyenne()));
//                                         moy.getAllStyles().setFgColor(0x000000);
//
//           String ch="";
//          for(Matiere m1:ServiceRecupMatiere.getInstance().getAllMatiere(n1.getM())){
//               ch+=m1.getNom_matiere();           
//           }
//           spanLabel2.setText(ch);
//                                         spanLabel2.getAllStyles().setFgColor(0x000000);
//
//             String ch1="";
//         for(User u1:ServiceUserApprenant.getInstance().getUser(n1.getU())){
//               ch1+=u1.getPrenom();           
//           }
//            spanLabel1.setText(ch1);
//                              spanLabel1.getAllStyles().setFgColor(0x000000);
//
//            
//            
//            Label pre=new Label("Le Prenom:");
//                              pre.getAllStyles().setFgColor(0xFF0000);
//
//            Label mat=new Label("La Matiere:");
//                              mat.getAllStyles().setFgColor(0xFF0000);
//
//            Label NoteO=new Label("La Note d'Orale:");
//                              NoteO.getAllStyles().setFgColor(0xFF0000);
//
//            Label NoteE=new Label("La Note d'Ecrit:");
//                              NoteE.getAllStyles().setFgColor(0xFF0000);
//
//            Label Moy=new Label("La Moyenne:");
//                              Moy.getAllStyles().setFgColor(0xFF0000);
//                              
//            Label a=new Label("                       ");
//            Label b=new Label("                       ");
//
//           c1.add(pre);
//           c1.add(spanLabel1);
//             c1.add(mat);
//
//           c1.add(spanLabel2);
//           c1.add(NoteO);
//           c1.add(orale);
//           c1.add(NoteE);
//
//           c1.add(ecrit);
//           c1.add(Moy);
//
//           c1.add(moy);
//           c1.add(a);
//           c1.add(b);
//        
//       }
// 
//  ImageViewer ens = null;
//        try {
//            ens = new ImageViewer(Image.createImage("/NoteeAp.jpg").scaled(1200,900));
//        } catch (IOException ex) {  
//            System.out.println(ex.toString());
//        }         
//        Container c2=new Container(new FlowLayout(CENTER, CENTER));
//       
//         c2.add(ens);
//        
//                  add(c2);
//                   add(c1);   
}
        }
