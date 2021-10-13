/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.main;

public class AccountNumberGenerator {

    private static int value = 0;

    public static int getNext() {
        return ++value;
    }

    public static int getCurrent() {
        return value;
    }

    private void reset() {
        value = 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountNumberGenerator{");
        sb.append('}');
        return sb.toString();
    }
}
