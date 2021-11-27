package entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCustomer;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    @ManyToMany
    @JoinColumn(table = "carServiceCenter", name = "customers")
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

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
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
}
