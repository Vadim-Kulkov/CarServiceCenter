/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.exceptions;

public class InsufficientFundsException extends Exception {
    private final String message = "Ошибка: попытка списать со счёта сумму, " +
            "превышающую остаток или лимит по кредитной карте";

    @Override
    public String getMessage() {
        return message;
    }
}
