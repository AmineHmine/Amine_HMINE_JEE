package metier;
import dao.IDao;
public class MetierImpl implements IMetier{
    //couplage faible
    private IDao dao;
    @Override
    public double calculer() {
        double tmp=dao.getData();
        double res=tmp*540/Math.cos(tmp*Math.PI);
        return res;
    }
    //pour l'injection de dependance
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
