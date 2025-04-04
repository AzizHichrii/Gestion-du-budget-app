package service;
import Interface.IPersonneDAO;
import Classe.Personne;
import java.util.List;

public class PersonneService {
    private IPersonneDAO personneDAO;

    public PersonneService(IPersonneDAO personneDAO) {
        this.personneDAO = personneDAO;
    }

    public void ajouterPersonne(Personne personne) {
        personneDAO.addPersonne(personne);
    }

    public Personne obtenirPersonne(int id) {
        return personneDAO.getPersonneById(id);
    }

    public List<Personne> listerPersonnes() {
        return personneDAO.getAllPersonnes();
    }

    public void modifierPersonne(Personne personne) {
        personneDAO.updatePersonne(personne);
    }

    public void supprimerPersonne(int id) {
        personneDAO.deletePersonne(id);
    }
}
