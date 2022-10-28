package entities;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee implements Serializable {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private BigDecimal salary;

    @Id
    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "CarServiceCenter")
    private CarServiceCenter center;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Repair> repairs;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return firstName.equals(employee.firstName)
                && lastName.equals(employee.lastName)
                && birthDate.equals(employee.birthDate)
                && email.equals(employee.email)
                && center.equals(employee.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthDate, email, center);
    }
}
