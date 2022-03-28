package pres;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Pres2 {
    public static void main(String[] args) throws Exception {
        /*injection de dep par instanciation dynamique (utilisation du fichier config)*/
        Scanner sxanner=new Scanner(new File("config.txt"));


        String daoClassName=sxanner.nextLine(); //lire la premiere ligne du fichier config
        Class cDao = Class.forName(daoClassName); //trouver le nom de la classe et charger le au memoire
        IDao dao= (IDao) cDao.newInstance(); //creer une instance pour cette classe

        String metierClassName=sxanner.nextLine();
        Class cMetier = Class.forName(metierClassName);
        IMetier metier= (IMetier) cMetier.newInstance();

        Method method=cMetier.getMethod("setDao",IDao.class); //appeler la methode setDao
        method.invoke(metier,dao);

        System.out.println("le resultat est : "+metier.calculer());
    }
}
