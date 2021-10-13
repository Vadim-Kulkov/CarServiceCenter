/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.main;

public enum Currency {

    USD,
    EUR,
    JOY,
    TRY,
    AED,
    RUB;

    public static final double[][] CURRENCY_RATIOS = {
            // USD-USD, USD-EUR, USD-JOY, USD-TRY, USD-AED, USD-RUB
            {1.0, 0.85, 109.6, 8.64, 3.67, 72.8},

            // EUR-USD, EUR-EUR, EUR-JOY, EUR-TRY, EUR-AED, EUR-RUB
            {1.17, 1.0, 0.6, 10.14, 4.31, 85.5},

            // JOY-USD, JOY-EUR, JOY-JOY, JOY-TRY, JOY-AED, JOY-RUB
            {0.079, 0.078, 1.0, 0.091, 0.034, 0.66},

            // TRY-USD  TRY-EUR, TRY-JOY, TRY-TRY, TRY-AED, TRY-RUB
            {0.43, 0.99, 12.69, 1.0, 0.12, 8.43},

            // AED-USD, AED-EUR, AED-JOY, AED-TRY, AED-AED, AED-RUB
            {2.35, 0.23, 29.84, 0.27, 1.0, 19.84},

            // RUB-USD, RUB-EUR, RUB-JOY, RUB-TRY, RUB-AED  RUB-RUB
            {0.014, 0.012, 1.5, 0.12, 0.05, 1.0}
    };
}
