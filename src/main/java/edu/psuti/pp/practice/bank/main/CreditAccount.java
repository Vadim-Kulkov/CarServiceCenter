/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.main;

import edu.psuti.pp.practice.bank.exceptions.InsufficientFundsException;
import edu.psuti.pp.practice.bank.service.Recount;

import java.util.Calendar;
import java.util.Date;

public class CreditAccount<default_interestRate> extends Account {

    // процентная ставка
    private double percentRate;
    // лимит по кредитной карте
    private double creditCardLimit;
    // начисленные проценты
    private double assessedPercent;
    // начисленные комиссионные
    private double assessedCommission;

    private static final double default_percentRate = 0;
    private static final double default_creditCardLimit = 0;

    public CreditAccount(int id) {
        this(id, default_balance, default_commission, default_valut, default_percentRate, default_creditCardLimit);
    }

    public CreditAccount(int id, double balance) {
        this(id, balance, default_commission, default_valut, default_percentRate, default_creditCardLimit);
    }

    public CreditAccount(int id, double balance, double commission) {
        this(id, balance, commission, default_valut, default_percentRate, default_creditCardLimit);
    }

    public CreditAccount(int id,
                         double balance,
                         double commission,
                         Currency valut) {
        this(id, balance, commission, valut, default_percentRate, default_creditCardLimit);
    }

    public CreditAccount(int id,
                         double balance,
                         double commission,
                         Currency valut,
                         double percentRate,
                         double creditCardLimit) {
        super(id, balance, commission, valut);
        this.percentRate = percentRate;
        this.creditCardLimit = creditCardLimit;
    }

    // процентная ставка
    public double getPercentRate() {
        return percentRate;
    }

    public void setPercentRate(double percentRate) {
        this.percentRate = percentRate;
    }

    // лимит по кредитной карте
    public double getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(double creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
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
        if (getBalance() < getCreditCardLimit()) {
            assessedPercent += (creditCardLimit - getBalance()) * (percentRate / actualDaysOfYear() / 100.0);
        } else {
            assessedPercent += (getBalance() * percentRate) / 100;
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
    public void popBalance(double value) throws InsufficientFundsException {

        if (value > creditCardLimit | value > balance) {
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
            balance -= Recount.doubleToLong(value);
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

        if (debAcc.getPercentRate() != getPercentRate()) {
            return false;
        }
        if (debAcc.getCreditCardLimit() != getCreditCardLimit()) {
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

        number ^= Double.hashCode(getPercentRate());
        number ^= Double.hashCode(getCreditCardLimit());
        number ^= Double.hashCode(getAssessedPercent());
        number ^= Double.hashCode(getAssessedCommission());
        return number;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreditAccount{");
        sb.append("percentRate=").append(percentRate);
        sb.append(", creditСardLimit=").append(creditCardLimit);
        sb.append(", assessedPercent=").append(assessedPercent);
        sb.append(", assessedCommission=").append(assessedCommission);
        sb.append('}');
        return sb.toString();
    }
}
