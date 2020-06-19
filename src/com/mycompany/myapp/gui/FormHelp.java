/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;

/**
 *
 * @author acer
 */
public class FormHelp extends Form{

    public FormHelp() {
        setTitle("Plus d'information");
        Container c1=new Container(new FlowLayout(CENTER, CENTER));
  
         
Label l99=new Label("Description:");
l99.getAllStyles().setFgColor(0xFF0000);
Label l100 = new Label("                               ");
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
           LoginForm cf = new LoginForm();
               cf.show(); 
        });
         ImageViewer inter= null;
        try {
            inter = new ImageViewer(Image.createImage("/help.png").scaled(1200,900));
        } catch (IOException ex) {  
            System.out.println(ex.toString());
        }   
        c1.add(inter);
        c1.add(l100);

 SpanLabel s = new SpanLabel("développer Trois plateformes de gestion d'écoles primaire avec une version\n" +
"mobile Minimisé pour les parents Les Apprennants et Les Enseignants, et une version web,Desktop  pour admin, employés,\n" +
"apprenants enseignants et parent avec une URL spécifique pour chaque établissement");
 
 
 
 add(c1);
 add(l99);
 add(s);
 show();
    }
    
    
}
