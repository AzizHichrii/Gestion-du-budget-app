package Classe;

public class Dépense {
    private int id;
    private double montant;
    private Categorie categorie; // Référence à un objet Categorie
    private String date;
    private String méthodePaiement;
    private int userId; // Identifiant de l'utilisateur associé à cette dépense

    // Constructeur
    public Dépense(int id, double montant, Categorie categorie, String date, String méthodePaiement, int userId) {
        this.id = id;
        this.montant = montant;
        this.categorie = categorie; // Association avec une catégorie
        this.date = date;
        this.méthodePaiement = méthodePaiement;
        this.userId = userId;
    }

    // Getter et Setter pour les attributs
    public int getId() {
        return id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Categorie getCategorie() {
        return categorie; // Retourne l'objet Categorie
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie; // Associe un objet Categorie à la dépense
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMéthodePaiement() {
        return méthodePaiement;
    }

    public void setMéthodePaiement(String méthodePaiement) {
        this.méthodePaiement = méthodePaiement;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Méthode pour afficher les détails de la dépense
    public void afficherDépense() {
        System.out.println("ID: " + id);
        System.out.println("Montant: " + montant);
        System.out.println("Catégorie: " + categorie.getNom()); // Affiche le nom de la catégorie associée
        System.out.println("Date: " + date);
        System.out.println("Méthode de paiement: " + méthodePaiement);
    }
}
