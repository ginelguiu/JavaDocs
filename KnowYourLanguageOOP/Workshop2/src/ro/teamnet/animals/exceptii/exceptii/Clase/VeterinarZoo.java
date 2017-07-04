package ro.teamnet.animals.exceptii.exceptii.Clase;

import ro.teamnet.animals.exceptii.exceptii.Interfete.AngajatZoo;

/**
 * Created by Ginel.Guiu on 7/4/2017.
 */
public class VeterinarZoo implements AngajatZoo {
    public void lucreaza(Animal animal)
    {
        System.out.println("Veterinarius are grija de animal");
        if(animal instanceof AnimalZooFeroce)
            animal.faceBaie();
    }
}
