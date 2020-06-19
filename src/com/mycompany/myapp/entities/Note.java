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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Note {
 
    private int id;
     private int u;
      private int m;

    private double note_orale;
    private double note_ecrit;
    private double moyenne;

    public Note(int u, int m, double note_orale, double note_ecrit, double moyenne) {
        this.u = u;
        this.m = m;
        this.note_orale = note_orale;
        this.note_ecrit = note_ecrit;
        this.moyenne = moyenne;
    }

    public Note(int id, double note_orale, double note_ecrit, double moyenne) {
        this.id = id;
        this.note_orale = note_orale;
        this.note_ecrit = note_ecrit;
        this.moyenne = moyenne;
    }
    public Note(double note_orale, double note_ecrit, double moyenne) {
        this.note_orale = note_orale;
        this.note_ecrit = note_ecrit;
        this.moyenne = moyenne;
    }
    public Note(){}

    public Note(int id, int m, double note_orale, double note_ecrit, double moyenne, int u) {
        this.id = id;
        this.m = m;
        this.note_orale = note_orale;
        this.note_ecrit = note_ecrit;
        this.moyenne = moyenne;
        this.u = u;
    }

    public Note(int m, double note_orale, double note_ecrit, double moyenne, int u) {
        this.m = m;
        this.note_orale = note_orale;
        this.note_ecrit = note_ecrit;
        this.moyenne = moyenne;
        this.u = u;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public double getNote_orale() {
        return note_orale;
    }

    public void setNote_orale(double note_orale) {
        this.note_orale = note_orale;
    }

    public double getNote_ecrit() {
        return note_ecrit;
    }

    public void setNote_ecrit(double note_ecrit) {
        this.note_ecrit = note_ecrit;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    public int getU() {
        return u;
    }

    public void setU(int u) {
        this.u = u;
    }

    @Override
    public String toString() {
        return "Note{" + "id=" + id + ", m=" + m + ", note_orale=" + note_orale + ", note_ecrit=" + note_ecrit + ", moyenne=" + moyenne + ", u=" + u + '}';
    }
}



    


    
    


