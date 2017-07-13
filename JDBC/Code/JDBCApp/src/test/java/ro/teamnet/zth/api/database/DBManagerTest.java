package ro.teamnet.zth.api.database;


import org.junit.Test;

import java.sql.SQLException;
import static org.junit.Assert.assertEquals;

/**
 * Created by Ginel.Guiu on 7/13/2017.
 */
public class DBManagerTest {
    @Test
    public void testCheckConnection() throws SQLException, ClassNotFoundException {
        assertEquals(true, DBManager.checkConnection(DBManager.getConnection()));
    }
}
