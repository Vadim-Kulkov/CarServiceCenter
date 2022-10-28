package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customer")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer implements Serializable {

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    @Id
    @Column(nullable = false, unique = true)
    private String phone;

    @ManyToMany(mappedBy = "customers", fetch = FetchType.LAZY)
    private List<CarServiceCenter> centers;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return name.equals(customer.name) &&
                phone.equals(customer.phone) &&
                email.equals(customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email);
    }
}

