package monprojet.dao;
import Classe.Utilisateur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {
    private Connection connection;

    public UtilisateurDAO(Connection connection) {
        this.connection = connection;
    }

    // Ajouter un nouvel utilisateur
    public void addUtilisateur(Utilisateur utilisateur) {
        String query = "INSERT INTO users (nom, prenom, email, motDePasse, budgetTotal) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, utilisateur.getNom());
            statement.setString(2, utilisateur.getPrenom());
            statement.setString(3, utilisateur.getEmail());
            statement.setString(4, utilisateur.getMotDePasse());
            statement.setDouble(5, utilisateur.getBudgetTotal());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Récupérer un utilisateur par son ID
    public Utilisateur getUtilisateurById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Utilisation de l'id depuis la classe Personne
                return new Utilisateur(
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("motDePasse"),
                        resultSet.getDouble("budgetTotal"),
                        null // Liste des dépenses à initialiser plus tard
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupérer tous les utilisateurs
    public List<Utilisateur> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                utilisateurs.add(new Utilisateur(
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("motDePasse"),
                        resultSet.getDouble("budgetTotal"),
                        null // Liste des dépenses à initialiser plus tard
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

    // Mettre à jour un utilisateur
    public void updateUtilisateur(Utilisateur utilisateur) {
        String query = "UPDATE users SET nom = ?, prenom = ?, email = ?, motDePasse = ?, budgetTotal = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, utilisateur.getNom());
            statement.setString(2, utilisateur.getPrenom());
            statement.setString(3, utilisateur.getEmail());
            statement.setString(4, utilisateur.getMotDePasse());
            statement.setDouble(5, utilisateur.getBudgetTotal());
            statement.setInt(6, utilisateur.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer un utilisateur
    public void deleteUtilisateur(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
