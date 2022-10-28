package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "repair")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Repair implements Serializable {

    @Id
    @Column(unique = true, nullable = false)
    private String description;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Worker", nullable = false)
    private Employee employee;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CarServiceCenter", nullable = false)
    private CarServiceCenter center;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Repair repair = (Repair) o;
        return description.equals(repair.description)
                && employee.equals(repair.employee)
                && center.equals(repair.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, employee, center);
    }
}
