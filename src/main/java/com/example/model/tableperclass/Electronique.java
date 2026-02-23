package com.example.model.tableperclass;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "electroniques")
public class Electronique extends Produit {

    @NotBlank(message = "La marque est obligatoire")
    private String marque;

    @NotBlank(message = "Le modèle est obligatoire")
    private String modele;

    @Positive(message = "La garantie doit être positive")
    private Integer garantieMois;

    private String caracteristiques;

    public Electronique() {
    }

    public Electronique(String nom, Double prix, String description, String marque,
                        String modele, Integer garantieMois, String caracteristiques) {
        super(nom, prix, description);
        this.marque = marque;
        this.modele = modele;
        this.garantieMois = garantieMois;
        this.caracteristiques = caracteristiques;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public Integer getGarantieMois() {
        return garantieMois;
    }

    public void setGarantieMois(Integer garantieMois) {
        this.garantieMois = garantieMois;
    }

    public String getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(String caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    @Override
    public String toString() {

        return "Les infos d'electronique\n" +
                "Id : " + getId() + "\n" +
                "Nom :" + getNom() + "\n" +
                "Prix : " + getPrix() + "\n" +
                "Desciption : " + getDescription() + "\n" +
                "Date de creation :" + getDateCreation() + "\n" +
                "Marque : " + marque + "\n" +
                "Model : " + modele + "\n" +
                "Garantie mois : " + garantieMois + "\n" +
                "Caracteristiques : " + caracteristiques ;
    }
}