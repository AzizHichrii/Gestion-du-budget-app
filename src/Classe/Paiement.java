package Classe;
import java.util.Date;

public class Paiement {
    private int id;
    private double montant;
    private Date datePaiement;
    private String methodePaiement; // Ex: Carte, Espèces, Virement
    private int factureId; // Lien avec Facture

    // Constructeur
    public Paiement(int id, double montant, Date datePaiement, String methodePaiement, int factureId) {
        this.id = id;
        this.montant = montant;
        this.datePaiement = datePaiement;
        this.methodePaiement = methodePaiement;
        this.factureId = factureId;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }

    public Date getDatePaiement() { return datePaiement; }
    public void setDatePaiement(Date datePaiement) { this.datePaiement = datePaiement; }

    public String getMethodePaiement() { return methodePaiement; }
    public void setMethodePaiement(String methodePaiement) { this.methodePaiement = methodePaiement; }

    public int getFactureId() { return factureId; }
    public void setFactureId(int factureId) { this.factureId = factureId; }

    // Affichage des infos du paiement
    public void afficherPaiement() {
        System.out.println("Paiement ID: " + id);
        System.out.println("Montant: " + montant);
        System.out.println("Date: " + datePaiement);
        System.out.println("Méthode: " + methodePaiement);
        System.out.println("Facture associée ID: " + factureId);
    }
}
