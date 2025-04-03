package monprojet.dao;
import Classe.Dépense;
import Classe.Categorie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DépenseDAO {
    private Connection connection;

    // Constructeur qui initialise la connexion à la base de données
    public DépenseDAO(Connection connection) {
        this.connection = connection;
    }

    // Ajouter une dépense
    public void addDépense(Dépense dépense) {
        String query = "INSERT INTO dépenses (montant, catégorie_id, date, méthodeDePaiement, userId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, dépense.getMontant());
            statement.setInt(2, dépense.getCategorie().getId()); // Utilisation de l'ID de la catégorie
            statement.setDate(3, Date.valueOf(dépense.getDate()));  // Assurez-vous que la date est bien formatée en LocalDate
            statement.setString(4, dépense.getMéthodePaiement());
            statement.setInt(5, dépense.getUserId());  // L'ID de l'utilisateur associé à la dépense
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Récupérer une dépense par son ID
    public Dépense getDépenseById(int id) {
        String query = "SELECT * FROM dépenses WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Charger la catégorie depuis la base de données avec l'ID de la catégorie
                int categorieId = resultSet.getInt("catégorie_id");
                Categorie categorie = getCategorieById(categorieId);  // Méthode pour récupérer la catégorie par son ID

                return new Dépense(
                        resultSet.getInt("id"),
                        resultSet.getDouble("montant"),
                        categorie,  // Passer l'objet catégorie ici
                        resultSet.getString("date"),
                        resultSet.getString("méthodeDePaiement"),
                        resultSet.getInt("userId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupérer toutes les dépenses d'un utilisateur
    public List<Dépense> getAllDépensesByUserId(int userId) {
        List<Dépense> dépenses = new ArrayList<>();
        String query = "SELECT * FROM dépenses WHERE userId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // Charger la catégorie depuis la base de données
                int categorieId = resultSet.getInt("catégorie_id");
                Categorie categorie = getCategorieById(categorieId);  // Méthode pour récupérer la catégorie par son ID

                dépenses.add(new Dépense(
                        resultSet.getInt("id"),
                        resultSet.getDouble("montant"),
                        categorie,  // Passer l'objet catégorie ici
                        resultSet.getString("date"),
                        resultSet.getString("méthodeDePaiement"),
                        resultSet.getInt("userId")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dépenses;
    }

    // Mettre à jour une dépense
    public void updateDépense(Dépense dépense) {
        String query = "UPDATE dépenses SET montant = ?, catégorie_id = ?, date = ?, méthodeDePaiement = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, dépense.getMontant());
            statement.setInt(2, dépense.getCategorie().getId()); // Utilisation de l'ID de la catégorie
            statement.setDate(3, Date.valueOf(dépense.getDate()));  // Conversion de LocalDate en Date
            statement.setString(4, dépense.getMéthodePaiement());
            statement.setInt(5, dépense.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer une dépense
    public void deleteDépense(int id) {
        String query = "DELETE FROM dépenses WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour récupérer une catégorie par son ID
    private Categorie getCategorieById(int id) {
        String query = "SELECT * FROM catégories WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Categorie(
                        resultSet.getInt("id"),
                        resultSet.getString("nom")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
