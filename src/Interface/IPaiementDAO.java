package Interface;
import Classe.Paiement;
import java.util.List;
public interface IPaiementDAO {
    void addPaiement(Paiement paiement);
    Paiement getPaiementById(int id);
    List<Paiement> getAllPaiements();
    void updatePaiement(Paiement paiement);
    void deletePaiement(int id);
}
