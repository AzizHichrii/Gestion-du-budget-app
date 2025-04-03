package Interface;
import Classe.Admin;
import java.util.List;
public interface IAdminDAO {
    void addAdmin(Admin admin);
    Admin getAdminById(int id);
    List<Admin> getAllAdmins();
    void updateAdmin(Admin admin);
    void deleteAdmin(int id);
}
