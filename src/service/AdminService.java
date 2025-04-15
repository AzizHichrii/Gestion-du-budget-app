package service;
import Classe.Admin;
import Interface.IAdminDAO;
import java.util.List;
public class AdminService {
    private IAdminDAO adminDAO;

    public AdminService(IAdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public void ajouterAdmin(Admin admin) {
        adminDAO.addAdmin(admin);
    }

    public Admin obtenirAdmin(int id) {
        return adminDAO.getAdminById(id);
    }

    public List<Admin> listerAdmins() {
        return adminDAO.getAllAdmins();
    }

    public void modifierAdmin(Admin admin) {
        adminDAO.updateAdmin(admin);
    }

    public void supprimerAdmin(int id) {
        adminDAO.deleteAdmin(id);
    }
}
