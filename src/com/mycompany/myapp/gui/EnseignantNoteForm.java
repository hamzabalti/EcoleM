/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
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
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;

public class EnseignantNoteForm extends Form{
    TextField NoteOrale;
        TextField NoteEcrit;
    TextField Moyenne;
int j=0;
   public EnseignantNoteForm() {
       
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
                Home cf = new Home();
                   
                cf.show();          
        });
               Style s = UIManager.getInstance().getComponentStyle("TitleCommand");

             FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
       
          getToolbar().addCommandToRightSideMenu("Votre Emploit", icons, (e) -> {
            EmploitEnseignantForm cf = new EmploitEnseignantForm();
               cf.show();
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
           
       
        setTitle("Ajouter Note");
        setLayout(BoxLayout.y());
        Container c1=new Container(BoxLayout.x());
        Container c2=new Container(BoxLayout.y());
        Container c3=new Container(BoxLayout.y());
        ArrayList<Clase> list=ServiceRecupClasse.getInstance().getClasses();
                     String ch2="";

  for(Matiere m1:ServiceRecupMatiere.getInstance().getAllMatiere(MyApplication.u.getM())){
               ch2+=m1.getNom_matiere(); 
                              System.out.println("Matiere nchalah ye5dem");

           }
        
        TextField NoteOrale = new TextField("","Note Orale");
        TextField NoteEcrit= new TextField("", "Note Ecrit");
        TextField Moyenne= new TextField("", "Moyennne");    
        ComboBox cApprenant=new ComboBox();
        ComboBox cClasse=new ComboBox();
        Label nb=new Label("Nbr Des Apprenants Dans Cette Classe=:"+j);
                    nb.getAllStyles().setFgColor(0x0000FF);

        for(Clase cc :list){
            cClasse.addItem(Integer.toString(cc.getNiveau())+cc.getType());
        }
        cClasse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               String ch= cClasse.getSelectedItem().toString();
               int niveau=Integer.parseInt(ch.substring(0,ch.length()-1));
               char type=ch.charAt(ch.length()-1);
               ArrayList<User> users=ServiceUserApprenant.getInstance().getUserAffecter(niveau, type);
                for(User u :users){   
               cApprenant.addItem(u.getPrenom());
               j++;
               
        }
                                        
                        nb.setText("Nbr Des Apprenants Dans Cette Classe=:"+Integer.toString(j));

        
               // System.out.println(users.get(0).getId());
            }
            
        });
        
        //String cha=cApprenant.getSelectedItem().toString();
         ImageViewer ens = null;
        try {
            ens = new ImageViewer(Image.createImage("/noteEns.jpg").scaled(1200,900));
        } catch (IOException ex) {  
            System.out.println(ex.toString());
        }         

          String ch3="";
  for(Matiere m1:ServiceRecupMatiere.getInstance().getAllMatiere(MyApplication.u.getM())){
               ch3+=m1.getNom_matiere(); 
                              System.out.println("Matiere nchalah ye5dem");

           }
           Label l=new Label("Bienvenu Mr(s)"+" "+MyApplication.u.getPrenom()+" "+"Votre Spécialité"+" "+ch3);
          l.getAllStyles().setFgColor(0x0000FF);
         l.setHeight(1000);
         

            Button btnValider = new Button("Affecter Note");      
        
            Label mat=new Label("Votre Spécialité:"+" "+ch2);
             mat.getAllStyles().setFgColor(0xFF0000);
            Label NoteO=new Label("La Note d'Orale:");
             NoteO.getAllStyles().setFgColor(0xFF0000);
            Label NoteE=new Label("La Note d'Ecrit:");
             NoteE.getAllStyles().setFgColor(0xFF0000);
            Label Moy=new Label("La Moyenne:");
             Moy.getAllStyles().setFgColor(0xFF0000);

             
            Label espace=new Label("                                                   ");

             
        c1.add(mat);
        
        c2.add(cClasse);
        c2.add(espace);
        c2.add(cApprenant);
        c2.add(NoteO);
        c2.add(NoteOrale);
                c2.add(NoteE);

        c2.add(NoteEcrit);
                c2.add(Moy);

        c2.add(Moyenne);
        c2.add(btnValider);       
        c3.add(c1);
        c3.add(c2);
         add(l);
        add(ens);
        add(nb);
        add(c3);
        
 
Moyenne.addPointerReleasedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            double tE=Double.parseDouble(NoteEcrit.getText());
            double tO=Double.parseDouble(NoteOrale.getText()); 
      
            String v=String.valueOf((tO+tE*2)/3);
//          String v1=String.valueOf(df.format(v));
             Moyenne.setText(v);
      
            
     // double moy=Double.parseDouble(Moyenne.getText());
            }
        });
  
        btnValider.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if( (ServiceRecupNote.getInstance().isStringOnlyAlphabet(NoteOrale.getText())==true) || (ServiceRecupNote.getInstance().isStringOnlyAlphabet(NoteEcrit.getText())==true)){
                Dialog.show("Alert", "Les Champs Doit étre Entier", new Command("OK"));}
               
                
                else  if ((NoteOrale.getText().length()==0)||(NoteEcrit.getText().length()==0) ||(Moyenne.getText().length()==0)||(cApprenant.getSelectedItem()==null)){
                    Dialog.show("Alert", "Vérfier les champs Remplit", new Command("OK"));}
                
                else if((Double.parseDouble(NoteOrale.getText())>20) || (Double.parseDouble(NoteEcrit.getText())>20)||(Double.parseDouble(Moyenne.getText())>20)||(Double.parseDouble(NoteOrale.getText())<0) || (Double.parseDouble(NoteEcrit.getText())<0)||(Double.parseDouble(Moyenne.getText())<0)){
                    Dialog.show("Alert", "Les Nombres doit etre entre 20 Et 0", new Command("OK"));}

              
                
               
                
                else {
                     ArrayList<User> us=ServiceUserApprenant.getInstance().getApprenantByName(cApprenant.getSelectedItem().toString());
                for(User u :us){   
                    System.out.println("idmatier"+ MyApplication.u.getM());
                        Note n = new Note(
                                u.getId(),
                                MyApplication.u.getM(), 
                                Double.parseDouble(NoteOrale.getText()), 
                                Double.parseDouble(NoteEcrit.getText()), 
                                Double.parseDouble(Moyenne.getText()));
                                
                                
                        if( ServiceRecupNote.getInstance().addNote(n))
                            Dialog.show("Success","Les Notes Sont Affecter",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
//                    } catch (NumberFormatException e) {
//                        Dialog.show("ERROR", "Les Notes must be a number", new Command("OK"));
//                    }
                }}
            }
        });  

        
    }
//        private double moyenne(MouseEvent event) {
////        DecimalFormat df=new DecimalFormat("0.00");
//         double tE=Double.parseDouble(NoteEcrit.getText());
//      double tO=Double.parseDouble(NoteOrale.getText()); 
//      
//      String v=String.valueOf((tO+tE*2)/3);
////      String v1=String.valueOf(df.format(v));
//      Moyenne.setText(v);
//      
//            
//      double moy=Double.parseDouble(Moyenne.getText());
//
//      
//      return moy;
//        
//    }
   
}
