package entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "carservicecenter")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarServiceCenter implements Serializable {

    @Id
    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY)
    private List<Employee> employees;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "Local_city", nullable = false)
    private City city;

    @Column(nullable = false)
    private String address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Company_Customers",
            joinColumns = @JoinColumn(name = "Customer_phone", referencedColumnName = "phone"),
            inverseJoinColumns = @JoinColumn(name = "Service_name", referencedColumnName = "name")
    )
    private List<Customer> customers;

    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY)
    private List<Repair> repairs;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarServiceCenter that = (CarServiceCenter) o;
        return name.equals(that.name)
                && city.equals(that.city)
                && address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city, address);
    }
}
