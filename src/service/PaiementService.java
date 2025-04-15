package service;
import Interface.IPaiementDAO;
import Classe.Paiement;
import java.util.List;
public class PaiementService {
    private IPaiementDAO paiementDAO;

    public PaiementService(IPaiementDAO paiementDAO) {
        this.paiementDAO = paiementDAO;
    }

    public void ajouterPaiement(Paiement paiement) {
        paiementDAO.addPaiement(paiement);
    }

    public Paiement obtenirPaiement(int id) {
        return paiementDAO.getPaiementById(id);
    }

    public List<Paiement> listerPaiements() {
        return paiementDAO.getAllPaiements();
    }

    public void modifierPaiement(Paiement paiement) {
        paiementDAO.updatePaiement(paiement);
    }

    public void supprimerPaiement(int id) {
        paiementDAO.deletePaiement(id);
    }
}
