package Classe;

import java.util.List;

public class Utilisateur extends Personne {
    // Attributs spécifiques à l'utilisateur
    private double budgetTotal; // Budget défini par l'utilisateur
    private List<Dépense> dépenses; // Liste des dépenses associées à l'utilisateur

    public Utilisateur(String nom, String prenom, String email, String motDePasse, double budgetTotal, List<Dépense> dépenses) {
        super(nom, prenom, email, motDePasse); // Appel du constructeur de Personne
        this.budgetTotal = budgetTotal; // Initialisation du budget
        this.dépenses = dépenses; // Initialisation de la liste des dépenses
    }

    // Getter et setter pour le budgetTotal
    public double getBudgetTotal() {
        return budgetTotal;
    }

    public void setBudgetTotal(double budgetTotal) {
        if (budgetTotal < 0) {
            System.out.println("Le budget ne peut pas être négatif.");
        } else {
            this.budgetTotal = budgetTotal;
        }
    }

    // Getter et setter pour la liste des dépenses
    public List<Dépense> getDépenses() {
        return dépenses;
    }

    public void setDépenses(List<Dépense> dépenses) {
        if (dépenses == null || dépenses.isEmpty()) {
            System.out.println("La liste des dépenses ne peut pas être vide.");
        } else {
            this.dépenses = dépenses;
        }
    }

    // Méthode pour afficher l'utilisateur et son budget
    public void afficherUtilisateur() {
        afficherPersonne(); // Appel de la méthode héritée de Personne
        System.out.println("Budget total: " + budgetTotal);
        if (dépenses == null || dépenses.isEmpty()) {
            System.out.println("Aucune dépense enregistrée.");
        } else {
            System.out.println("Dépenses: ");
            for (Dépense dépense : dépenses) {
                dépense.afficherDépense(); // Afficher les dépenses associées
            }
        }
    }
}
