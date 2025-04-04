package service;
import Interface.ICategorieDAO;
import Classe.Categorie;
import java.util.List;
public class CategorieService {
    private ICategorieDAO categorieDAO;

    public CategorieService(ICategorieDAO categorieDAO) {
        this.categorieDAO = categorieDAO;
    }

    public void ajouterCategorie(Categorie categorie) {
        categorieDAO.addCategorie(categorie);
    }

    public Categorie obtenirCategorie(int id) {
        return categorieDAO.getCategorieById(id);
    }

    public List<Categorie> listerCategories() {
        return categorieDAO.getAllCategories();
    }

    public void modifierCategorie(Categorie categorie) {
        categorieDAO.updateCategorie(categorie);
    }

    public void supprimerCategorie(int id) {
        categorieDAO.deleteCategorie(id);
    }
}
