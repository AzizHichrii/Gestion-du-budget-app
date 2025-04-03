package Classe;
import java.util.Date;

public class Facture {
    private int id;
    private double montantTotal;
    private Date dateEmission;
    private int userId; // Association avec un utilisateur

    // Constructeur
    public Facture(int id, double montantTotal, Date dateEmission, int userId) {
        this.id = id;
        this.montantTotal = montantTotal;
        this.dateEmission = dateEmission;
        this.userId = userId;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getMontantTotal() { return montantTotal; }
    public void setMontantTotal(double montantTotal) { this.montantTotal = montantTotal; }

    public Date getDateEmission() { return dateEmission; }
    public void setDateEmission(Date dateEmission) { this.dateEmission = dateEmission; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}
