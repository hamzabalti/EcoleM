/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author acer
 */
public class Avis {
    int id;
    int apprenant;
    String avis;
    int enseignant;

    public Avis(int apprenant, String avis, int enseignant) {
        this.apprenant = apprenant;
        this.avis = avis;
        this.enseignant = enseignant;
    }

    
    
    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", apprenant=" + apprenant + ", avis=" + avis + ", enseignant=" + enseignant + '}';
    }

    public Avis() {
    }

    public Avis(int id, int apprenant, String avis, int enseignant) {
        this.id = id;
        this.apprenant = apprenant;
        this.avis = avis;
        this.enseignant = enseignant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApprenant() {
        return apprenant;
    }

    public void setApprenant(int apprenant) {
        this.apprenant = apprenant;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public int getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(int enseignant) {
        this.enseignant = enseignant;
    }
    
    
    
    
    
}
