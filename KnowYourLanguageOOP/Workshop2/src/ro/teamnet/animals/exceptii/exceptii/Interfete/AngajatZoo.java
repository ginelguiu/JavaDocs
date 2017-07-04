package ro.teamnet.animals.exceptii.exceptii.Interfete;

import ro.teamnet.animals.exceptii.exceptii.Clase.Animal;

/**
 * Created by Ginel.Guiu on 7/4/2017.
 */
public interface AngajatZoo {
    public int valoareBonusPerAnimal = 50;
    public int calculeazaBonusSalarial();
    public void lucreaza(Animal animal);
}
