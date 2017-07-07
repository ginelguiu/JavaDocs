package exercise3;

/**
 * Created by Ginel.Guiu on 7/7/2017.
 */
public class Student3 extends Student{
    public Student3(String firstName, String lastName)
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
        Student3 s = (Student3)o;
        if(s.getFirstName().equals(this.getFirstName()))
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
