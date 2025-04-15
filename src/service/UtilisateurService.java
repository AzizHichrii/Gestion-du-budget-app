package service;
import Interface.IUtilisateurDAO;
import Classe.Utilisateur;
import java.util.List;
public class UtilisateurService {
    private IUtilisateurDAO utilisateurDAO;

    public UtilisateurService(IUtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurDAO.addUtilisateur(utilisateur);
    }

    public Utilisateur obtenirUtilisateur(int id) {
        return utilisateurDAO.getUtilisateurById(id);
    }

    public List<Utilisateur> listerUtilisateurs() {
        return utilisateurDAO.getAllUtilisateurs();
    }

    public void modifierUtilisateur(Utilisateur utilisateur) {
        utilisateurDAO.updateUtilisateur(utilisateur);
    }

    public void supprimerUtilisateur(int id) {
        utilisateurDAO.deleteUtilisateur(id);
    }
}
