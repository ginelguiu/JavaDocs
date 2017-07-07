package exercise3;

/**
 * Created by Ginel.Guiu on 7/7/2017.
 */
public class Student1 extends Student {
    public Student1(String firstName, String lastName)
    {
        super(firstName, lastName);
    }
    @Override
    public boolean equals(Object o)
    {
        if(this==o)
            return true;
        if(o==null || getClass() !=o.getClass())
            return false;
        Student1 s = (Student1)o;
        if(s.getFirstName().equals(this.getFirstName()))
            return true;
        return false;
    }

    @Override
    public int hashCode()
    {
        int result = this.getFirstName().hashCode();
        return result;
    }
}
