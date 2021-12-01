package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@MappedSuperclass
public class Company extends Customer {

    @Id
    @Column(nullable = false, unique = true)
    private String inn;

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Company company = (Company) o;
        return inn.equals(company.inn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inn);
    }
}
