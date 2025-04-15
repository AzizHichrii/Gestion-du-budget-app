package monprojet.dao;

import Classe.Admin;
import Classe.Utilisateur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    private Connection connection;

    // Constructeur qui initialise la connexion à la base de données
    public AdminDAO(Connection connection) {
        this.connection = connection;
    }

    // Ajouter un administrateur
    public void addAdmin(Admin admin) {
        String query = "INSERT INTO personne (nom, prenom, email, motDePasse, type) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, admin.getNom());
            statement.setString(2, admin.getPrenom());
            statement.setString(3, admin.getEmail());
            statement.setString(4, admin.getMotDePasse());
            statement.setString(5, "admin"); // Type "admin" pour différencier les administrateurs des autres personnes
            statement.executeUpdate();

            // Récupérer l'ID généré pour l'administrateur
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int adminId = rs.getInt(1);
                // Ajout des utilisateurs associés à l'administrateur
                for (Utilisateur utilisateur : admin.getUtilisateurs()) {
                    addUtilisateurToAdmin(adminId, utilisateur); // Associer l'utilisateur à cet admin
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Ajouter un utilisateur à un administrateur (table de relation entre admin et utilisateur)
    private void addUtilisateurToAdmin(int adminId, Utilisateur utilisateur) {
        String query = "INSERT INTO admin (adminId, userId) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, adminId);
            statement.setInt(2, utilisateur.getId()); // Associer un utilisateur à l'admin par l'ID
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Récupérer un administrateur par son ID
    public Admin getAdminById(int id) {
        String query = "SELECT * FROM personne WHERE id = ? AND type = 'admin'";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Récupérer les utilisateurs associés à cet administrateur
                List<Utilisateur> utilisateurs = getUtilisateursByAdminId(id);
                return new Admin(
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("motDePasse"),
                        resultSet.getString("type"),
                        utilisateurs
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Utilisateur> getUtilisateursByAdminId(int adminId) {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT p.id AS userId, p.nom, p.prenom, p.email, p.motDePasse, p.type, u.budgetTotal " +
                "FROM utilisateur u " +
                "JOIN personne p ON u.userId = p.id " +
                "JOIN admin au ON u.userId = au.userId " +
                "WHERE au.adminId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, adminId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Utilisateur utilisateur = new Utilisateur(
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("motDePasse"),
                        resultSet.getString("type"),
                        resultSet.getDouble("budgetTotal"),
                        new ArrayList<>()
                );
                utilisateur.setId(resultSet.getInt("userId"));
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

    // Récupérer tous les administrateurs
    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        String query = "SELECT * FROM personne WHERE type = 'admin'";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                List<Utilisateur> utilisateurs = getUtilisateursByAdminId(resultSet.getInt("id"));
                admins.add(new Admin(
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("motDePasse"),
                        resultSet.getString("type"),
                        utilisateurs
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    // Supprimer un administrateur
    public void deleteAdmin(int id) {
        String query = "DELETE FROM personne WHERE id = ? AND type = 'admin'";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer un utilisateur associé à un administrateur
    public void deleteUtilisateurFromAdmin(int adminId, int utilisateurId) {
        String query = "DELETE FROM admin WHERE adminId = ? AND userId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, adminId);
            statement.setInt(2, utilisateurId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
