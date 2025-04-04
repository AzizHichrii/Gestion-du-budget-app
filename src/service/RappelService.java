package service;
import Interface.IRappelDAO;
import Classe.Rappel;
import java.util.List;
public class RappelService {
    private IRappelDAO rappelDAO;

    public RappelService(IRappelDAO rappelDAO) {
        this.rappelDAO = rappelDAO;
    }

    public void ajouterRappel(Rappel rappel) {
        rappelDAO.addRappel(rappel);
    }

    public Rappel obtenirRappel(int id) {
        return rappelDAO.getRappelById(id);
    }

    public List<Rappel> listerRappels() {
        return rappelDAO.getAllRappels();
    }

    public void modifierRappel(Rappel rappel) {
        rappelDAO.updateRappel(rappel);
    }

    public void supprimerRappel(int id) {
        rappelDAO.deleteRappel(id);
    }
}
