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
public class City {

    @Column
    private String name;

    @Id
    @Column(unique = true, nullable = false)
    private String code;

    @Column
    private Boolean defaultCity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
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
        return Objects.equals(name, city.name)
                && code.equals(city.code)
                && Objects.equals(defaultCity, city.defaultCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, defaultCity);
    }
}
