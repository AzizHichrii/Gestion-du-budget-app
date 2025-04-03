package Interface;
import Classe.Facture;
import java.util.List;
public interface IFactureDAO {
    void addFacture(Facture facture);
    Facture getFactureById(int id);
    List<Facture> getAllFactures();
    void updateFacture(Facture facture);
    void deleteFacture(int id);
}
