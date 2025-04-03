package Classe;

public class Categorie {
    private int id;
    private String nom;

    // Constructeur
    public Categorie(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    // Getter et Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Méthode pour afficher la catégorie
    public void afficherCategorie() {
        System.out.println("ID: " + id);
        System.out.println("Nom de la catégorie: " + nom);
    }
}
