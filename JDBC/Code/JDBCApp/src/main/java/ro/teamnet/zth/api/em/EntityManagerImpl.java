package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.database.DBManager;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.*;
/**
 * Created by Ginel.Guiu on 7/13/2017.
 */
public class EntityManagerImpl implements EntityManager {
    @Override
    public <T> T findById(Class<T> entityClass, Long id) throws SQLException, ClassNotFoundException {
        Connection connection = DBManager.getConnection();
        List<Field> listFields = new ArrayList<Field>();
        String tableName = EntityUtils.getTableName(entityClass);
        listFields = EntityUtils.getFieldsByAnnotations(entityClass, Id.class);
        List<ColumnInfo> listColumns = new ArrayList<ColumnInfo>();
        listColumns = EntityUtils.getColumns(entityClass);

        Condition c1 = new Condition();
        for(ColumnInfo c:listColumns)
        {
            if(c.isId())
            {
                c1.setColumnName(c.getDbColumnName());
                c1.setValue(id);
            }
        }
        QueryBuilder qb = new QueryBuilder();
        qb.setTableName(tableName);
        qb.addCondition(c1);
        qb.setQueryType(QueryType.SELECT);
        qb.addQueryColumns(listColumns);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(qb.createQuery());
        T instance = null;
        if(resultSet.next())
        {
            try{
                instance = entityClass.newInstance();
                for(ColumnInfo c:listColumns)
                {
                    Field f= instance.getClass().getDeclaredField(c.getColumnName());
                    f.setAccessible(true);
                    f.set(instance, EntityUtils.castFromSqlType(resultSet.getObject(c.getDbColumnName()),c.getColumnType()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        return instance;
    }

    @Override
    public Long getNextIdVal(String tableName, String columnIdName) {
        try {
            Connection con = DBManager.getConnection();
            Statement stm = con.createStatement();
            String SQL = "SELECT max("+columnIdName+") FROM "+tableName;
            ResultSet result = stm.executeQuery(SQL);
            result.next();
            return result.getLong(1)+1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> Object insert(T entity) {
        try {
            Connection con = DBManager.getConnection();
            long id = 0;
            String tableName = EntityUtils.getTableName(entity.getClass());
            List<ColumnInfo> listColumns = new ArrayList<ColumnInfo>();
            listColumns = EntityUtils.getColumns(entity.getClass());
            for(ColumnInfo c:listColumns)
            {
                if(c.isId())
                {
                    id = getNextIdVal(tableName,c.getDbColumnName());
                    c.setValue(getNextIdVal(tableName, c.getDbColumnName()));
                }
                else
                {
                    Field f = entity.getClass().getDeclaredField(c.getColumnName());
                    f.setAccessible(true);
                    c.setValue(f.get(entity));
                }
            }
            QueryBuilder qb = new QueryBuilder();
            qb.setQueryType(QueryType.INSERT);
            qb.setTableName(tableName);
            qb.addQueryColumns(listColumns);
            Statement stm = con.createStatement();
            stm.executeUpdate(qb.createQuery());
            return findById(entity.getClass(),id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        try
        {
            Connection con = DBManager.getConnection();
            String tableName = EntityUtils.getTableName(entityClass);
            List<ColumnInfo> columnList = EntityUtils.getColumns(entityClass);
            QueryBuilder qb = new QueryBuilder();
            qb.setTableName(tableName);
            qb.addQueryColumns(columnList);
            qb.setQueryType(QueryType.SELECT);
            String sql = qb.createQuery();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<T> list = new ArrayList<T>();
            while(rs.next())
            {
                T instance = entityClass.newInstance();
                for(ColumnInfo column : columnList)
                {
                    Field field = instance.getClass().getDeclaredField(column.getColumnName());
                    field.setAccessible(true);
                    field.set(instance,EntityUtils.castFromSqlType(rs.getObject(column.getDbColumnName()),field.getType()));
                }
                list.add(instance);

            }
            return list;
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
