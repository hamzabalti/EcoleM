/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Garderie;
import com.mycompany.myapp.services.CategorieService;
import com.mycompany.myapp.services.ReservationsService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import com.mycompany.myapp.utils.WebService;

/**
 *
 * @author Jasser
 */
public class ListGarderieForm extends BaseForm{
    public ListGarderieForm(){

        super("Liste des Garderies", BoxLayout.y());

            Container t=new Container(new FlowLayout(CENTER, CENTER));
         Label l3=new Label("Bienvenu Mr(s)"+" "+MyApplication.u.getPrenom());
         l3.getAllStyles().setFgColor(0x0000FF);
         l3.setHeight(1000);
         t.add(l3);
        add(t);
        
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
       
//        Toolbar tb = getToolbar();
//        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
//            Home h = new Home();
//            h.show();
//        });
       
         
           
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
                           getToolbar().addCommandToRightSideMenu("Note des Enfants", icons, (e) -> {
            ParentNoteForm cf = new ParentNoteForm();
               cf.show();
        });
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
              
                  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            EspaceReservation rf = new EspaceReservation();
            rf.show();
        });
        
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
    WebService ws = new WebService();
    ReservationsService ds = new ReservationsService();
    Map x = ws.getResponse("li");
    ArrayList<Garderie> listevents = ds.getListGarderie(x);
    for (Garderie e : listevents) {
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            Slider rate = createStarRankSlider();
            Label a = new Label("Nom : "+e.getNom());
            Label b = new Label("Note : " +e.getNote());
            Container CR = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Button bou = new Button("Donner une note");
            CR.add(FlowLayout.encloseCenter(rate));
            CR.add(FlowLayout.encloseCenter(bou));
            photos.add(a);
            photos.add(b);
            photos.add(CR);
            try {
                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                photos.add(sep);
            } catch (IOException ex) {
            }
            
        
        
            add(photos);
            bou.addActionListener(ev->{
                int avis = rate.getProgress() / 2;
                ws.addRate(e.getId(), avis);
                System.out.println("hello");
            });
            
        }
        

    
    }
    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }
    
     private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(10);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }
    
}
