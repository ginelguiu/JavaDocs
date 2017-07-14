package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.database.DBManager;
import ro.teamnet.zth.appl.domain.Employee;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.*;
import java.util.Map;
import java.util.Set;

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

    @Override
    public <T> T update(T entity) {
        Long id=0L;
        String dbColumnName="";
        try {
            Connection conn=DBManager.getConnection();
            String nameTable=EntityUtils.getTableName(entity.getClass());
            List<ColumnInfo> listaCol=new ArrayList<ColumnInfo>();
            listaCol=EntityUtils.getColumns(entity.getClass());
            for(ColumnInfo col:listaCol)
            {
                Field field=entity.getClass().getDeclaredField(col.getColumnName());
                field.setAccessible(true);
                col.setValue(field.get(entity));
                if(col.isId())
                {
                    id= (Long) col.getValue();
                    dbColumnName=col.getDbColumnName();
                }
            }
            Condition obj=new Condition();
            obj.setValue(id);
            obj.setColumnName(dbColumnName);

            QueryBuilder qb=new QueryBuilder();
            qb.setTableName(nameTable);
            qb.setQueryType(QueryType.UPDATE);
            qb.addQueryColumns(listaCol);
            qb.addCondition(obj);
            Statement stm=DBManager.getConnection().createStatement();

            ResultSet resultSet=stm.executeQuery(qb.createQuery());
            if(resultSet.next())
                return entity;

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
    public void delete(Object entity) {
        Connection con = null;
        try {
            con = DBManager.getConnection();
            String tableName = EntityUtils.getTableName(entity.getClass());
            List<ColumnInfo> listColumns = EntityUtils.getColumns(entity.getClass());
            Long id = 0L;
            String cN = "";
            for(ColumnInfo c:listColumns)
            {
                if(c.isId())
                {
                    id = getNextIdVal(tableName,c.getDbColumnName());
                    c.setValue(getNextIdVal(tableName, c.getDbColumnName()));
                    cN = c.getDbColumnName();
                }
                Field f = entity.getClass().getDeclaredField(c.getColumnName());
                f.setAccessible(true);
                c.setValue(f.get(entity));
            }
            Condition c1 = new Condition();
            c1.setValue(id-1);
            c1.setColumnName(cN);
            QueryBuilder qb = new QueryBuilder();
            qb.addCondition(c1);
            qb.setQueryType(QueryType.DELETE);
            qb.setTableName(tableName);
            qb.addQueryColumns(listColumns);
            Statement stm = con.createStatement();
            stm.executeUpdate(qb.createQuery());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


    }

    @Override
    public <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params) {
        Connection con = null;
        try {
            con = DBManager.getConnection();
            String tableName = EntityUtils.getTableName(entityClass);
            List<ColumnInfo> listColumns = EntityUtils.getColumns(entityClass);
            Long id = null;
            for(ColumnInfo c:listColumns)
            {
                Field f = entityClass.getDeclaredField(c.getColumnName());
                f.setAccessible(true);
                c.setValue(f.get(entityClass.newInstance()));
            }
            QueryBuilder qb = new QueryBuilder();
            Set<Map.Entry<String, Object>> entries = params.entrySet();
            for(Map.Entry<String, Object> entry : entries)
            {
                Condition c = new Condition();
                c.setColumnName(entry.getKey());
                c.setValue(entry.getValue());
                qb.addCondition(c);
            }


            qb.setQueryType(QueryType.SELECT);
            qb.setTableName(tableName);
            qb.addQueryColumns(listColumns);
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery(qb.createQuery());
            List<T> list = new ArrayList<T>();
            while(resultSet.next())
            {
                T instance = entityClass.newInstance();
                for(ColumnInfo column : listColumns)
                {
                    Field field = instance.getClass().getDeclaredField(column.getColumnName());
                    field.setAccessible(true);
                    field.set(instance,EntityUtils.castFromSqlType(resultSet.getObject(column.getDbColumnName()),field.getType()));
                }
                list.add(instance);
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> void multipleInsert(List<T> list) {
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Object entity : list) {
            String tableName = EntityUtils.getTableName(entity.getClass());
            List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());

            for (ColumnInfo column : columns) {
                if (column.isId()) {
                    column.setValue(getNextIdVal(tableName, column.getDbColumnName())+list.indexOf(entity));
                } else {
                    Field field = null;
                    try {
                        field = entity.getClass().getDeclaredField(column.getColumnName());
                        field.setAccessible(true);
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (field != null) {
                            column.setValue(field.get(entity));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.setQueryType(QueryType.INSERT);
            queryBuilder.setTableName(tableName);
            queryBuilder.addQueryColumns(columns);
            String query = queryBuilder.createQuery();
            try {
                connection.createStatement().executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> findEmployees(String param) {
        try {
            Connection connection = DBManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLOYEES, DEPARTMENTS\n" +
                    "WHERE EMPLOYEES.DEPARTMENT_ID = DEPARTMENTS.DEPARTMENT_ID\n" +
                    "AND UPPER(DEPARTMENTS.DEPARTMENT_NAME) LIKE UPPER('%"+param+"%')");
            List<Employee> list = new ArrayList<Employee>();
            while (resultSet.next())
            {
                Employee employee = new Employee();
                employee.setEmployee_id(resultSet.getLong("EMPLOYEE_ID"));
                list.add(employee);
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
