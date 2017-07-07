package exercise3;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ginel.Guiu on 7/7/2017.
 */
public class ProgMain {

    public static void main(String[] args)
    {
        Map<Student1, BigDecimal> map1 = new HashMap<Student1, BigDecimal>();
        Map<Student2, BigDecimal> map2 = new HashMap<Student2, BigDecimal>();
        Map<Student3, BigDecimal> map3 = new HashMap<Student3, BigDecimal>();
        Map<Student4, BigDecimal> map4 = new HashMap<Student4, BigDecimal>();
        Student1 s1 = new Student1("Renato", "Sanches");
        Student1 sn1 = new Student1("Renato", "Sanches");
        Student2 s2 = new Student2("Corentin", "Tolisso");
        Student3 s3 = new Student3("Geralt","of Rivia");
        Student4 s4 = new Student4("Denis", "Ciobotariu");
        map1.put(s1,BigDecimal.valueOf(35));
        map2.put(s2,BigDecimal.valueOf(24));
        map3.put(s3,BigDecimal.valueOf(10));
        map4.put(s4,BigDecimal.valueOf(3));
        System.out.println(map1);
        System.out.println(map2);
        System.out.println(map3);
        System.out.println(map4);
        System.out.println(s1.equals(sn1));
    }
}
