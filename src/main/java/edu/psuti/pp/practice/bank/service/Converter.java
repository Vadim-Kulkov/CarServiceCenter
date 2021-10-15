/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.service;

import edu.psuti.pp.practice.bank.main.Currency;

public class Converter {

    public static long doubleToLong(double value) {
        return (long) (value * 100);
    }

    public static double longToDouble(double value) {
        return (value / 100);
    }

    private static int findPosition(Currency value) {
        return value.getCurrencyNumber();
    }

    public static double recountValue(double value, Currency old, Currency newCurrency) {
        int firstPosition = findPosition(old),
                secondPosition = findPosition(newCurrency);

        return value * Currency.CURRENCY_RATIOS[firstPosition][secondPosition];
    }
}


