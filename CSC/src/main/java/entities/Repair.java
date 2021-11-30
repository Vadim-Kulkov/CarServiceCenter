package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Repair {

    @Id
    @Column(unique = true, nullable = false)
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Worker", nullable = false)
    private Employee employee;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CarServiceCenter", nullable = false)
    private CarServiceCenter center;

    public CarServiceCenter getCenter() {
        return center;
    }

    public void setCenter(CarServiceCenter center) {
        this.center = center;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repair repair = (Repair) o;
        return description.equals(repair.description) && employee.equals(repair.employee) && center.equals(repair.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, employee, center);
    }
}
