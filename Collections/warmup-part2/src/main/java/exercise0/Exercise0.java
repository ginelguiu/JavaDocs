package exercise0;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 0: Iterate over the keys of a Map using keySet method (this method returns a Set of all the map keys) and
 *          print all its elements.
 */
public class Exercise0 {

    public Exercise0(){

    }

    public void iterateThroughMap(){

        // TODO Exercise #0 a) Create a Map (HashMap) and add elements to it (using put() method)
        Map<String, String> echipa = new LinkedHashMap<String, String>();
        echipa.put("GK", "Jaime Penedo");
        echipa.put("RB", "Romera Navarro");
        echipa.put("RCB", "Ionut Nedelcearu");
        echipa.put("LCB", "Giorgios Katsikas");
        echipa.put("LB", "Steliano Filip");
        echipa.put("RCM", "Paul Anton");
        echipa.put("LCM", "Filipe Nascimento");
        echipa.put("RM", "Sergiu Hanca");
        echipa.put("CAM", "Juan Albin");
        echipa.put("LM", "Claudiu Bumba");
        echipa.put("ST", "Adam Nemec");
        // TODO Exercise #0 a) Hint: Don't forget to specify the types of the key and value when creating the Map
        // TODO Exercise #0 b) Iterate over the Map using keySet() method and print all its elements
        for(String s : echipa.keySet())
        {
            System.out.println("["+s+"] = "+echipa.get(s));
        }
        // TODO Exercise #0 b) The elements could be printed like this: [key1=value1, key2=value2, ...]
        
    }

    public static void main(String[] args) {
        Exercise0 exercise0 = new Exercise0();
        exercise0.iterateThroughMap();
    }
}
