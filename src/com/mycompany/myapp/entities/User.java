/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Jasser
 */
public class User {
    private int id ; 
    private String username ; 
    private String email ; 
    private String roles ; 
    private String prenom ;
    private String sexe ;
    private String niveau;
    private String date ;
    private String password;
    private String tel ; 
    private String cin ; 
    private int idparent;
    private String loisir;
    private int affecter;
    private int classe;
    private int m;

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }


    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }

    public String getLoisir() {
        return loisir;
    }

    public void setLoisir(String loisir) {
        this.loisir = loisir;
    }

    public int getAffecter() {
        return affecter;
    }

    public void setAffecter(int affecter) {
        this.affecter = affecter;
    }
           

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getIdparent() {
        return idparent;
    }

    public void setIdparent(int idparent) {
        this.idparent = idparent;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", roles=" + roles + ", prenom=" + prenom + ", sexe=" + sexe + ", niveau=" + niveau + ", date=" + date + ", password=" + password + ", tel=" + tel + ", cin=" + cin + ", idparent=" + idparent + ", loisir=" + loisir + ", affecter=" + affecter + ", classe=" + classe + ", m=" + m + '}';
    }

 
    
    
    
}
