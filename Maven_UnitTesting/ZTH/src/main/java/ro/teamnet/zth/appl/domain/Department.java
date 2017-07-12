package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;

/**
 * Created by Ginel.Guiu on 7/12/2017.
 */
public class Department {
    @Id(name="department_id")
    private Long id;
    @Column(name="department_name")
    private String departmentName;
    @Column(name="location_id")
    private Long location;

    public long getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public long getLocation() {
        return location;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setLocation(long location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", location=" + location +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (id != that.id) return false;
        if (location != that.location) return false;
        return departmentName != null ? departmentName.equals(that.departmentName) : that.departmentName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (departmentName != null ? departmentName.hashCode() : 0);
        result = 31 * result + (int) (location ^ (location >>> 32));
        return result;
    }
}
