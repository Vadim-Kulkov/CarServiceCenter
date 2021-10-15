/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.main;


import java.util.Objects;

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
                        Currency currency) {
        super(id, balance, commission, currency);
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
        return Objects.hash(
                getBalance(),
                getCommission(),
                getCurrency(),
                getId()
        );
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("DebitAccount{");
        sb.append("balance=")
                .append(getBalance())
                .append(", id=")
                .append(getId())
                .append(", commission=")
                .append(getCommission())
                .append(", currency=")
                .append(getCurrency())
                .append('}');
        sb.append('}');
        return sb.toString();
    }
}
