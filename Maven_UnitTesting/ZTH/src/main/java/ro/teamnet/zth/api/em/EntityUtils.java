package ro.teamnet.zth.api.em;

import java.lang.reflect.*;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ginel.Guiu on 7/12/2017.
 */
public class EntityUtils {
    private EntityUtils() throws UnsupportedOperationException
    {

    }

    public static String getTableName(Class entity)
    {
        String v;
        if(entity.getAnnotation(Table.class)!=null)
            v=entity.getAnnotation(Table.class).getClass().getName();
        else
            v=null;
        return v;
    }

    public static List<ColumnInfo> getColumns(Class entity)
    {
        List<ColumnInfo> list = new ArrayList<>();
        Field[] fields=entity.getDeclaredFields();
        for(Field f:fields)
        {
            if(entity.getAnnotation(Column.class)!=null || entity.getAnnotation(Id.class)!=null)
            {
                ColumnInfo col = new ColumnInfo();
                col.setColumnName(f.getName());
                col.setColumnType(f.getType());
                col.setDbColumnName(f.getAnnotation(Id.class).name());
                list.add(col);
            }
        }
        return list;
    }

    public static Object castFromSqlType(Object value, Class wantedType)
    {
        if(value instanceof BigDecimal && wantedType.getName().equals("java.lang.integer"))
        {
            return ((BigDecimal) value).intValue();
        }

        if(value instanceof BigDecimal && wantedType.getName().equals("java.lang.long"))
        {
            return ((BigDecimal) value).longValue();
        }
        if(value instanceof BigDecimal && wantedType.getName().equals("java.lang.float"))
        {
            return ((BigDecimal) value).floatValue();
        }
        if(value instanceof BigDecimal && wantedType.getName().equals("java.lang.double"))
        {
            return ((BigDecimal) value).doubleValue();
        }
        return value;
    }

    public static List<Field> getFieldsByAnnotations(Class clazz, Class annotation)
    {
        Field[] f = clazz.getDeclaredFields();
        List<Field> list = new ArrayList<Field>();
        for(Field t : f)
        {
            if(t.isAnnotationPresent(annotation))
                list.add(t);
        }
        return list;
    }
}
