package edu.psuti.pp.practice.bank;

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
                        AccountNumberGenerator.Currency valut) {
        super(number, ost, comiss, valut);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        DebitAccount debAcc = (DebitAccount) obj;
        if (debAcc.getOst() != getOst())
            return false;
        if (debAcc.getComiss() != getComiss())
            return false;
        if (debAcc.getNumber() != getNumber())
            return false;
        if (debAcc.getValut() != getValut())
            return false;
        return true;
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

        answer.append(getClass());
        answer.append('\n');

        answer.append("private int number: ");
        answer.append(getNumber());
        answer.append('\n');

        answer.append("protected long ost: ");
        answer.append(getOst());
        answer.append('\n');

        answer.append("private long comiss: ");
        answer.append(getComiss());
        answer.append('\n');

        answer.append("private AccountNumberGenerator.Currency valut: ");
        answer.append(getValut());
        answer.append('\n');

        for (Method i : methods) {
            answer.append(i);
            answer.append('\n');
        }
        return answer.toString();
    }
}
