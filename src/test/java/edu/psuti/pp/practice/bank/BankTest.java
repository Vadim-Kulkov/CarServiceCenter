package edu.psuti.pp.practice.bank;

import edu.psuti.pp.practice.bank.exceptions.InsufficientFundsException;
import edu.psuti.pp.practice.bank.main.*;

import java.util.ArrayList;
import java.util.List;

public class BankTest {
    // методы тестирования классов
    public static void main(String[] args) {
        testAccountNumberGenerator();
        testCreditAccountCounstructors();
        testCreditAccountGettersAndSetters();
        testCreditAccountMethods1();
        testCreditAccountMethods2();
        testCreditAccountMethods3();
        testCreditAccountMethods4();
        testDebitAccount();
        testInsufficientFundsException();
        testNaturalClient();
        testNaturalClient2();
        testNaturalClient3();
        testNaturalClient4();
    }

    private static void testAccountNumberGenerator() {
        System.out.println(">>>>>>>>>>Test Class AccountNumberGenerator");
        AccountNumberGenerator a = new AccountNumberGenerator();
        System.out.print("Начальное значение : ");
        System.out.println(AccountNumberGenerator.getCurrent());
        System.out.println("Новое значение :");
        System.out.println(AccountNumberGenerator.getNext());
        System.out.println(AccountNumberGenerator.getCurrent());
    }

    private static void testCreditAccountCounstructors() {
        System.out.println(">>>>>>> Test Class CreditAccount");
        CreditAccount creditAccount01 = new CreditAccount(AccountNumberGenerator.getCurrent());
        CreditAccount creditAccount02 = new CreditAccount(AccountNumberGenerator.getNext(), 50_00);
        CreditAccount creditAccount03 = new CreditAccount(
                AccountNumberGenerator.getNext(),
                10_000,
                100);
        CreditAccount creditAccount04 = new CreditAccount(AccountNumberGenerator.getNext(), 25_000,
                150,
                Currency.USD);
        CreditAccount creditAccount05 = new CreditAccount(AccountNumberGenerator.getNext(), 45_000,
                80,
                Currency.RUB,
                5,
                100_000);
    }

    private static void testCreditAccountGettersAndSetters() {
        CreditAccount creditAccount01 = new CreditAccount(AccountNumberGenerator.getCurrent());
        System.out.println(creditAccount01.getPercentRate() + ' ' + creditAccount01.getCreditCardLimit());
        System.out.println(creditAccount01.getAssessedPercent() + ' ' + creditAccount01.getAssessedCommission());
        System.out.println(creditAccount01.getBalance() + ' ' + creditAccount01.getCommission());
        System.out.println(creditAccount01.getId() + ' ' + creditAccount01.getCurrency().toString());
        creditAccount01.setPercentRate(5);
        creditAccount01.setCreditCardLimit(150_000);
        creditAccount01.setAssessedPercent(312);
        creditAccount01.setAssessedCommission(500);
        creditAccount01.setCommission(33);
        creditAccount01.setId(AccountNumberGenerator.getNext());
        creditAccount01.setCurrency(Currency.RUB);
        System.out.println(">Поменяли значения");
        System.out.println(creditAccount01.getBalance());
        System.out.println(creditAccount01.getCommission());
        System.out.println(creditAccount01.getCurrency());
    }

    private static void testCreditAccountMethods1() {
        CreditAccount creditAccount01 = new CreditAccount(AccountNumberGenerator.getCurrent(), 500,
                300);
        creditAccount01.setPercentRate(5);
        creditAccount01.setCreditCardLimit(150_000);
        creditAccount01.setAssessedPercent(312);
        creditAccount01.setAssessedCommission(500);
        creditAccount01.setCommission(33);
        creditAccount01.setId(AccountNumberGenerator.getNext());
        creditAccount01.setCurrency(Currency.RUB);
        System.out.println(">>setCurrentCurrency");
        System.out.println(creditAccount01.getCurrency());
        System.out.println(creditAccount01.getCommission());
        System.out.println(creditAccount01.getBalance());
        creditAccount01.setCurrency(Currency.JOY);
        System.out.println(">Поменяли валюту ");
        System.out.println(creditAccount01.getCurrency());
        System.out.println(creditAccount01.getCommission());
        System.out.println(creditAccount01.getBalance());
    }

    private static void testCreditAccountMethods2() {
        CreditAccount creditAccount05 = new CreditAccount(AccountNumberGenerator.getNext(), 45_000,
                80, Currency.RUB, 5, 100_000);
        System.out.println(">>Тест методов");
        System.out.println(">accrualPercent метод начисления процентов");
        System.out.println("Начисленные проценты: " + creditAccount05.getAssessedPercent());
        creditAccount05.accruePercent();
        System.out.println("Новые проценты: " + creditAccount05.getAssessedPercent());
        CreditAccount creditAccount06 = new CreditAccount(AccountNumberGenerator.getNext(), 45_000,
                80, Currency.RUB, 5, 100_000);
        System.out.println(">Метод comissFromBalance, вычитающий комиссию из остатка");
        System.out.println(creditAccount06.getCommission());
        System.out.println(creditAccount06.getBalance());
        try {
            creditAccount06.debitCommissionFromBalance();
        } catch (InsufficientFundsException e) {
            e.getMessage();
        }
        System.out.println(creditAccount06.getAssessedCommission() + ' ' + creditAccount06.getBalance());
    }

    private static void testCreditAccountMethods3() {
        CreditAccount creditAccount06 = new CreditAccount(AccountNumberGenerator.getNext(), 45_000,
                80, Currency.RUB, 5, 100_000);
        System.out.println(">addToBalance, метод пополнения счёта");
        System.out.println("Остаток " + creditAccount06.getBalance());
        creditAccount06.setAssessedCommission(10_000);
        System.out.println("Начисленная комиссия " + creditAccount06.getAssessedCommission());
        System.out.println("Начисленные проценты " + creditAccount06.getAssessedPercent());
        try {
            creditAccount06.addToBalance(15_000);
        } catch (InsufficientFundsException e) {
            e.getMessage();
        }
        System.out.println("Остаток после " + creditAccount06.getBalance());
        System.out.println("Начисленная комиссия " + creditAccount06.getAssessedCommission());
        System.out.println("Начисленные проценты " + creditAccount06.getAssessedPercent());
    }

    private static void testCreditAccountMethods4() {
        CreditAccount creditAccount06 = new CreditAccount(AccountNumberGenerator.getNext(), 45_000,
                80, Currency.RUB, 5, 100_000);
        System.out.println(">>метод списывания суммы со счёта deductFromTheBalance");
        System.out.println(creditAccount06.getBalance());
        try {
            creditAccount06.debitTheBalance(10_000);
        } catch (InsufficientFundsException e) {
            e.getMessage();
        }
        System.out.println(creditAccount06.getBalance());
        CreditAccount creditAccount07 = new CreditAccount(6, 45_000, 80, Currency.RUB,
                5, 100_000);
        Account creditAccount08 = new CreditAccount(6, 45_000, 80, Currency.RUB,
                5, 100_000);
        System.out.println(">Equals" + ' ' + creditAccount08.equals(creditAccount07));
        System.out.println(">Hashcode" + ' ' + creditAccount07.hashCode() + " " + creditAccount08.hashCode());
        System.out.println(">ToString" + ' ' + creditAccount08);
    }

    private static void testDebitAccount() {
        System.out.println(">> Class DebitAccount");
        AccountNumberGenerator generator = new AccountNumberGenerator();
        DebitAccount debitAccountAccount01 = new DebitAccount(AccountNumberGenerator.getCurrent());
        DebitAccount creditAccount02 = new DebitAccount(AccountNumberGenerator.getNext(), 50_00);
        DebitAccount creditAccount03 = new DebitAccount(AccountNumberGenerator.getNext(), 10_000,
                100);
        DebitAccount creditAccount04 = new DebitAccount(AccountNumberGenerator.getNext(), 25_000,
                150, Currency.USD);
        System.out.println(">>>Тест методов");
        DebitAccount creditAccount05 = new DebitAccount(5, 25_000, 150, Currency.USD);
        Account creditAccount06 = new DebitAccount(5, 25_000, 150, Currency.USD);
        System.out.println(">Equals");
        System.out.println(creditAccount06.equals(creditAccount05));
        System.out.println(">HashCode");
        System.out.println("одинаковые хешкоды");
        System.out.println(creditAccount06.hashCode() + " " + creditAccount05.hashCode());
        System.out.println(">toString()");
        System.out.println(creditAccount06);
    }

    private static void testInsufficientFundsException() {
        System.out.println(">>>>>>>>>>Class InsufficientFundsException");
        CreditAccount creditAccount06 = new CreditAccount(
                AccountNumberGenerator.getNext(),
                45_000,
                80,
                Currency.RUB,
                5,
                100
        );
        try {
            creditAccount06.addToBalance(999_999);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }

    static List<Account> c = new ArrayList<>();

    static {
        c.add(new CreditAccount(111, 45_000, 80, Currency.RUB, 5, 100_000));
        c.add(new CreditAccount(222, 5_000, 42, Currency.USD, 7, 100_000));
        c.add(new DebitAccount(333, 30_000, 150, Currency.USD));
        c.add(new DebitAccount(444, 25_000, 20, Currency.JOY));
    }

    static NaturalClient c2 = new NaturalClient("Александр",
            "Андреевич",
            "Петров",
            1242,
            65,
            c);

    private static void testNaturalClient() {
        System.out.println(">>>>>>>Class NaturalClient");

        NaturalClient c1 = new NaturalClient("иван", "иванович", "иванов",
                1552, 34);
        System.out.println(">>>Тест методов");
        System.out.println(">метод, возвращающий ссылку на счёт по его номеру");
        System.out.println(c2.getLinkToAccount(333).getId());
        System.out.println('\n' + ">метод,возвращающий массив всех счетов");
        List<Account> test12312 = c2.getAccountList();
        for (Account i : test12312) {
            System.out.println(i.getBalance());
        }
        System.out.println(">возвращающий список счетов дебетовых карт");
        List<Account> test21212 = c2.getDebitList();
        for (Account f : test21212) {
            System.out.println(f.getId());
        }
    }

    private static void testNaturalClient2() {
        System.out.println(">метод, возвращающий список счетов кредитных карт");
        List<Account> test422 = c2.getCreditList();
        for (Account f : test422) {
            System.out.println(f.getId());
        }
        // метод, возвращающий суммарный остаток на всех дебетовых счетах
        System.out.println(">метод, возвращающий остаток на дебетовых счетах");
        System.out.println(c2.getDebitAccountsBalance());
        // метод, возвращающий сумму долга клиента и метод добавления счёта
        System.out.println(">метод, возвращающий долг и метод добавления счёта");
        c2.addAccount(new CreditAccount(213, -99_000, 42, Currency.USD, 7,
                100_000));
        System.out.println(c2.getDebt());
        // метод, возвращающий список счетов с положительным остатком
        System.out.println(">метод, возвращающий счета с положительным счётом");
    }

    private static void testNaturalClient3() {
        List<Account> test223 = c2.getAffirmativeSummaryBalance();
        for (Account i : test223) {
            System.out.println(i.getBalance());
        } // метод удаления счета по его номеру
        System.out.println(">метод удаления счета по его номеру");
        for (Account i : c2.getAccountList()) {
            System.out.print(i.getId() + " ");
        }
        System.out.println();
        c2.deleteAccount(213);
        for (Account i : c2.getAccountList()) {
            System.out.print(i.getId() + " ");
            System.out.println();
        }
        System.out.println(">метод списывания средств со счета ");
        for (Account i : c2.getAccountList()) {
            System.out.print(i.getId() + " " + i.getBalance());
        }
    }

    private static void testNaturalClient4() {
        System.out.println();
        c2.debitBalanceById(444, 10_000);
        for (Account i : c2.getAccountList()) {
            System.out.print(i.getId() + " " + i.getBalance());
        }
        System.out.println(); // метод, возвращающий суммарный остаток на всех счетах
        System.out.println(">метод, возвращающий суммарный остаток на всех счетах");
        System.out.println(c2.getSummaryBalance());
        System.out.println(">ToString" + ' ' + c2); // метод уменьшения размера остатка счета
        System.out.println(">метод уменьшения размера остатка счета");
        System.out.println(c2.getLinkToAccount(444).getBalance());
        c2.debitBalanceByAccount(c2.getLinkToAccount(444), 12);
        System.out.println(c2.getLinkToAccount(444).getBalance());
        //  метод увеличения размера остатка счета
        System.out.println(">метод увеличения размера остатка счета");
        System.out.println(c2.getLinkToAccount(444).getBalance());
        c2.addToBalanceByAccount(c2.getLinkToAccount(444), 1322);
        System.out.println(c2.getLinkToAccount(444).getBalance());
    }
}
