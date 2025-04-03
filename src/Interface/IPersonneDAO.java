package Interface;
import Classe.Personne;
import java.util.List;
public interface IPersonneDAO {
    void addPersonne(Personne personne);
    Personne getPersonneById(int id);
    List<Personne> getAllPersonnes();
    void updatePersonne(Personne personne);
    void deletePersonne(int id);
}
