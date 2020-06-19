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
public class Favoris {
    int id;
    int apprenant;
    int emploit;

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

    public int getEmploit() {
        return emploit;
    }

    public void setEmploit(int emploit) {
        this.emploit = emploit;
    }

    public Favoris() {
    }

    @Override
    public String toString() {
        return "Favoris{" + "id=" + id + ", apprenant=" + apprenant + ", emploit=" + emploit + '}';
    }

    public Favoris(int id, int apprenant, int emploit) {
        this.id = id;
        this.apprenant = apprenant;
        this.emploit = emploit;
    }

    public Favoris(int apprenant, int emploit) {
        this.apprenant = apprenant;
        this.emploit = emploit;
    }
    
    
}
