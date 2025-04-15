package Classe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

    private static final String URL = "jdbc:mysql://localhost:3306/gestion_budget";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    // Bloc statique pour charger le pilote JDBC et vérifier la connexion au démarrage
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Chargement du pilote MySQL
            System.out.println("Pilote MySQL chargé avec succès !");
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur : Pilote MySQL introuvable !");
            e.printStackTrace();
        }
    }

    // Méthode pour établir une connexion à la base de données
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connexion à la base de données réussie !");
            } catch (SQLException e) {
                System.err.println("Échec de la connexion à la base de données :");
                throw e; // Relance l'exception pour la gestion externe
            }
        }
        return connection;
    }

    // Méthode pour fermer la connexion
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connexion fermée avec succès !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture de la connexion :");
            e.printStackTrace();
        }
    }
}