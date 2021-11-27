package entities;

import javax.persistence.*;

@Entity
@Table(name = "individual")
public class Individual extends Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCustomer;
    @Column
    private String passportNo;

    @Override
    public Long getIdCustomer() {
        return idCustomer;
    }

    @Override
    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }
}
