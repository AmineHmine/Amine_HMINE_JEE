
package ext;
import dao.IDao;

public class DaoImpl2 implements IDao {
    @Override
    public double getData() {
        System.out.println("varsion capteur");
        double temp=1000;
        return temp;
    }
}
