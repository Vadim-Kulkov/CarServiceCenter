/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.main;


public class DebitAccount extends Account {

    public DebitAccount(int id) {
        this(
                id,
                DEFAULT_BALANCE,
                DEFAULT_COMMISSION,
                DEFAULT_CURRENCY
        );
    }

    public DebitAccount(int id, double balance) {
        this(
                id,
                balance,
                DEFAULT_COMMISSION,
                DEFAULT_CURRENCY
        );
    }

    public DebitAccount(int id, double balance, double commission) {
        this(
                id,
                balance,
                commission,
                DEFAULT_CURRENCY
        );
    }

    public DebitAccount(int id,
                        double balance,
                        double commission,
                        Currency valut) {
        super(id, balance, commission, valut);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DebitAccount that = (DebitAccount) obj;
        return Double.compare(that.getBalance(), getBalance()) == 0 &&
                Double.compare(that.getCommission(), getCommission()) == 0 &&
                that.getId() == getId() &&
                that.getCurrency() == getCurrency();
    }

    @Override
    public int hashCode() {
        return 111_111 ^
                Double.hashCode(getBalance()) ^
                Double.hashCode(getCommission()) ^
                getCurrency().hashCode() ^
                getId();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DebitAccount{");
        sb.append('}');
        return sb.toString();
    }
}
