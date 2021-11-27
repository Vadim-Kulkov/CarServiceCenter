package entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmployee;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String birthDate;
    @Column
    private BigDecimal salary;
    @Column
    private String email;
    @Column
    @ManyToOne
    @JoinColumn(name = "employees", table = "carServiceCenter")
    private CarServiceCenter center;


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

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
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
}
