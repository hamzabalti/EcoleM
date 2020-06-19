/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class cour {
	 private int id; 
         private String nom ;
	 private String extension ;
	 private String cour_pdf ;
	 private int matiere ; 
	 private int classe ;

    public cour(int id, String nom, String extension, String cour_pdf, int matiere, int classe) {
        this.id = id;
        this.nom = nom;
        this.extension = extension;
        this.cour_pdf = cour_pdf;
        this.matiere = matiere;
        this.classe = classe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

         
         
         
    public cour(int id, String cour_pdf, int matiere, int classe) {
        this.id = id;
        this.cour_pdf = cour_pdf;
        this.matiere = matiere;
        this.classe = classe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCour_pdf() {
        return cour_pdf;
    }

    public void setCour_pdf(String cour_pdf) {
        this.cour_pdf = cour_pdf;
    }

    public int getMatiere() {
        return matiere;
    }

    public void setMatiere(int matiere) {
        this.matiere = matiere;
    }

    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }
         
	

    public cour() {
    }

    @Override
    public String toString() {
        return "cour{" + "id=" + id + ", nom=" + nom + ", extension=" + extension + ", cour_pdf=" + cour_pdf + ", matiere=" + matiere + ", classe=" + classe + '}';
    }
        
	
}

