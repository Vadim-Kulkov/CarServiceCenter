/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.service;

import edu.psuti.pp.practice.bank.main.Currency;

public class Recount {

    public static long doubleToLong(double value) {
        return (long) (value * 100);
    }

    public static double longToDouble(double value) {
        return (value / 100.0);
    }

    private static int findFirstPosition(Currency value) {
        if (value == Currency.USD) {
            return 0;
        } else if (value == Currency.EUR) {
            return 1;
        } else if (value == Currency.JOY) {
            return 2;
        } else if (value == Currency.TRY) {
            return 3;
        } else if (value == Currency.AED) {
            return 4;
        } else if (value == Currency.RUB) {
            return 5;
        } else {
            return 6;
        }

    }

    public static double recountValue(double value, Currency old, Currency newCurrency) {
        int firstPosition = findFirstPosition(old),
                secondPosition = findFirstPosition(newCurrency);

        return value * Currency.currencyRatios[firstPosition][secondPosition];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Recount{");
        sb.append('}');
        return sb.toString();
    }
}


