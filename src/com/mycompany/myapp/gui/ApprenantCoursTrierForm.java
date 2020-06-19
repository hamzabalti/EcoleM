/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
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
public class ApprenantCoursTrierForm extends Form{
    
      public ApprenantCoursTrierForm() {
      super("Votre Liste Des Cour", BoxLayout.y());

        
        
               getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
CoursApprenantForm fr=new CoursApprenantForm();        
fr.show(); 
        });
              
  
      
      
       
       
      }
    
}
