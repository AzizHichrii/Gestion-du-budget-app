import Classe.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import monprojet.dao.*;

public class Main {
    private static Connection connection;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // Établir la connexion à la base de données
            connection = Connexion.getConnection();
            System.out.println("✅ Connexion à la base de données établie.");

            // Menu principal
            boolean running = true;
            while (running) {
                System.out.println("\n=== MENU PRINCIPAL ===");
                System.out.println("1. Gérer les Personnes/Utilisateurs/Admins");
                System.out.println("2. Gérer les Catégories");
                System.out.println("3. Gérer les Dépenses");
                System.out.println("4. Gérer les Factures");
                System.out.println("5. Gérer les Paiements");
                System.out.println("6. Gérer les Rappels");
                System.out.println("0. Quitter");
                System.out.print("Choix : ");

                int choix = scanner.nextInt();
                scanner.nextLine(); // Pour consommer la nouvelle ligne

                switch (choix) {
                    case 1:
                        gererPersonnes();
                        break;
                    case 2:
                        gererCategories();
                        break;
                    case 3:
                        gererDepenses();
                        break;
                    case 4:
                        gererFactures();
                        break;
                    case 5:
                        gererPaiements();
                        break;
                    case 6:
                        gererRappels();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println(" Choix invalide.");
                }
            }

            // Fermer la connexion
            Connexion.closeConnection();
            System.out.println(" Connexion à la base de données fermée.");

        } catch (SQLException e) {
            System.err.println(" Erreur de connexion à la base de données :");
            e.printStackTrace();
        }
    }

    // Méthodes pour gérer chaque entité

    private static void gererPersonnes() {
        PersonneDAO personneDAO = new PersonneDAO(connection);
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO(connection);
        AdminDAO adminDAO = new AdminDAO(connection);

        boolean back = false;
        while (!back) {
            System.out.println("\n=== GESTION PERSONNES ===");
            System.out.println("1. Ajouter une Personne");
            System.out.println("2. Ajouter un Utilisateur");
            System.out.println("3. Ajouter un Admin");
            System.out.println("4. Lister toutes les Personnes");
            System.out.println("5. Lister tous les Utilisateurs");
            System.out.println("6. Lister tous les Admins");
            System.out.println("7. Trouver une Personne par ID");
            System.out.println("8. Trouver un Utilisateur par ID");
            System.out.println("9. Trouver un Admin par ID");
            System.out.println("10. Mettre à jour une Personne");
            System.out.println("11. Mettre à jour un Utilisateur");
            System.out.println("12. Supprimer une Personne");
            System.out.println("0. Retour");
            System.out.print("Choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    // Ajouter une Personne
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Email : ");
                    String email = scanner.nextLine();
                    System.out.print("Mot de passe : ");
                    String mdp = scanner.nextLine();
                    System.out.print("type : ");
                    String type = scanner.nextLine();

                    Personne personne = new Personne(nom, prenom, email, mdp,type) {};
                    personneDAO.addPersonne(personne);
                    System.out.println("✅ Personne ajoutée avec succès.");
                    break;

                case 2:
                    // Ajouter un Utilisateur
                    System.out.print("Nom : ");
                    nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    prenom = scanner.nextLine();
                    System.out.print("Email : ");
                    email = scanner.nextLine();
                    System.out.print("Mot de passe : ");
                    mdp = scanner.nextLine();
                    System.out.print("type : ");
                    type = scanner.nextLine();
                    System.out.print("Budget total : ");
                    double budget = scanner.nextDouble();
                    scanner.nextLine();

                    Utilisateur utilisateur = new Utilisateur(nom, prenom, email, mdp,type, budget, new ArrayList<>());
                    utilisateurDAO.addUtilisateur(utilisateur);
                    System.out.println("✅ Utilisateur ajouté avec succès.");
                    break;

                case 3:
                    // Ajouter un Admin
                    System.out.print("Nom : ");
                    nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    prenom = scanner.nextLine();
                    System.out.print("Email : ");
                    email = scanner.nextLine();
                    System.out.print("Mot de passe : ");
                    mdp = scanner.nextLine();
                    System.out.print("type : ");
                    type = scanner.nextLine();

                    Admin admin = new Admin(nom, prenom, email, mdp,type, new ArrayList<>());
                    adminDAO.addAdmin(admin);
                    System.out.println("✅ Admin ajouté avec succès.");
                    break;

                case 4:
                    // Lister toutes les Personnes
                    List<Personne> personnes = personneDAO.getAllPersonnes();
                    System.out.println("\n=== LISTE DES PERSONNES ===");
                    personnes.forEach(p -> System.out.println(p.getNom() + " " + p.getPrenom() + " - " + p.getEmail() + " - " + p.getType()));
                    break;

                case 5:
                    // Lister tous les Utilisateurs
                    List<Utilisateur> utilisateurs = utilisateurDAO.getAllUtilisateurs();
                    System.out.println("\n=== LISTE DES UTILISATEURS ===");
                    utilisateurs.forEach(u -> System.out.println(u.getNom() + " " + u.getPrenom() + " - Budget: " + u.getBudgetTotal()));
                    break;

                case 6:
                    // Lister tous les Admins
                    List<Admin> admins = adminDAO.getAllAdmins();
                    System.out.println("\n=== LISTE DES ADMINS ===");
                    admins.forEach(a -> System.out.println(a.getNom() + " " + a.getPrenom() + " - " + a.getEmail()));
                    break;

                case 7:
                    // Trouver une Personne par ID
                    System.out.print("ID de la personne : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Personne p = personneDAO.getPersonneById(id);
                    if (p != null) {
                        System.out.println("Personne trouvée : " + p.getNom() + " " + p.getPrenom());
                    } else {
                        System.out.println(" Personne non trouvée.");
                    }
                    break;

                case 8:
                    // Trouver un Utilisateur par ID
                    System.out.print("ID de l'utilisateur : ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    Utilisateur u = utilisateurDAO.getUtilisateurById(id);
                    if (u != null) {
                        System.out.println("Utilisateur trouvé : " + u.getNom() + " " + u.getPrenom() + " - Budget: " + u.getBudgetTotal());
                    } else {
                        System.out.println(" Utilisateur non trouvé.");
                    }
                    break;

                case 9:
                    // Trouver un Admin par ID
                    System.out.print("ID de l'admin : ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    Admin a = adminDAO.getAdminById(id);
                    if (a != null) {
                        System.out.println("Admin trouvé : " + a.getNom() + " " + a.getPrenom());
                    } else {
                        System.out.println(" Admin non trouvé.");
                    }
                    break;

                case 10:
                    // Mettre à jour une Personne
                    System.out.print("ID de la personne à mettre à jour : ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    Personne persToUpdate = personneDAO.getPersonneById(id);
                    if (persToUpdate != null) {
                        System.out.print("Nouveau nom (" + persToUpdate.getNom() + ") : ");
                        nom = scanner.nextLine();
                        System.out.print("Nouveau prénom (" + persToUpdate.getPrenom() + ") : ");
                        prenom = scanner.nextLine();
                        System.out.print("Nouvel email (" + persToUpdate.getEmail() + ") : ");
                        email = scanner.nextLine();
                        System.out.print("Nouveau mot de passe : ");
                        mdp = scanner.nextLine();
                        System.out.print("Modifier le type : ");
                        type = scanner.nextLine();

                        persToUpdate.setNom(nom.isEmpty() ? persToUpdate.getNom() : nom);
                        persToUpdate.setPrenom(prenom.isEmpty() ? persToUpdate.getPrenom() : prenom);
                        persToUpdate.setEmail(email.isEmpty() ? persToUpdate.getEmail() : email);
                        persToUpdate.setMotDePasse(mdp.isEmpty() ? persToUpdate.getMotDePasse() : mdp);
                        persToUpdate.setType(type.isEmpty() ? persToUpdate.getType() : type);


                        personneDAO.updatePersonne(persToUpdate);
                        System.out.println(" Personne mise à jour avec succès.");
                    } else {
                        System.out.println(" Personne non trouvée.");
                    }
                    break;

                case 11:
                    // Mettre à jour un Utilisateur
                    System.out.print("ID de l'utilisateur à mettre à jour : ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    Utilisateur userToUpdate = utilisateurDAO.getUtilisateurById(id);
                    if (userToUpdate != null) {
                        System.out.print("Nouveau nom (" + userToUpdate.getNom() + ") : ");
                        nom = scanner.nextLine();
                        System.out.print("Nouveau prénom (" + userToUpdate.getPrenom() + ") : ");
                        prenom = scanner.nextLine();
                        System.out.print("Nouvel email (" + userToUpdate.getEmail() + ") : ");
                        email = scanner.nextLine();
                        System.out.print("Nouveau mot de passe : ");
                        mdp = scanner.nextLine();
                        System.out.print("Nouveau budget (" + userToUpdate.getBudgetTotal() + ") : ");
                        String budgetStr = scanner.nextLine();

                        userToUpdate.setNom(nom.isEmpty() ? userToUpdate.getNom() : nom);
                        userToUpdate.setPrenom(prenom.isEmpty() ? userToUpdate.getPrenom() : prenom);
                        userToUpdate.setEmail(email.isEmpty() ? userToUpdate.getEmail() : email);
                        userToUpdate.setMotDePasse(mdp.isEmpty() ? userToUpdate.getMotDePasse() : mdp);
                        if (!budgetStr.isEmpty()) {
                            userToUpdate.setBudgetTotal(Double.parseDouble(budgetStr));
                        }

                        utilisateurDAO.updateUtilisateur(userToUpdate);
                        System.out.println(" Utilisateur mis à jour avec succès.");
                    } else {
                        System.out.println(" Utilisateur non trouvé.");
                    }
                    break;

                case 12:
                    // Supprimer une Personne
                    System.out.print("ID de la personne à supprimer : ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    personneDAO.deletePersonne(id);
                    System.out.println(" Personne supprimée avec succès.");
                    break;

                case 0:
                    back = true;
                    break;

                default:
                    System.out.println(" Choix invalide.");
            }
        }
    }

    private static void gererCategories() {
        CategorieDAO categorieDAO = new CategorieDAO(connection);

        boolean back = false;
        while (!back) {
            System.out.println("\n=== GESTION CATÉGORIES ===");
            System.out.println("1. Ajouter une catégorie");
            System.out.println("2. Lister toutes les catégories");
            System.out.println("3. Trouver une catégorie par ID");
            System.out.println("4. Mettre à jour une catégorie");
            System.out.println("5. Supprimer une catégorie");
            System.out.println("0. Retour");
            System.out.print("Choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    // Ajouter une catégorie
                    System.out.print("Nom de la catégorie : ");
                    String nom = scanner.nextLine();
                    Categorie categorie = new Categorie(0, nom);
                    categorieDAO.addCategorie(categorie);
                    System.out.println(" Catégorie ajoutée avec succès.");
                    break;

                case 2:
                    // Lister toutes les catégories
                    List<Categorie> categories = categorieDAO.getAllCategories();
                    System.out.println("\n=== LISTE DES CATÉGORIES ===");
                    categories.forEach(c -> System.out.println(c.getId() + " - " + c.getNom()));
                    break;

                case 3:
                    // Trouver une catégorie par ID
                    System.out.print("ID de la catégorie : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Categorie c = categorieDAO.getCategorieById(id);
                    if (c != null) {
                        System.out.println("Catégorie trouvée : " + c.getNom());
                    } else {
                        System.out.println(" Catégorie non trouvée.");
                    }
                    break;

                case 4:
                    // Mettre à jour une catégorie
                    System.out.print("ID de la catégorie à mettre à jour : ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    Categorie catToUpdate = categorieDAO.getCategorieById(id);
                    if (catToUpdate != null) {
                        System.out.print("Nouveau nom (" + catToUpdate.getNom() + ") : ");
                        nom = scanner.nextLine();
                        catToUpdate.setNom(nom);
                        categorieDAO.updateCategorie(catToUpdate);
                        System.out.println(" Catégorie mise à jour avec succès.");
                    } else {
                        System.out.println(" Catégorie non trouvée.");
                    }
                    break;

                case 5:
                    // Supprimer une catégorie
                    System.out.print("ID de la catégorie à supprimer : ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    categorieDAO.deleteCategorie(id);
                    System.out.println(" Catégorie supprimée avec succès.");
                    break;

                case 0:
                    back = true;
                    break;

                default:
                    System.out.println(" Choix invalide.");
            }
        }
    }

    private static void gererDepenses() {
        DépenseDAO depenseDAO = new DépenseDAO(connection);
        CategorieDAO categorieDAO = new CategorieDAO(connection);
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO(connection);

        boolean back = false;
        while (!back) {
            System.out.println("\n=== GESTION DÉPENSES ===");
            System.out.println("1. Ajouter une dépense");
            System.out.println("2. Lister toutes les dépenses d'un utilisateur");
            System.out.println("3. Trouver une dépense par ID");
            System.out.println("4. Mettre à jour une dépense");
            System.out.println("5. Supprimer une dépense");
            System.out.println("0. Retour");
            System.out.print("Choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    // Ajouter une dépense
                    System.out.print("ID de l'utilisateur : ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();

                    // Vérifier que l'utilisateur existe
                    Utilisateur user = utilisateurDAO.getUtilisateurById(userId);
                    if (user == null) {
                        System.out.println(" Utilisateur non trouvé.");
                        break;
                    }

                    System.out.print("Montant : ");
                    double montant = scanner.nextDouble();
                    scanner.nextLine();

                    // Lister les catégories disponibles
                    System.out.println("\nCatégories disponibles :");
                    List<Categorie> categories = categorieDAO.getAllCategories();
                    categories.forEach(c -> System.out.println(c.getId() + " - " + c.getNom()));
                    System.out.print("ID de la catégorie : ");
                    int categorieId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Date (YYYY-MM-DD) : ");
                    String dateStr = scanner.nextLine();

                    System.out.print("Méthode de paiement : ");
                    String methodePaiement = scanner.nextLine();

                    Categorie categorie = categorieDAO.getCategorieById(categorieId);
                    if (categorie == null) {
                        System.out.println(" Catégorie non trouvée.");
                        break;
                    }

                    Dépense depense = new Dépense(0, montant, categorie, dateStr, methodePaiement, userId);
                    depenseDAO.addDépense(depense);
                    System.out.println(" Dépense ajoutée avec succès.");
                    break;

                case 2:
                    // Lister toutes les dépenses d'un utilisateur
                    System.out.print("ID de l'utilisateur : ");
                    userId = scanner.nextInt();
                    scanner.nextLine();

                    List<Dépense> depenses = depenseDAO.getAllDépensesByUserId(userId);
                    System.out.println("\n=== LISTE DES DÉPENSES ===");
                    depenses.forEach(d -> System.out.println(
                            "ID: " + d.getId() +
                                    " - Montant: " + d.getMontant() +
                                    " - Catégorie: " + d.getCategorie().getNom() +
                                    " - Date: " + d.getDate() +
                                    " - Paiement: " + d.getMéthodePaiement()
                    ));
                    break;

                case 3:
                    // Trouver une dépense par ID
                    System.out.print("ID de la dépense : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Dépense d = depenseDAO.getDépenseById(id);
                    if (d != null) {
                        System.out.println(
                                "Dépense trouvée : " +
                                        "Montant: " + d.getMontant() +
                                        " - Catégorie: " + d.getCategorie().getNom() +
                                        " - Date: " + d.getDate()
                        );
                    } else {
                        System.out.println(" Dépense non trouvée.");
                    }
                    break;

                case 4:
                    // Mettre à jour une dépense
                    System.out.print("ID de la dépense à mettre à jour : ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    Dépense depToUpdate = depenseDAO.getDépenseById(id);
                    if (depToUpdate != null) {
                        System.out.print("Nouveau montant (" + depToUpdate.getMontant() + ") : ");
                        String montantStr = scanner.nextLine();
                        if (!montantStr.isEmpty()) {
                            depToUpdate.setMontant(Double.parseDouble(montantStr));
                        }

                        // Catégorie
                        System.out.println("\nCatégories disponibles :");
                        categories = categorieDAO.getAllCategories();
                        categories.forEach(c -> System.out.println(c.getId() + " - " + c.getNom()));
                        System.out.print("Nouvel ID de catégorie (" + depToUpdate.getCategorie().getId() + ") : ");
                        String catIdStr = scanner.nextLine();
                        if (!catIdStr.isEmpty()) {
                            Categorie newCat = categorieDAO.getCategorieById(Integer.parseInt(catIdStr));
                            if (newCat != null) {
                                depToUpdate.setCategorie(newCat);
                            }
                        }

                        System.out.print("Nouvelle date (" + depToUpdate.getDate() + ") : ");
                        String newDate = scanner.nextLine();
                        if (!newDate.isEmpty()) {
                            depToUpdate.setDate(newDate);
                        }

                        System.out.print("Nouvelle méthode de paiement (" + depToUpdate.getMéthodePaiement() + ") : ");
                        String newMethode = scanner.nextLine();
                        if (!newMethode.isEmpty()) {
                            depToUpdate.setMéthodePaiement(newMethode);
                        }

                        depenseDAO.updateDépense(depToUpdate);
                        System.out.println(" Dépense mise à jour avec succès.");
                    } else {
                        System.out.println(" Dépense non trouvée.");
                    }
                    break;

                case 5:
                    // Supprimer une dépense
                    System.out.print("ID de la dépense à supprimer : ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    depenseDAO.deleteDépense(id);
                    System.out.println(" Dépense supprimée avec succès.");
                    break;

                case 0:
                    back = true;
                    break;

                default:
                    System.out.println(" Choix invalide.");
            }
        }
    }

    private static void gererFactures() {
        FactureDAO factureDAO = new FactureDAO(connection);
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO(connection);

        boolean back = false;
        while (!back) {
            System.out.println("\n=== GESTION FACTURES ===");
            System.out.println("1. Ajouter une facture");
            System.out.println("2. Lister toutes les factures d'un utilisateur");
            System.out.println("3. Trouver une facture par ID");
            System.out.println("4. Supprimer une facture");
            System.out.println("0. Retour");
            System.out.print("Choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    // Ajouter une facture
                    System.out.print("ID de l'utilisateur : ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();

                    // Vérifier que l'utilisateur existe
                    Utilisateur user = utilisateurDAO.getUtilisateurById(userId);
                    if (user == null) {
                        System.out.println( " Utilisateur non trouvé.");
                        break;
                    }

                    System.out.print("Montant total : ");
                    double montant = scanner.nextDouble();
                    scanner.nextLine();

                    Facture facture = new Facture(0, montant, new Date(), userId);
                    factureDAO.addFacture(facture);
                    System.out.println(" Facture ajoutée avec succès. ID: " + facture.getId());
                    break;

                case 2:
                    // Lister toutes les factures d'un utilisateur
                    System.out.print("ID de l'utilisateur : ");
                    userId = scanner.nextInt();
                    scanner.nextLine();

                    List<Facture> factures = factureDAO.getAllFacturesByUserId(userId);
                    System.out.println("\n=== LISTE DES FACTURES ===");
                    factures.forEach(f -> System.out.println(
                            "ID: " + f.getId() +
                                    " - Montant: " + f.getMontantTotal() +
                                    " - Date: " + f.getDateEmission()
                    ));
                    break;

                case 3:
                    // Trouver une facture par ID
                    System.out.print("ID de la facture : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Facture f = factureDAO.getFactureById(id);
                    if (f != null) {
                        System.out.println(
                                "Facture trouvée : " +
                                        "Montant: " + f.getMontantTotal() +
                                        " - Date: " + f.getDateEmission()
                        );
                    } else {
                        System.out.println(" Facture non trouvée.");
                    }
                    break;

                case 4:
                    // Supprimer une facture
                    System.out.print("ID de la facture à supprimer : ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    factureDAO.deleteFacture(id);
                    System.out.println(" Facture supprimée avec succès.");
                    break;

                case 0:
                    back = true;
                    break;

                default:
                    System.out.println(" Choix invalide.");
            }
        }
    }

    private static void gererPaiements() {
        PaiementDAO paiementDAO = new PaiementDAO(connection);
        FactureDAO factureDAO = new FactureDAO(connection);

        boolean back = false;
        while (!back) {
            System.out.println("\n=== GESTION PAIEMENTS ===");
            System.out.println("1. Ajouter un paiement");
            System.out.println("2. Lister tous les paiements");
            System.out.println("3. Trouver un paiement par ID");
            System.out.println("4. Mettre à jour un paiement");
            System.out.println("5. Supprimer un paiement");
            System.out.println("0. Retour");
            System.out.print("Choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    // Ajouter un paiement
                    System.out.print("ID de la facture : ");
                    int factureId = scanner.nextInt();
                    scanner.nextLine();

                    // Vérifier que la facture existe
                    Facture facture = factureDAO.getFactureById(factureId);
                    if (facture == null) {
                        System.out.println(" Facture non trouvée.");
                        break;
                    }

                    System.out.print("Montant : ");
                    double montant = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Méthode de paiement : ");
                    String methode = scanner.nextLine();

                    Paiement paiement = new Paiement(0, montant, new Date(), methode, factureId);
                    paiementDAO.addPaiement(paiement);
                    System.out.println(" Paiement ajouté avec succès.");
                    break;

                case 2:
                    // Lister tous les paiements
                    List<Paiement> paiements = paiementDAO.getAllPaiements();
                    System.out.println("\n=== LISTE DES PAIEMENTS ===");
                    paiements.forEach(p -> System.out.println(
                            "ID: " + p.getId() +
                                    " - Montant: " + p.getMontant() +
                                    " - Date: " + p.getDatePaiement() +
                                    " - Méthode: " + p.getMethodePaiement() +
                                    " - Facture ID: " + p.getFactureId()
                    ));
                    break;

                case 3:
                    // Trouver un paiement par ID
                    System.out.print("ID du paiement : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Paiement p = paiementDAO.getPaiementById(id);
                    if (p != null) {
                        System.out.println(
                                "Paiement trouvé : " +
                                        "Montant: " + p.getMontant() +
                                        " - Date: " + p.getDatePaiement() +
                                        " - Méthode: " + p.getMethodePaiement()
                        );
                    } else {
                        System.out.println(" Paiement non trouvé.");
                    }
                    break;

                case 4:
                    // Mettre à jour un paiement
                    System.out.print("ID du paiement à mettre à jour : ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    Paiement paiementToUpdate = paiementDAO.getPaiementById(id);
                    if (paiementToUpdate != null) {
                        System.out.print("Nouveau montant (" + paiementToUpdate.getMontant() + ") : ");
                        String montantStr = scanner.nextLine();
                        if (!montantStr.isEmpty()) {
                            paiementToUpdate.setMontant(Double.parseDouble(montantStr));
                        }

                        System.out.print("Nouvelle méthode de paiement (" + paiementToUpdate.getMethodePaiement() + ") : ");
                        String newMethode = scanner.nextLine();
                        if (!newMethode.isEmpty()) {
                            paiementToUpdate.setMethodePaiement(newMethode);
                        }

                        System.out.print("Nouvel ID de facture (" + paiementToUpdate.getFactureId() + ") : ");
                        String factIdStr = scanner.nextLine();
                        if (!factIdStr.isEmpty()) {
                            paiementToUpdate.setFactureId(Integer.parseInt(factIdStr));
                        }

                        paiementDAO.updatePaiement(paiementToUpdate);
                        System.out.println(" Paiement mis à jour avec succès.");
                    } else {
                        System.out.println(" Paiement non trouvé.");
                    }
                    break;

                case 5:
                    // Supprimer un paiement
                    System.out.print("ID du paiement à supprimer : ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    paiementDAO.deletePaiement(id);
                    System.out.println(" Paiement supprimé avec succès.");
                    break;

                case 0:
                    back = true;
                    break;

                default:
                    System.out.println(" Choix invalide.");
            }
        }
    }

    private static void gererRappels() {
        RappelDAO rappelDAO = new RappelDAO(connection);
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO(connection);

        boolean back = false;
        while (!back) {
            System.out.println("\n=== GESTION RAPPELS ===");
            System.out.println("1. Ajouter un rappel");
            System.out.println("2. Lister tous les rappels d'un utilisateur");
            System.out.println("3. Trouver un rappel par ID");
            System.out.println("4. Mettre à jour un rappel");
            System.out.println("5. Supprimer un rappel");
            System.out.println("0. Retour");
            System.out.print("Choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    // Ajouter un rappel
                    System.out.print("ID de l'utilisateur : ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();

                    // Vérifier que l'utilisateur existe
                    Utilisateur user = utilisateurDAO.getUtilisateurById(userId);
                    if (user == null) {
                        System.out.println(" Utilisateur non trouvé.");
                        break;
                    }

                    System.out.print("Montant : ");
                    double montant = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Description : ");
                    String description = scanner.nextLine();

                    Rappel rappel = new Rappel(0, new Date(), montant, description, userId);
                    rappelDAO.addRappel(rappel);
                    System.out.println(" Rappel ajouté avec succès.");
                    break;

                case 2:
                    // Lister tous les rappels d'un utilisateur
                    System.out.print("ID de l'utilisateur : ");
                    userId = scanner.nextInt();
                    scanner.nextLine();

                    List<Rappel> rappels = rappelDAO.getAllRappelsByUserId(userId);
                    System.out.println("\n=== LISTE DES RAPPELS ===");
                    rappels.forEach(r -> System.out.println(
                            "ID: " + r.getId() +
                                    " - Date: " + r.getDate() +
                                    " - Montant: " + r.getMontant() +
                                    " - Description: " + r.getDescription()
                    ));
                    break;

                case 3:
                    // Trouver un rappel par ID
                    System.out.print("ID du rappel : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Rappel r = rappelDAO.getRappelById(id);
                    if (r != null) {
                        System.out.println(
                                "Rappel trouvé : " +
                                        "Date: " + r.getDate() +
                                        " - Montant: " + r.getMontant() +
                                        " - Description: " + r.getDescription()
                        );
                    } else {
                        System.out.println(" Rappel non trouvé.");
                    }
                    break;

                case 4:
                    // Mettre à jour un rappel
                    System.out.print("ID du rappel à mettre à jour : ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    Rappel rappelToUpdate = rappelDAO.getRappelById(id);
                    if (rappelToUpdate != null) {
                        System.out.print("Nouveau montant (" + rappelToUpdate.getMontant() + ") : ");
                        String montantStr = scanner.nextLine();
                        if (!montantStr.isEmpty()) {
                            rappelToUpdate.setMontant(Double.parseDouble(montantStr));
                        }

                        System.out.print("Nouvelle description (" + rappelToUpdate.getDescription() + ") : ");
                        String newDesc = scanner.nextLine();
                        if (!newDesc.isEmpty()) {
                            rappelToUpdate.setDescription(newDesc);
                        }

                        rappelDAO.updateRappel(rappelToUpdate);
                        System.out.println(" Rappel mis à jour avec succès.");
                    } else {
                        System.out.println(" Rappel non trouvé.");
                    }
                    break;

                case 5:
                    // Supprimer un rappel
                    System.out.print("ID du rappel à supprimer : ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    rappelDAO.deleteRappel(id);
                    System.out.println(" Rappel supprimé avec succès.");
                    break;

                case 0:
                    back = true;
                    break;

                default:
                    System.out.println(" Choix invalide.");
            }
        }
    }
}