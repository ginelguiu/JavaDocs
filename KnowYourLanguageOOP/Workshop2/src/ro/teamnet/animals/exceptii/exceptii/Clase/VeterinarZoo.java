package ro.teamnet.animals.exceptii.exceptii.Clase;

import ro.teamnet.animals.exceptii.exceptii.Interfete.AngajatZoo;

/**
 * Created by Ginel.Guiu on 7/4/2017.
 */
public class VeterinarZoo implements AngajatZoo {
    private int bonus;
    public void lucreaza(Animal animal)
    {
        System.out.println("Veterinarius are grija de animal");
        this.bonus=calculeazaBonusSalarial();
        System.out.println(calculeazaBonusSalarial());
        if(animal instanceof AnimalZooFeroce)
            animal.faceBaie();
    }

    public int getBonus() {
        return bonus;
    }

    public int calculeazaBonusSalarial()
    {
        return 2*valoareBonusPerAnimal;
    }
}
