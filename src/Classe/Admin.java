package Classe;

import java.util.List;

public class Admin extends Personne {
    // Attributs spécifiques à l'Classe.Admin
    private List<Utilisateur> utilisateurs; // Liste des utilisateurs que l'Classe.Admin gère

    public Admin(String nom, String prenom, String email, String motDePasse, List<Utilisateur> utilisateurs) {
        super(nom, prenom, email, motDePasse); // Appel du constructeur de la classe Classe.Personne
        this.utilisateurs = utilisateurs;
    }

    // Getter et setter pour la liste des utilisateurs
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    // Méthode pour afficher les utilisateurs gérés par l'Classe.Admin
    public void afficherUtilisateurs() {
        System.out.println("Utilisateurs sous gestion : ");
        for (Utilisateur utilisateur : utilisateurs) {
            utilisateur.afficherUtilisateur();
        }
    }

    // Méthode pour ajouter un utilisateur
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurs.add(utilisateur);
    }

    // Méthode pour supprimer un utilisateur
    public void supprimerUtilisateur(Utilisateur utilisateur) {
        utilisateurs.remove(utilisateur);
    }

    // Méthode pour générer un rapport des dépenses de tous les utilisateurs
    public void générerRapportDépenses() {
        System.out.println("Rapport des dépenses : ");
        for (Utilisateur utilisateur : utilisateurs) {
            System.out.println("Dépenses de l'utilisateur " + utilisateur.getNom() + " : ");
            for (Dépense dépense : utilisateur.getDépenses()) {
                System.out.println("Classe.Dépense : " + dépense.getMontant() + ", Catégorie : " + dépense.getCategorie());
            }
        }
    }
}
