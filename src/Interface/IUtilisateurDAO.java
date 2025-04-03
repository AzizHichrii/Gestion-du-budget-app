package Interface;
import Classe.Utilisateur;
import java.util.List;
public interface IUtilisateurDAO {
    void addUtilisateur(Utilisateur utilisateur);
    Utilisateur getUtilisateurById(int id);
    List<Utilisateur> getAllUtilisateurs();
    void updateUtilisateur(Utilisateur utilisateur);
    void deleteUtilisateur(int id);
}
