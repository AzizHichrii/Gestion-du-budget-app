package monprojet.dao;

import Classe.Facture;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FactureDAO {
    private Connection connection;

    public FactureDAO(Connection connection) {
        this.connection = connection;
    }

    // Ajouter une facture
    public void addFacture(Facture facture) {
        String query = "INSERT INTO factures (montantTotal, dateEmission, userId) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDouble(1, facture.getMontantTotal());
            statement.setDate(2, new java.sql.Date(facture.getDateEmission().getTime())); // Conversion Date SQL
            statement.setInt(3, facture.getUserId());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                facture.setId(generatedKeys.getInt(1)); // Récupération de l'ID généré
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Récupérer une facture par ID
    public Facture getFactureById(int id) {
        String query = "SELECT * FROM factures WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Facture(
                        resultSet.getInt("id"),
                        resultSet.getDouble("montantTotal"),
                        resultSet.getDate("dateEmission"),
                        resultSet.getInt("userId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupérer toutes les factures d'un utilisateur
    public List<Facture> getAllFacturesByUserId(int userId) {
        List<Facture> factures = new ArrayList<>();
        String query = "SELECT * FROM factures WHERE userId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                factures.add(new Facture(
                        resultSet.getInt("id"),
                        resultSet.getDouble("montantTotal"),
                        resultSet.getDate("dateEmission"),
                        resultSet.getInt("userId")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return factures;
    }

    // Supprimer une facture
    public void deleteFacture(int id) {
        String query = "DELETE FROM factures WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
