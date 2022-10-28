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
@Table(name = "city")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class City implements Serializable {

    @Column
    private String name;

    @Id
    @Column(unique = true, nullable = false)
    private String code;

    @Column
    private Boolean defaultCity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city", cascade = CascadeType.REFRESH)
    private List<CarServiceCenter> centers;

    @Column(name = "defaultCity")
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        City city = (City) o;
        return code.equals(city.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
