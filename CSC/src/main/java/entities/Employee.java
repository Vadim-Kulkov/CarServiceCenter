package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
public class Employee {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String birthDate;

    @Column(nullable = false)
    private BigDecimal salary;

    @Id
    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(nullable = false, name = "CarServiceCenter")
    private CarServiceCenter center;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Repair> repairs;

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public CarServiceCenter getCenter() {
        return center;
    }

    public void setCenter(CarServiceCenter center) {
        this.center = center;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return firstName.equals(employee.firstName)
                && lastName.equals(employee.lastName)
                && birthDate.equals(employee.birthDate)
                && salary.equals(employee.salary)
                && email.equals(employee.email)
                && center.equals(employee.center)
                && Objects.equals(repairs, employee.repairs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthDate, salary, email, center, repairs);
    }
}