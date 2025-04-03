package monprojet.dao;

import Classe.Rappel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RappelDAO {
    private Connection connection;

    // Constructeur qui initialise la connexion à la base de données
    public RappelDAO(Connection connection) {
        this.connection = connection;
    }

    // Ajouter un rappel
    public void addRappel(Rappel rappel) {
        String query = "INSERT INTO rappels (date, montant, description, userId) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, new java.sql.Date(rappel.getDate().getTime())); // Conversion de Date en SQL Date
            statement.setDouble(2, rappel.getMontant());
            statement.setString(3, rappel.getDescription());
            statement.setInt(4, rappel.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Récupérer un rappel par son ID
    public Rappel getRappelById(int id) {
        String query = "SELECT * FROM rappels WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Rappel(
                        resultSet.getInt("id"),
                        resultSet.getDate("date"),
                        resultSet.getDouble("montant"),
                        resultSet.getString("description"),
                        resultSet.getInt("userId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupérer tous les rappels d'un utilisateur par son ID
    public List<Rappel> getAllRappelsByUserId(int userId) {
        List<Rappel> rappels = new ArrayList<>();
        String query = "SELECT * FROM rappels WHERE userId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rappels.add(new Rappel(
                        resultSet.getInt("id"),
                        resultSet.getDate("date"),
                        resultSet.getDouble("montant"),
                        resultSet.getString("description"),
                        resultSet.getInt("userId")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rappels;
    }

    // Mettre à jour un rappel
    public void updateRappel(Rappel rappel) {
        String query = "UPDATE rappels SET date = ?, montant = ?, description = ?, userId = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, new java.sql.Date(rappel.getDate().getTime())); // Conversion de Date en SQL Date
            statement.setDouble(2, rappel.getMontant());
            statement.setString(3, rappel.getDescription());
            statement.setInt(4, rappel.getUserId());
            statement.setInt(5, rappel.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer un rappel
    public void deleteRappel(int id) {
        String query = "DELETE FROM rappels WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
