package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarServiceCenter {

    @Id
    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY)
    private List<Employee> employees;

    @ManyToOne(fetch = FetchType.LAZY)
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
                && phone.equals(that.phone)
                && Objects.equals(employees, that.employees)
                && city.equals(that.city)
                && address.equals(that.address)
                && Objects.equals(customers, that.customers)
                && Objects.equals(repairs, that.repairs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, employees, city, address, customers, repairs);
    }
}
