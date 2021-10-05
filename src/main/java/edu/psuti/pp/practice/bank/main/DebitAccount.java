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

    public DebitAccount(int number) {
        this(number, default_balance, default_commission, default_valut);
    }

    public DebitAccount(int number, double balance) {
        this(number, balance, default_commission, default_valut);
    }

    public DebitAccount(int number, double balance, double commission) {
        this(number, balance, commission, default_valut);
    }

    public DebitAccount(int number,
                        double balance,
                        double commission,
                        Currency valut) {
        super(number, balance, commission, valut);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        DebitAccount debAcc = (DebitAccount) obj;
        if (debAcc.getBalance() != getBalance()) {
            return false;
        }
        if (debAcc.getCommission() != getCommission()) {
            return false;
        }
        if (debAcc.getId() != getId()) {
            return false;
        }
        return debAcc.getValut() == getValut();
    }

    @Override
    public int hashCode() {
        int number = 1_111_111_111;
        number ^= Double.hashCode(getBalance());
        number ^= getId();
        number ^= Double.hashCode(getCommission());
        number ^= (getValut() == null) ? 0 : getValut().hashCode();
        return number;
    }


}
