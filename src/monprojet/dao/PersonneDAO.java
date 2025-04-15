package monprojet.dao;

import Classe.Personne;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonneDAO {
    private Connection connection;

    // Constructeur qui initialise la connexion à la base de données
    public PersonneDAO(Connection connection) {
        this.connection = connection;
    }

    // Ajouter une personne (Utilisateur ou Administrateur)
    public void addPersonne(Personne personne) {
        String query = "INSERT INTO personne (nom, prenom, email, motDePasse,type) VALUES (?, ?, ?, ?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, personne.getNom());
            statement.setString(2, personne.getPrenom());
            statement.setString(3, personne.getEmail());
            statement.setString(4, personne.getMotDePasse());
            statement.setString(5, personne.getType());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Récupérer une personne par son ID
    public Personne getPersonneById(int id) {
        String query = "SELECT * FROM personne WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Personne(
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("motDePasse"),
                        resultSet.getString("type")

                ) {
                    // Implémentation anonyme car Personne est abstraite
                };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupérer toutes les personnes
    public List<Personne> getAllPersonnes() {
        List<Personne> personnes = new ArrayList<>();
        String query = "SELECT * FROM personne";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                personnes.add(new Personne(
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("motDePasse"),
                        resultSet.getString("type")

                ) {
                    // Implémentation anonyme car Personne est abstraite
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personnes;
    }

    // Mettre à jour une personne
    public void updatePersonne(Personne personne) {
        String query = "UPDATE personne SET nom = ?, prenom = ?, email = ?, motDePasse = ?, type = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, personne.getNom());
            statement.setString(2, personne.getPrenom());
            statement.setString(3, personne.getEmail());
            statement.setString(4, personne.getMotDePasse());
            statement.setString(5, personne.getType());

            statement.setInt(6, Personne.getId()); // Utilisation de Personne.getId() pour l'ID
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer une personne par son ID
    public void deletePersonne(int id) {
        String query = "DELETE FROM personne WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
