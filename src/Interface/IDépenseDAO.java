package Interface;
import Classe.Dépense;
import java.util.List;
public interface IDépenseDAO {
    void addDépense(Dépense dépense);
    Dépense getDépenseById(int id);
    List<Dépense> getAllDépensesByUserId(int userId);
    void updateDépense(Dépense dépense);
    void deleteDépense(int id);
}
