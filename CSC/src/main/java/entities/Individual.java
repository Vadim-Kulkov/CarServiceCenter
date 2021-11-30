package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@MappedSuperclass
public class Individual extends Customer {

    @Id
    @Column(name = "passportNo", nullable = false)
    private String passportNo;

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Individual that = (Individual) o;
        return passportNo.equals(that.passportNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passportNo);
    }
}
