package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
