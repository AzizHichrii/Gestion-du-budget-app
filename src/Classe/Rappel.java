package Classe;

import java.util.Date;

public class Rappel {
    private int id;
    private Date date;
    private double montant;
    private String description;
    private int userId; // Associe le rappel à un utilisateur

    // Constructeur
    public Rappel(int id, Date date, double montant, String description, int userId) {
        this.id = id;
        this.date = date;
        this.montant = montant;
        this.description = description;
        this.userId = userId;
    }

    // Getter et Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Méthode pour afficher les informations du rappel
    public void afficherRappel() {
        System.out.println("ID: " + id);
        System.out.println("Date: " + date);
        System.out.println("Montant: " + montant);
        System.out.println("Description: " + description);
        System.out.println("Classe.Utilisateur ID: " + userId);
    }
}
