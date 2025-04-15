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
        try {
            // 1. Insertion dans la table personne
            String queryPersonne = "INSERT INTO personne (nom, prenom, email, motDePasse,type) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psPersonne = connection.prepareStatement(queryPersonne, Statement.RETURN_GENERATED_KEYS);
            psPersonne.setString(1, utilisateur.getNom());
            psPersonne.setString(2, utilisateur.getPrenom());
            psPersonne.setString(3, utilisateur.getEmail());
            psPersonne.setString(4, utilisateur.getMotDePasse());
            psPersonne.setString(5, utilisateur.getType());

            psPersonne.executeUpdate();

            // 2. Récupération de l'id généré
            ResultSet rs = psPersonne.getGeneratedKeys();
            int idPersonne = 0;
            if (rs.next()) {
                idPersonne = rs.getInt(1);
            }

            // 3. Insertion dans la table utilisateur
            String queryUtilisateur = "INSERT INTO utilisateur (userId, budgetTotal) VALUES (?, ?)";
            PreparedStatement psUtilisateur = connection.prepareStatement(queryUtilisateur);
            psUtilisateur.setInt(1, idPersonne); // userId = id de la personne
            psUtilisateur.setDouble(2, utilisateur.getBudgetTotal());
            psUtilisateur.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Utilisateur getUtilisateurById(int id) {
        String query = "SELECT p.*, u.budgetTotal FROM utilisateur u JOIN personne p ON u.userId = p.id WHERE u.userId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Utilisateur(
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("motDePasse"),
                        resultSet.getString("type"),
                        resultSet.getDouble("budgetTotal"),
                        null
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Utilisateur> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT p.*, u.budgetTotal FROM utilisateur u JOIN personne p ON u.userId = p.id";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                utilisateurs.add(new Utilisateur(
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("motDePasse"),
                        resultSet.getString("type"),
                        resultSet.getDouble("budgetTotal"),
                        null
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

    // Mettre à jour un utilisateur
    public void updateUtilisateur(Utilisateur utilisateur) {
        String query = "UPDATE utilisateur SET nom = ?, prenom = ?, email = ?, motDePasse = ?, type = ?, budgetTotal = ? WHERE id = ?";
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
        String query = "DELETE FROM utilisateur WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
