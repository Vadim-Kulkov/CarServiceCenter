package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carServiceCenter")
public class CarServiceCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarServiceCenter;
    @Column
    private String title;
    @Column
    private String phone;

    @Column
    @OneToMany(mappedBy = "carServiceCenter")
    private List<Employee> employees;

    @ManyToOne()
    @JoinColumn(name = "idCity", table = "city")
    private City city;
    @Column
    private String address;
    @Column
    @ManyToMany
    @JoinColumn(table = "customer", name = "centers")
    private List<Customer> customers;
    @Column
    @OneToMany
    @JoinColumn(name = "repairs", table = "repair")
    private List<Repair> repairs;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Long getIdCarServiceCenter() {
        return idCarServiceCenter;
    }

    public void setIdCarServiceCenter(Long idCarServiceCenter) {
        this.idCarServiceCenter = idCarServiceCenter;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
