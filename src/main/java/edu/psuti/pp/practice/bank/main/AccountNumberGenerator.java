/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.main;

// Этот класс нужно использовать для генерации номеров счетов
public class AccountNumberGenerator {

    private static int num = 0;

    public static int getNext() {
        return ++num;
    }

    public static int getCurrent() {
        return num;
    }

    private void reset() {
        num = 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountNumberGenerator{");
        sb.append('}');
        return sb.toString();
    }
}
