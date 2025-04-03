package monprojet.dao;
import Classe.Paiement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaiementDAO {
    private Connection connection;

    // Constructeur avec connexion à la BDD
    public PaiementDAO(Connection connection) {
        this.connection = connection;
    }

    // Ajouter un paiement
    public void addPaiement(Paiement paiement) {
        String query = "INSERT INTO paiements (montant, datePaiement, methodePaiement, factureId) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, paiement.getMontant());
            statement.setDate(2, new java.sql.Date(paiement.getDatePaiement().getTime()));
            statement.setString(3, paiement.getMethodePaiement());
            statement.setInt(4, paiement.getFactureId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Récupérer un paiement par son ID
    public Paiement getPaiementById(int id) {
        String query = "SELECT * FROM paiements WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Paiement(
                        resultSet.getInt("id"),
                        resultSet.getDouble("montant"),
                        resultSet.getDate("datePaiement"),
                        resultSet.getString("methodePaiement"),
                        resultSet.getInt("factureId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupérer tous les paiements
    public List<Paiement> getAllPaiements() {
        List<Paiement> paiements = new ArrayList<>();
        String query = "SELECT * FROM paiements";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                paiements.add(new Paiement(
                        resultSet.getInt("id"),
                        resultSet.getDouble("montant"),
                        resultSet.getDate("datePaiement"),
                        resultSet.getString("methodePaiement"),
                        resultSet.getInt("factureId")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paiements;
    }

    // Mettre à jour un paiement
    public void updatePaiement(Paiement paiement) {
        String query = "UPDATE paiements SET montant = ?, datePaiement = ?, methodePaiement = ?, factureId = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, paiement.getMontant());
            statement.setDate(2, new java.sql.Date(paiement.getDatePaiement().getTime()));
            statement.setString(3, paiement.getMethodePaiement());
            statement.setInt(4, paiement.getFactureId());
            statement.setInt(5, paiement.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer un paiement
    public void deletePaiement(int id) {
        String query = "DELETE FROM paiements WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
