package entities;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Id
    @Column(nullable = false, unique = true)
    private String phone;

    @ManyToMany(mappedBy = "customers", fetch = FetchType.LAZY)
    private List<CarServiceCenter> centers;

    public List<CarServiceCenter> getCenters() {
        return centers;
    }

    public void setCenters(List<CarServiceCenter> centers) {
        this.centers = centers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return name.equals(customer.name) &&
                email.equals(customer.email) &&
                phone.equals(customer.phone) &&
                centers.equals(customer.centers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                name,
                email,
                phone,
                centers
        );
    }
}
