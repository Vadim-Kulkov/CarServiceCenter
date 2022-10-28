package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "individual")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Individual extends Customer implements Serializable {

    @Column(unique = true, nullable = false)
    private String passportNo;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Individual that = (Individual) o;
        return passportNo.equals(that.passportNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passportNo);
    }
}
