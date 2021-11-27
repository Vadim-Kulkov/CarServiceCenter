package entities;

import javax.persistence.*;

@Entity
@Table(name = "repair")
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRepair;
    @Column
    private String description;
    @Column
    @ManyToOne
    @JoinColumn(name = "idEmployee", table = "Employee")
    private Employee employee;
    @Column

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

    public Long getIdRepair() {
        return idRepair;
    }

    public void setIdRepair(Long idRepair) {
        this.idRepair = idRepair;
    }
}
