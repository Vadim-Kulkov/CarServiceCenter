/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.main;

import java.lang.reflect.Method;

/*
Этот класс не добавляет свои методы и поля, и не переопределяет методы и поля
суперкласса. Класс определяет аналогичные суперклассу конструкторы, в которых
просто вызывает соответствующий конструктор суперкласса
*/
public class DebitAccount extends Account {

    public DebitAccount(int number) {
        super(number);
    }

    public DebitAccount(int number, double ost) {
        super(number, ost);
    }

    public DebitAccount(int number, double ost, double comiss) {
        super(number, ost, comiss);
    }

    public DebitAccount(int number,
                        double ost,
                        double comiss,
                        Currency valut) {
        super(number, ost, comiss, valut);
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
        if (debAcc.getOst() != getOst()) {
            return false;
        }
        if (debAcc.getComiss() != getComiss()) {
            return false;
        }
        if (debAcc.getNumber() != getNumber()) {
            return false;
        }
        return debAcc.getValut() == getValut();
    }

    @Override
    public int hashCode() {
        int number = 1_111_111_111;
        number ^= Double.hashCode(getOst());
        number ^= getNumber();
        number ^= Double.hashCode(getComiss());
        number ^= (getValut() == null) ? 0 : getValut().hashCode();
        return number;
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        Class account = DebitAccount.class;
        Method[] methods = account.getDeclaredMethods();

        answer.append(getClass())
                .append('\n');

        answer.append("private int number: ")
                .append(getNumber())
                .append('\n');

        answer.append("protected long ost: ")
                .append(getOst())
                .append('\n');

        answer.append("private long comiss: ")
                .append(getComiss())
                .append('\n');

        answer.append("private Currency valut: ")
                .append(getValut())
                .append('\n');

        for (Method i : methods) {
            answer.append(i)
                    .append('\n');
        }
        return answer.toString();
    }
}
