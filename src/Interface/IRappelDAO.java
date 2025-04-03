package Interface;
import Classe.Rappel;
import java.util.List;
public interface IRappelDAO {
    void addRappel(Rappel rappel);
    Rappel getRappelById(int id);
    List<Rappel> getAllRappels();
    void updateRappel(Rappel rappel);
    void deleteRappel(int id);
}
