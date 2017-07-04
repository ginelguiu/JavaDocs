package ro.teamnet.animals.exceptii.exceptii.Clase;

import ro.teamnet.animals.exceptii.exceptii.Exceptii.AnimalManancaOmException;
import ro.teamnet.animals.exceptii.exceptii.Interfete.AngajatZoo;

/**
 * Created by Ginel.Guiu on 7/4/2017.
 */
public class AnimalZooFeroce extends Animal {
    public void mananca(Object o){
        if(o instanceof AngajatZoo)
            throw new AnimalManancaOmException("Vezi ca-l haleste!");
        else
            System.out.println("AnimalulZooFeroce mananca");
    };
    public void seJoaca(){
        System.out.println("AnimalulZooRar se joaca");
    };
    public void faceBaie(){
        System.out.println("AnimalulZooRar face baie");
    };

    @Override
    public void doarme()
    {
        super.doarme();
        System.out.println("Animalul feroce vaneaza");
    }
}
