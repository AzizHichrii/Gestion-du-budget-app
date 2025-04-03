package monprojet.dao;

import Classe.Categorie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAO {
    private Connection connection;

    // Constructeur qui initialise la connexion à la base de données
    public CategorieDAO(Connection connection) {
        this.connection = connection;
    }

    // Ajouter une nouvelle catégorie
    public void addCategorie(Categorie categorie) {
        String query = "INSERT INTO categories (nom) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, categorie.getNom());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Récupérer une catégorie par son ID
    public Categorie getCategorieById(int id) {
        String query = "SELECT * FROM categories WHERE id = ?";
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

    // Récupérer toutes les catégories
    public List<Categorie> getAllCategories() {
        List<Categorie> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                categories.add(new Categorie(
                        resultSet.getInt("id"),
                        resultSet.getString("nom")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    // Mettre à jour une catégorie
    public void updateCategorie(Categorie categorie) {
        String query = "UPDATE categories SET nom = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, categorie.getNom());
            statement.setInt(2, categorie.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer une catégorie
    public void deleteCategorie(int id) {
        String query = "DELETE FROM categories WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
