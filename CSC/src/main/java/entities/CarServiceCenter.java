package entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
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

    @Column
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Company_Customers",
            joinColumns = @JoinColumn(name = "Customer_phone", referencedColumnName = "phone"),
            inverseJoinColumns = @JoinColumn(name = "Service_name", referencedColumnName = "name")
    )
    private List<Customer> customers;

    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY)
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

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarServiceCenter that = (CarServiceCenter) o;
        return name.equals(that.name) && phone.equals(that.phone) && Objects.equals(employees, that.employees) && city.equals(that.city) && address.equals(that.address) && Objects.equals(customers, that.customers) && Objects.equals(repairs, that.repairs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, employees, city, address, customers, repairs);
    }
}
