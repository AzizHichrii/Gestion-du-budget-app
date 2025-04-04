package service;
import Interface.IDépenseDAO;
import Classe.Dépense;
import java.util.List;
public class DepenseService {
    private IDépenseDAO depenseDAO;

    public DepenseService(IDépenseDAO depenseDAO) {
        this.depenseDAO = depenseDAO;
    }

    public void ajouterDepense(Dépense depense) {
        depenseDAO.addDépense(depense);
    }

    public Dépense obtenirDepense(int id) {
        return depenseDAO.getDépenseById(id);
    }

    public List<Dépense> listerDepenses() {
        return depenseDAO.getAllDépensesByUserId();
    }

    public void modifierDepense(Dépense depense) {
        depenseDAO.updateDépense(depense);
    }

    public void supprimerDepense(int id) {
        depenseDAO.deleteDépense(id);
    }
}
