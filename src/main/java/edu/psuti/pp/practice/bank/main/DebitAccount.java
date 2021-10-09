/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.main;

/*
Этот класс не добавляет свои методы и поля, и не переопределяет методы и поля
суперкласса. Класс определяет аналогичные суперклассу конструкторы, в которых
просто вызывает соответствующий конструктор суперкласса
*/
public class DebitAccount extends Account {

    public DebitAccount(int id) {
        this(
                id,
                DEFAULT_BALANCE,
                DEFAULT_COMMISSION,
                DEFAULT_CURRENT_CURRENCY
        );
    }

    public DebitAccount(int id, double balance) {
        this(
                id,
                balance,
                DEFAULT_COMMISSION,
                DEFAULT_CURRENT_CURRENCY
        );
    }

    public DebitAccount(int id, double balance, double commission) {
        this(
                id,
                balance,
                commission,
                DEFAULT_CURRENT_CURRENCY
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
                that.getCurrentCurrency() == getCurrentCurrency();
    }

    @Override
    public int hashCode() {
        return 111_111 ^
                Double.hashCode(getBalance()) ^
                Double.hashCode(getCommission()) ^
                getCurrentCurrency().hashCode() ^
                getId();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DebitAccount{");
        sb.append("id=")
                .append(getId());
        sb.append(", balance=").append(getBalance());
        sb.append(", currentCurrency=").append(getCurrentCurrency());
        sb.append(", Commission=").append(getCommission());
        sb.append('}');
        return sb.toString();
    }
}
