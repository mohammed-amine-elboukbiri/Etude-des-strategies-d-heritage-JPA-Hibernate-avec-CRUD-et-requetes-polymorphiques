package com.example.model.tableperclass;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être positif")
    private Double prix;

    private String description;

    @NotNull(message = "La date de création est obligatoire")
    private LocalDateTime dateCreation;

    public Produit() {
        this.dateCreation = LocalDateTime.now();
    }

    public Produit(String nom, Double prix, String description) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.dateCreation = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {

        return "Les infos du produit\n" +
                "Id : " + id + "\n" +
                "Nom :" + nom + "\n" +
                "Prix : " + prix + "\n" +
                "Desciption : " + description + "\n" +
                "Date de creation :" + dateCreation ;

    }
}