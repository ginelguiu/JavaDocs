package exercise3;

/**
 * Created by Ginel.Guiu on 7/7/2017.
 */
public class Student4 extends Student {
    public Student4(String firstName, String lastName)
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
        Student4 s = (Student4)o;
        if(s.getFirstName().equals(this.getFirstName()) && s.getLastName().equals(this.getLastName()))
            return true;
        return false;
    }

    @Override
    public int hashCode()
    {
        int result = this.getFirstName().hashCode();
        result = result*18 + this.getLastName().hashCode();
        return result;
    }
}
