package ro.teamnet.animals.exceptii.exceptii.Clase;

import ro.teamnet.animals.exceptii.exceptii.Exceptii.AnimalPeCaleDeDisparitieException;
import ro.teamnet.animals.exceptii.exceptii.Interfete.AngajatZoo;

/**
 * Created by Ginel.Guiu on 7/4/2017.
 */
public class IngrijitorZoo implements AngajatZoo {

    private int bonus;
    public void lucreaza(Animal animal)
    {
        System.out.println("Ingrijitorul intra in cusca animalului");
    }
    public void lucreaza(Animal animal, Object mancare) throws AnimalPeCaleDeDisparitieException {
        this.lucreaza(animal);
        animal.doarme();
        animal.faceBaie();
        animal.seJoaca();
        animal.mananca(mancare);
        this.bonus=calculeazaBonusSalarial();
        System.out.println(this.bonus);
        if(animal instanceof AnimalZooRar && mancare==null)
            throw new AnimalPeCaleDeDisparitieException("Vezi ca asta nu mai duce mult, terminatule");
    }

    public int getBonus() {
        return bonus;
    }

    public int calculeazaBonusSalarial()
    {
        return 3*valoareBonusPerAnimal;
    }
}
