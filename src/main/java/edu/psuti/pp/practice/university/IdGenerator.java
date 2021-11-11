/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.university;

public class IdGenerator {

    private static final int DEFAULT_VALUE = 0;

    private static int value = DEFAULT_VALUE;

    public static int getNext() {
        return ++value;
    }

    public static int getCurrent() {
        return value;
    }

    private void reset() {
        value = DEFAULT_VALUE;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountNumberGenerator{");
        sb.append("value= ")
                .append(value)
                .append('}');
        return sb.toString();
    }
}
