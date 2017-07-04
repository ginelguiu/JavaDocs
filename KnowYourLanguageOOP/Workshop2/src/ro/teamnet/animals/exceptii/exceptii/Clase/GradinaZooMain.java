package ro.teamnet.animals.exceptii.exceptii.Clase;

import ro.teamnet.animals.exceptii.exceptii.Exceptii.AnimalPeCaleDeDisparitieException;
import ro.teamnet.animals.exceptii.exceptii.Interfete.AngajatZoo;

/**
 * Created by Ginel.Guiu on 7/4/2017.
 */
public class GradinaZooMain {
    public static void main(String[] args) throws AnimalPeCaleDeDisparitieException {
        AnimalZooRar animal1 = new AnimalZooRar("Pinguin", "Georgia");
        AnimalZooRar animal2 = new AnimalZooRar("Elefant","Africa");
        AnimalZooRar animal3 = new AnimalZooRar();

        AnimalZooFeroce animalFeroce = new AnimalZooFeroce();

        AngajatZoo angajat1 = new IngrijitorZoo();
        IngrijitorZoo angajat2 = new IngrijitorZoo();

        AngajatZoo angajat3 = new VeterinarZoo();
        VeterinarZoo angajat4 = new VeterinarZoo();

        angajat3.lucreaza(animal1);
        angajat3.lucreaza(animal2);
        angajat3.lucreaza(animal3);
        angajat4.lucreaza(animal1);
        angajat4.lucreaza(animal2);
        angajat4.lucreaza(animal3);

        angajat1.lucreaza(animal1);
        angajat1.lucreaza(animal2);
        angajat1.lucreaza(animal3);

        angajat2.lucreaza(animal1);
        angajat2.lucreaza(animal2);
        angajat2.lucreaza(animal3);

        try {
            angajat2.lucreaza(animal1,null);
        } catch (AnimalPeCaleDeDisparitieException e) {
            e.printStackTrace();
        }
        try {
            angajat2.lucreaza(animal1,angajat1);
        } catch (AnimalPeCaleDeDisparitieException e) {
            e.printStackTrace();
        }
        try {
            angajat2.lucreaza(animal1,new String("Mancare"));
        } catch (AnimalPeCaleDeDisparitieException e) {
            e.printStackTrace();
        }

        angajat2.lucreaza(animalFeroce);
        try {
            angajat2.lucreaza(animalFeroce,null);
        } catch (AnimalPeCaleDeDisparitieException e) {
            e.printStackTrace();
        }
        angajat2.lucreaza(animalFeroce, new String("Mancare"));

        System.out.println("Finish!");


    }
}
