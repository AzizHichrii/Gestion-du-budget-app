package service;
import Classe.Facture;
import java.util.List;
import Interface.IFactureDAO;
public class FactureService {
    private IFactureDAO factureDAO;

    public FactureService(IFactureDAO factureDAO) {
        this.factureDAO = factureDAO;
    }

    public void ajouterFacture(Facture facture) {
        factureDAO.addFacture(facture);
    }

    public Facture obtenirFacture(int id) {
        return factureDAO.getFactureById(id);
    }

    public List<Facture> listerFactures() {
        return factureDAO.getAllFactures();
    }

    public void modifierFacture(Facture facture) {
        factureDAO.updateFacture(facture);
    }

    public void supprimerFacture(int id) {
        factureDAO.deleteFacture(id);
    }
}
