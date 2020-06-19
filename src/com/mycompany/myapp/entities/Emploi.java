/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Hamza
 */
public class Emploi {
    	 private int id; 
         private int user ;
	 private String extension ;
         private String n_emploit ;
	 private String emploit ;
	 private int classe ;

    public Emploi() {
    }

    public Emploi(int id, int user, String extension, String n_emploit, String emploit, int classe) {
        this.id = id;
        this.user = user;
        this.extension = extension;
        this.n_emploit = n_emploit;
        this.emploit = emploit;
        this.classe = classe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getN_emploit() {
        return n_emploit;
    }

    public void setN_emploit(String n_emploit) {
        this.n_emploit = n_emploit;
    }

    public String getEmploit() {
        return emploit;
    }

    public void setEmploit(String emploit) {
        this.emploit = emploit;
    }

    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }

    @Override
    public String toString() {
        return "Emploi{" + "id=" + id + ", user=" + user + ", extension=" + extension + ", n_emploit=" + n_emploit + ", emploit=" + emploit + ", classe=" + classe + '}';
    }
         
         
         
}
