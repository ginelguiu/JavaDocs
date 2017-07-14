package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Ginel.Guiu on 7/13/2017.
 */
public class EntityManagerImplTest {

    @Test
    public void testFindById() throws SQLException, ClassNotFoundException {
        EntityManagerImpl manager = new EntityManagerImpl();
        Department d = manager.findById(Department.class, 70l);
        assertEquals("Public Relations", d.getDepartmentName());
    }
    @Test
    public void testGetNextIdVal() {
        EntityManagerImpl manager = new EntityManagerImpl();
        long id = manager.getNextIdVal("locations", "location_id");

        assertNotEquals("Shouldn't be -1!", -1, id);
        assertEquals("Id different than expected!", 3201L, id);
    }

    @Test
    public void testInsert() {
        EntityManagerImpl manager = new EntityManagerImpl();
        Department d = new Department();
        d.setDepartmentName("Telecommunication");
        d.setLocation(1000L);
        Department result = (Department) manager.insert(d);

//        assertNotNull("Shouldn't be null!", result);
        assertEquals("Department name different than expected!", "Telecommunication", result.getDepartmentName());


    }
    @Test
    public void testUpdate(){
        EntityManagerImpl manager = new EntityManagerImpl();
        Department d = new Department();
        d.setDepartmentName("Ominous");
        d.setLocation(1200l);
        d.setId(270l);
        Department result2 = (Department) manager.update(d);
        assertEquals("Not good!","Ominous",result2.getDepartmentName());
    }

    @Test
    public void testDelete(){
        EntityManagerImpl manager = new EntityManagerImpl();
        Department d = new Department();
        d.setId(271L);
        manager.delete(d);
    }
    @Test
    public void findAllTest()throws Exception
    {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        List<Department> departments = entityManager.findAll(Department.class);

        assertEquals("List size should be 28",28,departments.size());
    }
    @Test
    public void findByParamsTest()
    {
        EntityManagerImpl eM = new EntityManagerImpl();
        Map<String, Object> params = new HashMap<>();
        params.put("Location_id", 1700L);
        params.put("Department_name", "IT Support");
        List<Department> result = eM.findByParams(Department.class, params);

        assertEquals(1,result.size());
    }

    @Test
    public void findEmployeesTest()
    {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        List<Employee> result = entityManager.findEmployees("eS");

        assertEquals(35,result.size());
    }

}
