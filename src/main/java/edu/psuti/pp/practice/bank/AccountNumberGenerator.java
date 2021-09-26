package edu.psuti.pp.practice.bank;

import java.lang.reflect.Method;

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

    enum Currency {
        USD,
        EUR,
        JOY,
        TRY,
        AED,
        RUB;
    }

    //public void generate() { }
    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        Class account = Account.class;
        Method[] methods = account.getDeclaredMethods();

        answer.append(getClass());
        answer.append('\n');

        answer.append("private static int num: ");
        answer.append(num);
        answer.append('\n');


        for (Method i : methods) {
            answer.append(i);
            answer.append('\n');
        }
        return answer.toString();
    }
}
