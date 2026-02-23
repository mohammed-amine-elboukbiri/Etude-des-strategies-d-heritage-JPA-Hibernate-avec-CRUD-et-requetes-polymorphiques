package com.example;

import com.example.model.joined.Developpeur;
import com.example.model.joined.Employe;
import com.example.model.joined.Manager;
import com.example.model.singletable.Moto;
import com.example.model.singletable.Vehicule;
import com.example.model.singletable.Voiture;
import com.example.model.tableperclass.Electronique;
import com.example.model.tableperclass.Livre;
import com.example.model.tableperclass.Produit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class App {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-inheritance");

        try {
            System.out.println("\n=== Test de la stratégie SINGLE_TABLE ===");
            testSingleTable(emf);

            System.out.println("\n=== Test de la stratégie JOINED ===");
            testJoined(emf);

            System.out.println("\n=== Test de la stratégie TABLE_PER_CLASS ===");
            testTablePerClass(emf);

        } finally {
            emf.close();
        }
    }

    private static void testSingleTable(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            System.out.println("Création des véhicules...");

            Voiture voiture1 = new Voiture(
                    "Renault", "Clio", LocalDate.of(2021, 6, 21), 16000.0,
                    4, true, "Essence"
            );

            Voiture voiture2 = new Voiture(
                    "Peugeot", "208", LocalDate.of(2020, 4, 14), 19000.0,
                    5, true, "Diesel"
            );

            Moto moto1 = new Moto(
                    "kawasaki", "Ninja H2", LocalDate.of(2023, 8, 19), 20000.0,
                    998, "Manuelle"
            );

            em.persist(voiture1);
            em.persist(voiture2);
            em.persist(moto1);

            em.getTransaction().commit();
            System.out.println("Véhicules créés avec succès !");

            // Requêtes polymorphiques
            em.clear();

            System.out.println("\nRécupération de tous les véhicules :");
            List<Vehicule> vehicules = em.createQuery("SELECT v FROM Vehicule v", Vehicule.class)
                    .getResultList();
            vehicules.forEach(System.out::println);

            System.out.println("\nRécupération de toutes les voitures :");
            List<Voiture> voitures = em.createQuery("SELECT v FROM Voiture v", Voiture.class)
                    .getResultList();
            voitures.forEach(System.out::println);

            System.out.println("\nRécupération de toutes les motos :");
            List<Moto> motos = em.createQuery("SELECT m FROM Moto m", Moto.class)
                    .getResultList();
            motos.forEach(System.out::println);

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private static void testJoined(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            System.out.println("Création des employés...");

            Developpeur dev1 = new Developpeur(
                    "Amine", "ELBOUKBIRI", "amine.ELBOUKBIRI@gmail.com", LocalDate.of(2024, 4, 12),
                    "Java", "Backend", 1
            );

            Developpeur dev2 = new Developpeur(
                    "othman", "ELMATLINI", "othman.ELMATLINI@gmail.com", LocalDate.of(2020, 3, 10),
                    "JavaScript", "Frontend", 3
            );

            Manager manager1 = new Manager(
                    "Ilyass", "SEKHSOUKHI", "ilyass.SEKHSOUKHI@gmail.com", LocalDate.of(2018, 7, 20),
                    "IT", 10, 5000.0
            );

            em.persist(dev1);
            em.persist(dev2);
            em.persist(manager1);

            em.getTransaction().commit();
            System.out.println("Employés créés avec succès !");

            em.clear();

            System.out.println("\nRécupération de tous les employés :");
            List<Employe> employes = em.createQuery("SELECT e FROM Employe e", Employe.class)
                    .getResultList();
            employes.forEach(System.out::println);

            System.out.println("\nRécupération de tous les développeurs :");
            List<Developpeur> devs = em.createQuery("SELECT d FROM Developpeur d", Developpeur.class)
                    .getResultList();
            devs.forEach(System.out::println);

            System.out.println("\nRécupération de tous les managers :");
            List<Manager> managers = em.createQuery("SELECT m FROM Manager m", Manager.class)
                    .getResultList();
            managers.forEach(System.out::println);

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private static void testTablePerClass(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            System.out.println("Création des produits...");

            Livre livre1 = new Livre(
                    "Le Seigneur des Anneaux", 30.19, "Un classique de fantasy",
                    "J.R.R. Tolkien", "978-2075134729", 1300, "Pocket"
            );

            Livre livre2 = new Livre(
                    "Harry Potter", 25.85, "Roman fantastique pour jeunes",
                    "J.K. Rowling", "978-2070643028", 900, "Gallimard"
            );

            Electronique elec1 = new Electronique(
                    "Iphone 15 pro max", 10000.0, "Smartphone haut de gamme",
                    "Iphone", "15 pro max", 24, "Écran 6.2\", 512, 8Go RAM"
            );

            em.persist(livre1);
            em.persist(livre2);
            em.persist(elec1);

            em.getTransaction().commit();
            System.out.println("Produits créés avec succès !");

            em.clear();

            System.out.println("\nRécupération de tous les produits :");
            List<Produit> produits = em.createQuery("SELECT p FROM Produit p", Produit.class)
                    .getResultList();
            produits.forEach(System.out::println);

            System.out.println("\nRécupération de tous les livres :");
            List<Livre> livres = em.createQuery("SELECT l FROM Livre l", Livre.class)
                    .getResultList();
            livres.forEach(System.out::println);

            System.out.println("\nRécupération de tous les produits électroniques :");
            List<Electronique> elecs = em.createQuery("SELECT e FROM Electronique e", Electronique.class)
                    .getResultList();
            elecs.forEach(System.out::println);

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}