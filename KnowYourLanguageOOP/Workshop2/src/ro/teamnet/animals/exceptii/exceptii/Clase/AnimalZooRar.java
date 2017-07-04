package ro.teamnet.animals.exceptii.exceptii.Clase;

import ro.teamnet.animals.exceptii.exceptii.Exceptii.AnimalManancaOmException;
import ro.teamnet.animals.exceptii.exceptii.Interfete.AngajatZoo;

/**
 * Created by Ginel.Guiu on 7/4/2017.
 */
public class AnimalZooRar extends Animal{
    private String nume;
    private String numeTaraDeOrigine;

    public AnimalZooRar()
    {
        this.nume = "Pierdut de viata";
        this.numeTaraDeOrigine = "Din flori";
    }
    public AnimalZooRar(String nume, String numeTaraDeOrigine)
    {
        this.nume=nume;
        this.numeTaraDeOrigine=numeTaraDeOrigine;
    }

    public void mananca(Object o){
        if(o instanceof AngajatZoo)
            throw new AnimalManancaOmException("Vezi ca-l haleste!");
        else
            System.out.println("AnimalulZooRar mananca");
    };
    public void seJoaca(){
        System.out.println("AnimalulZooRar se joaca");
    };
    public void faceBaie(){
        System.out.println("AnimalulZooRar face baie");
    };

}
