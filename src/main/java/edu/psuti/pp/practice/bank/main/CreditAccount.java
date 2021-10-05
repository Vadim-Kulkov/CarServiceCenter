/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.main;

import edu.psuti.pp.practice.bank.exceptions.InsufficientFundsException;

import java.util.Calendar;
import java.util.Date;

public class CreditAccount<default_percentSt> extends Account {

    // процентная ставка
    private double percentSt;
    // лимит по кредитной карте
    private double creditLimit;
    // начисленные проценты
    private double assessedPercent;
    // начисленные комиссионные
    private double assessedCommission;

    private static final double default_percentSt = 0;
    private static final double default_creditLimit = 0;

    public CreditAccount(int id) {
        this(id, default_balance, default_commission, default_valut, default_percentSt, default_creditLimit);
    }

    public CreditAccount(int id, double balance) {
        this(id, balance, default_commission, default_valut, default_percentSt, default_creditLimit);
    }

    public CreditAccount(int id, double balance, double commission) {
        this(id, balance, commission, default_valut, default_percentSt, default_creditLimit);
    }

    public CreditAccount(int id,
                         double balance,
                         double commission,
                         Currency valut) {
        this(id, balance, commission, valut, default_percentSt, default_creditLimit);
    }

    public CreditAccount(int id,
                         double balance,
                         double commission,
                         Currency valut,
                         double percentSt,
                         double creditLimit) {
        super(id, balance, commission, valut);
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

    public void setCreditLimit(double creditLimit) {
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
    public double getAssessedCommission() {
        return assessedCommission;
    }

    public void setAssessedCommission(double assessedComission) {
        this.assessedCommission = assessedComission;
    }

    // метод начисления процентов
    public void accrualPercent() {
        if (getBalance() < getCreditLimit()) {
            assessedPercent += (creditLimit - getBalance()) * (percentSt / actualDaysOfYear() / 100.0);
        } else {
            assessedPercent += (getBalance() * percentSt) / 100;
        }
    }

    @Override
    public void commissionFromBalance() throws InsufficientFundsException {
        if (getCommission() > getBalance()) {
            throw new InsufficientFundsException();
        }
        balance -= getCommission();
    }

    @Override
    public void popBalance(double value) {
        try {
            if (value > creditLimit | value > balance) {
                throw new InsufficientFundsException();
            }
            if (value == 0) {
                return;
            }
            if (assessedCommission > 0) {
                if (assessedCommission >= value) {
                    assessedCommission -= value;
                    return;
                } else {
                    value -= assessedCommission;
                    assessedCommission = 0;
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
                balance -= doubleToLong(value);
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
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        CreditAccount debAcc = (CreditAccount) obj;
        if (debAcc.getBalance() != getBalance()) {
            return false;
        }
        if (debAcc.getCommission() != getCommission()) {
            return false;
        }
        if (debAcc.getId() != getId()) {
            return false;
        }
        if (debAcc.getValut() != getValut()) {
            return false;
        }

        if (debAcc.getPercentSt() != getPercentSt()) {
            return false;
        }
        if (debAcc.getCreditLimit() != getCreditLimit()) {
            return false;
        }
        if (debAcc.getAssessedPercent() != getAssessedPercent()) {
            return false;
        }
        return debAcc.getAssessedCommission() == getAssessedCommission();
    }

    @Override
    public int hashCode() {
        int number = 1_111_111_111;
        number ^= Double.hashCode(getBalance());
        number ^= getId();
        number ^= Double.hashCode(getCommission());
        number ^= (getValut() == null) ? 0 : getValut().hashCode();

        number ^= Double.hashCode(getPercentSt());
        number ^= Double.hashCode(getCreditLimit());
        number ^= Double.hashCode(getAssessedPercent());
        number ^= Double.hashCode(getAssessedCommission());
        return number;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreditAccount{");
        sb.append("percentSt=").append(percentSt);
        sb.append(", creditLimit=").append(creditLimit);
        sb.append(", assessedPercent=").append(assessedPercent);
        sb.append(", assessedCommission=").append(assessedCommission);
        sb.append('}');
        return sb.toString();
    }
}
