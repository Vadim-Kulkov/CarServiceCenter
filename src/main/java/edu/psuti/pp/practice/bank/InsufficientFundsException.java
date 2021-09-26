package edu.psuti.pp.practice.bank;

import java.lang.reflect.Method;

public class InsufficientFundsException extends Exception {
    @Override
    public String getMessage() {
        return "Ошибка: попытка списать со счёта сумму," +
                " превышающую остаток или лимит по кредитной карте";
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        Class account = InsufficientFundsException.class;
        Method[] methods = account.getDeclaredMethods();
        for (Method i : methods) {
            answer.append(i);
            answer.append('\n');
        }
        return answer.toString();
    }
}
