package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;

/**
 * Created by Ginel.Guiu on 7/12/2017.
 */
public class Jobs {
    @Id(name="job_id")
    private Long job_id;
    @Column(name="job_title")
    private String job_title;
    @Column(name="min_salary")
    private double min_salary;
    @Column(name="max_salary")
    private double max_salary;

    public Long getJob_id() {
        return job_id;
    }

    public void setJob_id(Long job_id) {
        this.job_id = job_id;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public double getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(double min_salary) {
        this.min_salary = min_salary;
    }

    public double getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(double max_salary) {
        this.max_salary = max_salary;
    }

    @Override
    public String toString() {
        return "Jobs{" +
                "job_id=" + job_id +
                ", job_title='" + job_title + '\'' +
                ", min_salary=" + min_salary +
                ", max_salary=" + max_salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jobs jobs = (Jobs) o;

        if (Double.compare(jobs.min_salary, min_salary) != 0) return false;
        if (Double.compare(jobs.max_salary, max_salary) != 0) return false;
        if (job_id != null ? !job_id.equals(jobs.job_id) : jobs.job_id != null) return false;
        return job_title != null ? job_title.equals(jobs.job_title) : jobs.job_title == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = job_id != null ? job_id.hashCode() : 0;
        result = 31 * result + (job_title != null ? job_title.hashCode() : 0);
        temp = Double.doubleToLongBits(min_salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(max_salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
