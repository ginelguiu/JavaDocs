package ro.teamnet.zth.api.em;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by Ginel.Guiu on 7/12/2017.
 */
public class ColumnInfo {
    private String columnName;
    private Class columnType;
    private String dbColumnName;
    private Boolean isId;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Class getColumnType() {
        return columnType;
    }

    public void setColumnType(Class columnType) {
        this.columnType = columnType;
    }

    public String getDbColumnName() {
        return dbColumnName;
    }

    public void setDbColumnName(String dbColumnName) {
        this.dbColumnName = dbColumnName;
    }

    public Boolean getId() {
        return isId;
    }

    public void setId(Boolean id) {
        isId = id;
    }
}
