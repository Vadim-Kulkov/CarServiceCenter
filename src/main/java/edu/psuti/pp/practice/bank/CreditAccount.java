package edu.psuti.pp.practice.bank;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

public class CreditAccount extends Account {
    // процентная ставка
    private double percentSt;
    // лимит по кредитной карте
    private double creditLimit;
    // начисленные проценты
    private double assessedPercent;
    // начисленные комиссионные
    private double assessedComission;

    public CreditAccount(int number) {
        super(number);
    }

    public CreditAccount(int number, double ost) {
        super(number, ost);
    }

    public CreditAccount(int number, double ost, double comiss) {
        super(number, ost, comiss);
    }

    public CreditAccount(int number,
                         double ost,
                         double comiss,
                         AccountNumberGenerator.Currency valut) {
        super(number, ost, comiss, valut);
    }

    public CreditAccount(int number,
                         double ost,
                         double comiss,
                         AccountNumberGenerator.Currency valut,
                         double percentSt,
                         double creditLimit) {
        super(number, ost, comiss, valut);
        this.percentSt = percentSt;
        this.creditLimit = creditLimit;
    }

    // процентная ставка
    public double getPercentSt() {
        return percentSt;
    }

    public void setPercentSt(double percentSt) {
        this.percentSt = percentSt;
    }

    // лимит по кредитной карте
    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double CreditLimit) {
        this.creditLimit = creditLimit;
    }

    // начисленные проценты
    public double getAssessedPercent() {
        return assessedPercent;
    }

    public void setAssessedPercent(double assessedPercent) {
        this.assessedPercent = assessedPercent;
    }

    // начисленные комиссионные
    public double getAssessedComission() {
        return assessedComission;
    }

    public void setAssessedComission(double assessedComission) {
        this.assessedComission = assessedComission;
    }

    // метод начисления процентов
    public void accrualPercent() {
        if (getOst() < getCreditLimit())
            assessedPercent += (creditLimit - getOst()) * (percentSt / actualDaysOfYear() / 100.0);
        else
            assessedPercent += (getOst() * percentSt) / 100;
    }

    @Override
    public void comissFromOst() {
        assessedComission += getComiss();
    }

    @Override
    public void popOst(double value) {
        try {
            if (value > creditLimit | value > ost)
                throw new InsufficientFundsException();
            if (value == 0) return;
            if (assessedComission > 0) {
                if (assessedComission >= value) {
                    assessedComission -= value;
                    return;
                } else {
                    value -= assessedComission;
                    assessedComission = 0;
                }
            }
            if (value != 0 && assessedPercent > 0) {
                if (assessedPercent >= value) {
                    assessedPercent -= value;
                    return;
                } else {
                    value -= assessedPercent;
                    assessedPercent = 0;
                }
            }
            if (value != 0) {
                ost -= doubleToLong(value);
            }
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private int actualDaysOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        CreditAccount debAcc = (CreditAccount) obj;
        if (debAcc.getOst() != getOst())
            return false;
        if (debAcc.getComiss() != getComiss())
            return false;
        if (debAcc.getNumber() != getNumber())
            return false;
        if (debAcc.getValut() != getValut())
            return false;

        if (debAcc.getPercentSt() != getPercentSt())
            return false;
        if (debAcc.getCreditLimit() != getCreditLimit())
            return false;
        if (debAcc.getAssessedPercent() != getAssessedPercent())
            return false;
        if (debAcc.getAssessedComission() != getAssessedComission())
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

        number ^= Double.hashCode(getPercentSt());
        number ^= Double.hashCode(getCreditLimit());
        number ^= Double.hashCode(getAssessedPercent());
        number ^= Double.hashCode(getAssessedComission());
        return number;
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();

        Class account = CreditAccount.class;
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

        answer.append("private double percentSt: ");
        answer.append(getPercentSt());
        answer.append('\n');

        answer.append("private double creditLimit: ");
        answer.append(getCreditLimit());
        answer.append('\n');

        answer.append("private double assessedPercent: ");
        answer.append(getAssessedPercent());
        answer.append('\n');

        answer.append("private double assessedComission: ");
        answer.append(getAssessedComission());
        answer.append('\n');

        for (Method i : methods) {
            answer.append(i);
            answer.append('\n');
        }
        return answer.toString();
    }

}
