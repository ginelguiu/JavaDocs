package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;

import java.util.Date;

/**
 * Created by Ginel.Guiu on 7/12/2017.
 */
public class Employee {
    @Id(name="employee_id")
    private Long employee_id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private int phone_number;
    @Column(name = "hire_date")
    private Date hire_date;
    @Column(name = "job_id")
    private String job_id;
    @Column(name = "salary")
    private int salary;
    @Column(name = "commission_pct")
    private int commission_pct;
    @Column(name = "manager_id")
    private int manager_id;
    @Column(name = "department_id")
    private int department_id;

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getCommission_pct() {
        return commission_pct;
    }

    public void setCommission_pct(int commission_pct) {
        this.commission_pct = commission_pct;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", phone_number=" + phone_number +
                ", hire_date=" + hire_date +
                ", job_id='" + job_id + '\'' +
                ", salary=" + salary +
                ", commission_pct=" + commission_pct +
                ", manager_id=" + manager_id +
                ", department_id=" + department_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (phone_number != employee.phone_number) return false;
        if (salary != employee.salary) return false;
        if (commission_pct != employee.commission_pct) return false;
        if (manager_id != employee.manager_id) return false;
        if (department_id != employee.department_id) return false;
        if (employee_id != null ? !employee_id.equals(employee.employee_id) : employee.employee_id != null)
            return false;
        if (first_name != null ? !first_name.equals(employee.first_name) : employee.first_name != null) return false;
        if (last_name != null ? !last_name.equals(employee.last_name) : employee.last_name != null) return false;
        if (email != null ? !email.equals(employee.email) : employee.email != null) return false;
        if (hire_date != null ? !hire_date.equals(employee.hire_date) : employee.hire_date != null) return false;
        return job_id != null ? job_id.equals(employee.job_id) : employee.job_id == null;
    }

    @Override
    public int hashCode() {
        int result = employee_id != null ? employee_id.hashCode() : 0;
        result = 31 * result + (first_name != null ? first_name.hashCode() : 0);
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + phone_number;
        result = 31 * result + (hire_date != null ? hire_date.hashCode() : 0);
        result = 31 * result + (job_id != null ? job_id.hashCode() : 0);
        result = 31 * result + salary;
        result = 31 * result + commission_pct;
        result = 31 * result + manager_id;
        result = 31 * result + department_id;
        return result;
    }
}
